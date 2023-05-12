package com.guijarro.republicservices.domain.usecases

import com.guijarro.republicservices.data.remote.api_dto.DriversAndRoutesResponseDto
import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.repository.LocalDriversAndRoutsRepository
import com.guijarro.republicservices.domain.repository.RemoteDrivesAndRoutRepository
import com.guijarro.republicservices.utils.UIState
import com.guijarro.republicservices.utils.mapToDriverModel
import com.guijarro.republicservices.utils.mapToRouteModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDriversAndRoutsUseCase @Inject constructor(
    private val repository: RemoteDrivesAndRoutRepository,
    private val localDriversAndRoutsRepository: LocalDriversAndRoutsRepository,
    private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<UIState<List<DriverModel>>> = flow {
        emit(UIState.Loading)

        try {
            val response = repository.getDriversAndRoutes()
            if (response.isSuccessful) {
                response.body()?.let {
                    it.drivers.mapToDriverModel().map {driver ->
                        localDriversAndRoutsRepository.insertDriver(driver)
                    }
                    it.routes.mapToRouteModel().map { route ->
                        localDriversAndRoutsRepository.insertRoute(route)
                    }

                    emit(UIState.Success(localDriversAndRoutsRepository.getDrivers()))
                } ?: throw Exception("Response is null")

            } else {
                throw Exception(response.errorBody()?.string())
            }

        } catch (e: Exception) {
            val localDrivers = localDriversAndRoutsRepository.getDrivers()
            if (localDrivers.isNotEmpty()) {
                emit(UIState.Success(localDrivers))
            } else {
                emit(UIState.Error(e))
            }
        }


    }.flowOn(dispatcher)
}

