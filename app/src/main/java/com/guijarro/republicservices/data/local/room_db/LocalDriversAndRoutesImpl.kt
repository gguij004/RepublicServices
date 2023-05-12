package com.guijarro.republicservices.data.local.room_db

import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel
import com.guijarro.republicservices.domain.repository.LocalDriversAndRoutsRepository

class LocalDriversAndRoutesImpl(
    private val dao: DriversAndRoutesDao
) : LocalDriversAndRoutsRepository {
    override suspend fun getDrivers(): List<DriverModel> {
        return dao.getDrivers()
    }

    override suspend fun getRoutes(): List<RouteModel> {
        return dao.getRoutes()
    }

    override suspend fun insertDriver(driver: DriverModel) {
        dao.insertDriver(driver)
    }

    override suspend fun insertRoute(route: RouteModel) {
        dao.insertRoute(route)
    }
}