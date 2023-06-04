package com.flockinger.simplebackend.api

import com.flockinger.simplebackend.repository.model.SensorDataEntity
import com.flockinger.simplebackend.repository.model.Unit
import java.util.*

class AddSensorDataRequest (
    var sensorName: String,
    var value: Double,
    var unit: Unit
) {
    fun toEntity(): SensorDataEntity = SensorDataEntity(
            id = UUID.randomUUID().toString(),
            sensorName = sensorName,
            value = value,
            unit = unit
    )
}