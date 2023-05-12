package com.guijarro.republicservices.domain.repository

import com.guijarro.republicservices.data.remote.api_dto.DriversAndRoutesResponseDto
import retrofit2.Response

interface RemoteDrivesAndRoutRepository {

    suspend fun getDriversAndRoutes(): Response<DriversAndRoutesResponseDto>
}