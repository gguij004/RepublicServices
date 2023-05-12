package com.guijarro.republicservices.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routes_table")
data class RouteModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String
)
