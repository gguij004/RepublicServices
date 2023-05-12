package com.guijarro.republicservices.data.remote.api_dto

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)