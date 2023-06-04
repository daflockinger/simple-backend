package com.flockinger.simplebackend.api

import com.flockinger.simplebackend.repository.model.Unit

data class StoredSensorDataResponse (
        var id: String,
        var sensorName: String,
        var value: Double,
        var unit: Unit
)