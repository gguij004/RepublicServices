package com.guijarro.republicservices.domain.usecases

import com.guijarro.republicservices.domain.models.RouteModel
import com.guijarro.republicservices.domain.repository.LocalDriversAndRoutsRepository
import com.guijarro.republicservices.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocalDriversAndRoutesUseCase @Inject constructor(
    private val repository: LocalDriversAndRoutsRepository
) {

    operator fun invoke(driverId: String): Flow<UIState<RouteModel?>> = flow {
        emit(UIState.Loading)

        try {
            val routes = repository.getRoutes()
            emit(UIState.Success(routeCorrectFilter(routes, driverId.toInt())))
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }

    private fun routeCorrectFilter(routes: List<RouteModel>, driverId: Int): RouteModel? {
        val sameId = routes.firstOrNull { it.id == driverId }
        return when {
            sameId != null -> sameId
            driverId % 2 == 0 -> routes.firstOrNull { it.type == "R" }
            driverId % 5 == 0 -> routes.filter { it.type == "C" }.getOrNull(1)
            else -> routes.lastOrNull { it.type == "I" }
        }
    }
}