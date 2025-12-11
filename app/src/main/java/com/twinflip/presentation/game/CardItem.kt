package com.twinflip.presentation.game

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    gameCard: GameCard,
    onCardClick: () -> Unit,
    cardSize: Dp
) {
    val context = LocalContext.current

    val rotation = animateFloatAsState(
        targetValue = if (gameCard.isFlipped) 180f else 0f,
        animationSpec = tween(400),
        label = "",

        )

    Card(
        modifier = modifier
            .size(cardSize)
            .clickable(enabled = !gameCard.isFlipped) { onCardClick() }
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 12 * density
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp,
            pressedElevation = 10.dp
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        if (rotation.value <= 90f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .size(cardSize)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
            )
        } else {

            val imageLoader = ImageLoader.Builder(context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f

                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    model = ImageRequest.Builder(context)
                        .data("file:///android_asset/${gameCard.card.imagePath}")
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = gameCard.card.name

                )

                Log.d("CardItem", "Loading asset: ${gameCard.card.imagePath}")
            }
        }
    }
}