package com.flockinger.simplebackend.service

import com.flockinger.simplebackend.api.AddSensorDataRequest
import com.flockinger.simplebackend.api.StoredSensorDataResponse
import com.flockinger.simplebackend.repository.SensorDataRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SensorDataService(
        private val repository: SensorDataRepository
) {
    @Transactional
    fun addSensorData(sensorDataRequest: AddSensorDataRequest): StoredSensorDataResponse {
        val storedSensorData = repository.save(sensorDataRequest.toEntity())
        return with(storedSensorData) {
            StoredSensorDataResponse(
                    id, sensorName, value, unit
            )
        }
    }
}