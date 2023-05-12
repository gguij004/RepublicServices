package com.guijarro.republicservices.data.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel

@Database(
    entities = [DriverModel::class,RouteModel::class],
    version = 1
)
abstract class DriversAndRoutesDatabase: RoomDatabase() {

    abstract val driversAndRoutesDao: DriversAndRoutesDao

    companion object{
        const val DATABASE_NAME = "republic_services_db"
    }

}