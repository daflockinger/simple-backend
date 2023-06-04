package com.flockinger.simplebackend.service

import com.flockinger.simplebackend.api.AddSensorDataRequest
import com.flockinger.simplebackend.repository.model.Unit
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase()
@AutoConfigureTestEntityManager
class SensorDataServiceTest(
        @Autowired private val service: SensorDataService
) {

    @Test
    fun `addSensorData with valid sensor information should store in database correctly`() {
        val sampleSensorData = AddSensorDataRequest(
                "Super Cooler 3000 Temp 1",
                3.5,
                Unit.DEGREE_CELSIUS
        )
        val result = service.addSensorData(sampleSensorData)
        assertThat(result.id).isNotEmpty()
        assertThat(result).extracting("sensorName", "value", "unit")
                .contains("Super Cooler 3000 Temp 1", 3.5, Unit.DEGREE_CELSIUS)
    }
}