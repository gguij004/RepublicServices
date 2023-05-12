package com.guijarro.republicservices.presenter.routes_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guijarro.republicservices.presenter.DriversAndRoutesScreenViewModel
import com.guijarro.republicservices.presenter.routes_screen.components.RouteItem
import com.guijarro.republicservices.utils.UIState
import com.guijarro.republicservices.utils.appBarElevation
import com.guijarro.republicservices.utils.horizontalPadding

@Composable
fun RoutesScreen(
    viewModel: DriversAndRoutesScreenViewModel,
) {
    viewModel.getRoute()
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = appBarElevation.dp
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = horizontalPadding.dp),
                    text = "Routes"
                )
            }
        },
        content = { padding ->

            Column(modifier = Modifier
                .padding(padding)
                .padding(horizontalPadding.dp)) {
                when (val state = viewModel.route.value) {
                    is UIState.Loading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    is UIState.Success -> state.data?.let { RouteItem(it) }
                    is UIState.Error -> Text(text = "error")

                }
            }
        }
    )
}