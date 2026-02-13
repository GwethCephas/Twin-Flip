package com.twinflip.core.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

@Composable
fun ConfettiEffect(modifier: Modifier = Modifier) {
    KonfettiView(
        modifier = modifier.fillMaxSize(),
        parties = listOf(
            Party(
                speed = 12f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                size = listOf(Size.SMALL, Size.MEDIUM),
                timeToLive = 5000L,
                shapes = listOf(Shape.Square, Shape.Circle),
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                position = Position.Relative(0.5, 0.0),
                emitter = Emitter(duration = 500, TimeUnit.MILLISECONDS).max(500)
            )
        )
    )
}