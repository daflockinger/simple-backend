package com.flockinger.simplebackend.repository.model

import jakarta.persistence.*

@Entity(name = "sensor_data")
class SensorDataEntity (
        @Id
        var id: String,
        @Column(name = "sensor_name")
        var sensorName: String,
        @Column(name = "sensor_value")
        var value: Double,
        @Enumerated(EnumType.STRING)
        var unit: Unit
)