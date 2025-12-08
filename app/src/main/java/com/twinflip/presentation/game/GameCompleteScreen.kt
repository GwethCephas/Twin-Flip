package com.twinflip.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.twinflip.presentation.common.CustomButton
import com.twinflip.presentation.common.CustomTextRow

@Composable
fun GameCompleteScreen(
    modifier: Modifier = Modifier,
    time: String,
    moves: Int,
    score: Int,

    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Level \n Complete!",
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            textAlign = TextAlign.Center,
            color = Color.White,
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


        CustomButton(
            modifier = modifier.fillMaxWidth(0.75f),
            text = "Play Again",
            onClick = {

            },
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )


    }

}

@Preview(showBackground = true)
@Composable
fun GameCompleteScreenPreview() {
    GameCompleteScreen(
        time = "00.46",
        moves = 20,
        score = 800
    )
}