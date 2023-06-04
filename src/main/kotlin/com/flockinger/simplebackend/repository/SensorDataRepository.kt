package com.flockinger.simplebackend.repository

import com.flockinger.simplebackend.repository.model.SensorDataEntity
import org.springframework.data.repository.CrudRepository

interface SensorDataRepository: CrudRepository<SensorDataEntity, String>