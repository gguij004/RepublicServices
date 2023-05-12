package com.guijarro.republicservices.presenter.routes_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.guijarro.republicservices.domain.models.RouteModel


@Composable
fun RouteItem(route: RouteModel) {

    Box {
        Column() {
            Text("ID: ${route.id} ")
            Text("Route type: ${route.type}")
            Text("Route name: ${route.name}")
        }
    }

}