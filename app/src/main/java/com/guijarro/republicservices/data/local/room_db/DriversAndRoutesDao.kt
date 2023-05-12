package com.guijarro.republicservices.data.local.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel

@Dao
interface DriversAndRoutesDao {

    @Query("SELECT * FROM drivers_table")
    suspend fun getDrivers(): List<DriverModel>

    @Query("SELECT * FROM routes_table")
    suspend fun getRoutes(): List<RouteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DriverModel::class)
    suspend fun insertDriver(driver: DriverModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = RouteModel::class)
    suspend fun insertRoute(route: RouteModel)

}