package com.guijarro.republicservices.data.remote.api_dto

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String? = null
)