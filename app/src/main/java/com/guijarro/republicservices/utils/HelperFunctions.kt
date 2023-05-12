package com.guijarro.republicservices.utils

import com.guijarro.republicservices.data.remote.api_dto.Driver
import com.guijarro.republicservices.data.remote.api_dto.Route
import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel

fun List<Driver>?.mapToDriverModel(): List<DriverModel> {
    return this?.map {
        val name = it.name?.split(" ") ?: emptyList()
        DriverModel(
            id = it.id ?: "",
            firstName = if (name.isNotEmpty()) name[0] else "",
            lastName = if (name.isNotEmpty() && name.size > 1) name[1] else ""
        )
    } ?: emptyList()
}

fun List<Route>?.mapToRouteModel(): List<RouteModel> {
    return this?.map {
        RouteModel(
            id = it.id ?: -1,
            name = it.name ?: "",
            type = it.type ?: ""
        )
    } ?: emptyList()
}