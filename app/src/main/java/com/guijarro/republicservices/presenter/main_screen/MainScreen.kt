package com.guijarro.republicservices.presenter.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.guijarro.republicservices.presenter.PresenterScreenList
import com.guijarro.republicservices.presenter.DriversAndRoutesScreenViewModel
import com.guijarro.republicservices.presenter.main_screen.components.DriverItem
import com.guijarro.republicservices.utils.UIState
import com.guijarro.republicservices.utils.appBarElevation
import com.guijarro.republicservices.utils.horizontalPadding

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: DriversAndRoutesScreenViewModel,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = appBarElevation.dp
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = horizontalPadding.dp),
                    text = "Drivers"
                )
            }
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontalPadding.dp)
            ) {
                Button(
                    modifier = Modifier
                        .align(alignment = Alignment.End),
                    onClick = { viewModel.sortDriversByLastName() }) {
                    Text(text = "SORT ITEMS")
                }
                when (val state = viewModel.drivers.value) {
                    is UIState.Loading -> {
                        Box {
                            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                        }
                    }
                    is UIState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(state.data) { driver ->
                                DriverItem(
                                    name = "${driver.firstName} ${driver.lastName}",
                                    id = driver.id,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.yourDriver = driver
                                            navController.navigate(
                                                route = PresenterScreenList.RoutScreen.route
                                                        + "/${driver.id}"
                                            )
                                        }
                                )
                            }
                        }
                    }
                    is UIState.Error -> {
                        Text(text = "error")
                    }
                }
            }

        })
}

