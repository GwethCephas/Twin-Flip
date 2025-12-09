package com.twinflip.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.twinflip.R
import com.twinflip.presentation.common.CustomButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToThemeScreen: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.twin_flip_image),
            contentDescription = "Twin Flip Logo"
        )
        CustomButton(
            text = "Single Player",
            color = MaterialTheme.colorScheme.primary,
            onClick = {
                onNavigateToThemeScreen()
            }
        )
        CustomButton(
            text = "Two Players",
            color = MaterialTheme.colorScheme.primary,
            onClick = {
            }
        )

        CustomButton(
            text = "QuickPlay",
            color = MaterialTheme.colorScheme.surfaceContainer,
            onClick = {
                onNavigateToThemeScreen()
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onNavigateToThemeScreen = {}
    )
}


















