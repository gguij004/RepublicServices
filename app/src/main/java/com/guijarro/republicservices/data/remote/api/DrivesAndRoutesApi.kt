package com.guijarro.republicservices.data.remote.api

import com.guijarro.republicservices.data.remote.api_dto.DriversAndRoutesResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Rest API: consume drivers and routs end point
 */
interface DrivesAndRoutesApi {
    @GET(END_POINT)
    suspend fun getDriversAndRoutes(): Response<DriversAndRoutesResponseDto>

    //https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/data
    companion object {
        const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"
        const val END_POINT = "data"
    }
}
