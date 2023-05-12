package com.guijarro.republicservices.data.remote.repository_impl

import com.guijarro.republicservices.data.remote.api.DrivesAndRoutesApi
import com.guijarro.republicservices.data.remote.api_dto.DriversAndRoutesResponseDto
import com.guijarro.republicservices.domain.repository.RemoteDrivesAndRoutRepository
import retrofit2.Response
import javax.inject.Inject

class RemoteDrivesAndRoutesRepositoryImpl @Inject constructor(
    private val api: DrivesAndRoutesApi
) : RemoteDrivesAndRoutRepository {
    override suspend fun getDriversAndRoutes(): Response<DriversAndRoutesResponseDto> {
        return api.getDriversAndRoutes()
    }
}