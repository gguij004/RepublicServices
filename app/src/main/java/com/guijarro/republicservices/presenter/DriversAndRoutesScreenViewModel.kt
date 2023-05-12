package com.guijarro.republicservices.presenter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guijarro.republicservices.domain.models.DriverModel
import com.guijarro.republicservices.domain.models.RouteModel
import com.guijarro.republicservices.domain.usecases.GetDriversAndRoutsUseCase
import com.guijarro.republicservices.domain.usecases.GetLocalDriversAndRoutesUseCase
import com.guijarro.republicservices.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriversAndRoutesScreenViewModel @Inject constructor(
    private val getDriversAndRoutsUseCase: GetDriversAndRoutsUseCase,
    private val getLocalDriversAndRoutesUseCase: GetLocalDriversAndRoutesUseCase,

    ) : ViewModel() {

    var yourDriver: DriverModel? = null

    private val _drivers: MutableState<UIState<List<DriverModel>>> = mutableStateOf(UIState.Loading)
    val drivers: State<UIState<List<DriverModel>>> = _drivers

    private val _route: MutableState<UIState<RouteModel?>> = mutableStateOf(UIState.Loading)
    val route: State<UIState<RouteModel?>> = _route

    private var unsortedDrivers: List<DriverModel> = emptyList()

    init {
        getDriversAndRoutes()
    }

    fun sortDriversByLastName() {
        if (unsortedDrivers.isNotEmpty()) {
            val sorted = unsortedDrivers.sortedBy { it.lastName }

            _drivers.value = UIState.Success(sorted)
        }
    }

    fun getRoute() {
        yourDriver?.let {
            getLocalDriversAndRoutesUseCase(it.id).onEach { state ->
                _route.value = state
            }.launchIn(viewModelScope)
        } ?: let {
            _route.value = UIState.Error(Exception("no driver selected"))
        }
    }

    private fun getDriversAndRoutes() {
        getDriversAndRoutsUseCase().onEach { resource ->
            if (resource is UIState.Success) {
                unsortedDrivers = resource.data
            }
            _drivers.value = resource
        }.launchIn(viewModelScope)
    }

}