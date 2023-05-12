package com.guijarro.republicservices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guijarro.republicservices.presenter.PresenterScreenList
import com.guijarro.republicservices.presenter.DriversAndRoutesScreenViewModel
import com.guijarro.republicservices.presenter.main_screen.MainScreen
import com.guijarro.republicservices.presenter.routes_screen.RoutesScreen
import com.guijarro.republicservices.ui.theme.RepublicServicesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServicesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    val mViewModel = hiltViewModel<DriversAndRoutesScreenViewModel>()

                    NavHost(
                        navController = navController,
                        startDestination = PresenterScreenList.MainScreen.route
                    ) {
                        composable(
                            route = PresenterScreenList.MainScreen.route
                        ) {
                            MainScreen(navController = navController, mViewModel)
                        }

                        composable(
                            route = PresenterScreenList.RoutScreen.route + "/{Id}"
                        ) {
                            RoutesScreen(mViewModel)
                        }
                    }

                }
            }
        }
    }
}

