package com.guijarro.republicservices.data.remote.api_dto

import com.google.gson.annotations.SerializedName

data class DriversAndRoutesResponseDto(
    @SerializedName("drivers")
    val drivers: List<Driver>? = null,
    @SerializedName("routes")
    val routes: List<Route>? = null
)