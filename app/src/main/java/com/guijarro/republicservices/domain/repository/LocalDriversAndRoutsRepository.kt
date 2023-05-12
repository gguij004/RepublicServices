package com.guijarro.republicservices.domain.repository

import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel

interface LocalDriversAndRoutsRepository {

    suspend fun getDrivers(): List<DriverModel>

    suspend fun getRoutes(): List<RouteModel>

    suspend fun insertDriver(driver: DriverModel)

    suspend fun insertRoute(route: RouteModel)
}