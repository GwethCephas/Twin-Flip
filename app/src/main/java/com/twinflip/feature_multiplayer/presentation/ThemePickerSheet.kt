package com.twinflip.feature_multiplayer.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.twinflip.feature_themes.presentation.theme.ThemeViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ThemePickerSheet(
    themeViewModel: ThemeViewModel,
    onNavigateToMultiplayer: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isVisible: Boolean
) {

    val state = themeViewModel.themeUiState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    val offsetY = remember { Animatable(0f) }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessVeryLow
            ),
            initialOffsetY = { fullHeight ->
                fullHeight
            }
        ) + fadeIn(animationSpec = tween(300)),
        exit = slideOutVertically() + fadeOut(animationSpec = tween(300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(0, offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            if (offsetY.value > 200f) {
                                onDismissRequest()
                            } else {
                                scope.launch {
                                    offsetY.animateTo(0f)
                                }
                            }
                        },
                        onVerticalDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                offsetY.snapTo(offsetY.value + dragAmount)

                            }

                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    initialOffsetY = { it }
                ) + fadeIn(animationSpec = tween(300)),
                exit = slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = "Choose a Theme",
                            style = MaterialTheme.typography.titleLarge
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(state.value.themes.size) { index ->
                                val currentTheme = state.value.themes[index]
                                MultiplayerThemeItem(
                                    theme = currentTheme,
                                    onThemeClick = {
                                        onNavigateToMultiplayer(currentTheme.themeName)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}