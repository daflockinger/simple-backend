package com.flockinger.simplebackend.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.flockinger.simplebackend.config.SecurityConfig
import com.flockinger.simplebackend.config.SecurityConfig.Companion.ADMN_ROLE
import com.flockinger.simplebackend.repository.model.Unit
import com.flockinger.simplebackend.service.SensorDataService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class SensorDataControllerTest(
        @Autowired private val mockMvc: MockMvc
) {

    @MockkBean
    private lateinit var service: SensorDataService

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `addSensorData with valid data should store using service nicely`() {
        every { service.addSensorData(any()) } returns StoredSensorDataResponse(
                "123", "Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN
        )
        val request = AddSensorDataRequest("Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN)

        mockMvc.perform(post("/telemetry")
                .with(httpBasic("admin", "admin"))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk)
                .andExpect(jsonPath("$.sensorName").value(request.sensorName))
                .andExpect(jsonPath("$.value").value(request.value))
                .andExpect(jsonPath("$.unit").value(request.unit!!.name))
    }

    @Test
    fun `addSensorData with wrong password should return 401`() {
        val request = AddSensorDataRequest("Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN)

        mockMvc.perform(post("/telemetry")
                .with(csrf())
                .with(httpBasic("admin", "1234"))
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isUnauthorized)
    }

    @Test
    fun `addSensorData with wrong user should return 401`() {
        val request = AddSensorDataRequest("Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN)

        mockMvc.perform(post("/telemetry")
                .with(csrf())
                .with(httpBasic("hacker", "admin"))
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isUnauthorized)
    }

    @Test
    fun `addSensorData with empty sensor name should return bad request`() {
        every { service.addSensorData(any()) } returns StoredSensorDataResponse(
                "123", "", -2.2,Unit.DEGREE_KELVIN
        )
        val request = AddSensorDataRequest(null, -2.2,Unit.DEGREE_KELVIN)

        mockMvc.perform(post("/telemetry")
                .with(httpBasic("admin", "admin"))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `addSensorData with too low value should return bad request`() {
        every { service.addSensorData(any()) } returns StoredSensorDataResponse(
                "123", "Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN
        )
        val request = AddSensorDataRequest("Cryo dynamics flux sensor 1", -5000.0,Unit.DEGREE_KELVIN)

        mockMvc.perform(post("/telemetry")
                .with(httpBasic("admin", "admin"))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `addSensorData with null unit should return bad request`() {
        every { service.addSensorData(any()) } returns StoredSensorDataResponse(
                "123", "Cryo dynamics flux sensor 1", -2.2,Unit.DEGREE_KELVIN
        )
        val request = AddSensorDataRequest("Cryo dynamics flux sensor 1", -2.2, null)

        mockMvc.perform(post("/telemetry")
                .with(httpBasic("admin", "admin"))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
    }

}