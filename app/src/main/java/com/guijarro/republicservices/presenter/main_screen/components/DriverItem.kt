package com.guijarro.republicservices.presenter.main_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DriverItem(
    modifier: Modifier = Modifier,
    name: String,
    id: String,
) {
    
    Box(
        modifier = modifier
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(end = 32.dp)
        ) {
            Text(
                text = "ID: $id, "
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Name: $name"
            )
        }
    }
}