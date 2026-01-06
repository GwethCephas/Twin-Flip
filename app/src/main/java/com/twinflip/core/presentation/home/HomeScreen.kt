package com.twinflip.core.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.twinflip.R
import com.twinflip.core.presentation.common.CustomButton
import com.twinflip.feature_multiplayer.presentation.ThemePickerSheet
import com.twinflip.feature_themes.presentation.theme.ThemeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToThemeScreen: () -> Unit,
    onNavigateToMultiPlayerScreen: (String) -> Unit,
    themeViewModel: ThemeViewModel
) {

    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThemePickerSheet(
            themeViewModel = themeViewModel,
            isVisible = showBottomSheet,
            onNavigateToMultiplayer = { themeName ->
                scope.launch {
                    showBottomSheet = false
                    onNavigateToMultiPlayerScreen(themeName)
                }
            }
        )

        Image(
            painter = painterResource(id = R.drawable.twin_flip_mipmap_),
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
                showBottomSheet = true
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