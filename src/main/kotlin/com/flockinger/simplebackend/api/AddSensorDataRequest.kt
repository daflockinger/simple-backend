package com.flockinger.simplebackend.api

import com.flockinger.simplebackend.repository.model.SensorDataEntity
import com.flockinger.simplebackend.repository.model.Unit
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

class AddSensorDataRequest (
        @get:NotNull(message = "Sensor name must not be empty!")
        var sensorName: String?,
        @get:DecimalMin(value="-1000.0", message = "Insertion of unrealistic values is prohibited!")
        var value: Double?,
        @get:NotNull
        var unit: Unit?
) {
    fun toEntity(): SensorDataEntity = SensorDataEntity(
            id = UUID.randomUUID().toString(),
            sensorName = sensorName!!,
            value = value!!,
            unit = unit!!
    )
}