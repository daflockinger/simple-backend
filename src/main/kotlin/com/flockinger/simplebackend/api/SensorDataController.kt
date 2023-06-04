package com.flockinger.simplebackend.api

import com.flockinger.simplebackend.service.SensorDataService
import jakarta.validation.Valid
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController

@RestController
class SensorDataController(
        private val service: SensorDataService
) {

    @RequestMapping(
            path = ["telemetry"], method = [POST],
            consumes = [APPLICATION_JSON_VALUE], produces = [APPLICATION_JSON_VALUE]
    )
    fun addSensorData(
            @RequestBody @Valid sensorDataRequest: AddSensorDataRequest
    ): ResponseEntity<StoredSensorDataResponse> {
        return ok(service.addSensorData(sensorDataRequest))
    }
}