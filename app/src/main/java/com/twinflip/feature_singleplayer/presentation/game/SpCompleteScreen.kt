package com.twinflip.feature_singleplayer.presentation.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.twinflip.R
import com.twinflip.core.audio.SoundManager
import com.twinflip.core.ui.common.ConfettiEffect
import com.twinflip.core.ui.common.CustomButton
import com.twinflip.core.ui.common.CustomTextRow

@Composable
fun SpCompleteScreen(
    modifier: Modifier = Modifier,
    time: String,
    moves: Int,
    score: Int,
    onNavigateToThemeScreen: () -> Unit,
    onPlayAgainClick: () -> Unit,
    soundManager: SoundManager
) {

    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = if (score == 1000) "Perfect Score!" else "Level Complete!",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = SemiBold
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextRow(
                text = "Time : ",
                textResult = time
            )
            CustomTextRow(
                text = "Moves :  ",
                textResult = moves.toString()
            )
            CustomTextRow(
                text = "Score : ",
                textResult = score.toString()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(R.drawable.fantasy_crown),
                contentDescription = "Fantasy Crown",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                modifier = modifier.fillMaxWidth(0.8f),
                text = "Play Again",
                onClick = {
                    onPlayAgainClick()
                },
                color = MaterialTheme.colorScheme.tertiary,
                soundManager = soundManager
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(
                modifier = modifier.fillMaxWidth(0.8f),
                text = "Change Theme",
                onClick = {
                    onNavigateToThemeScreen()
                },
                color = MaterialTheme.colorScheme.secondary,
                soundManager = soundManager
            )


        }
        ConfettiEffect(modifier = modifier)
    }
}