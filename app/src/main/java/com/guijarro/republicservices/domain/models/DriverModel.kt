package com.guijarro.republicservices.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers_table")
data class DriverModel(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String
)
