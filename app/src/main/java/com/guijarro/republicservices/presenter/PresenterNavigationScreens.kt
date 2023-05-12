package com.guijarro.republicservices.presenter

sealed class PresenterScreenList(val route: String) {
    object MainScreen : PresenterScreenList("main_screen")
    object RoutScreen : PresenterScreenList("route_screen")
}

