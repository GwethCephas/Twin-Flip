package com.twinflip.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.twinflip.core.audio.GameSound
import com.twinflip.core.audio.SoundManager

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color,
    soundManager: SoundManager
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .shadow(elevation = 5.dp)
            .background(color)
            .clickable {
                soundManager.playSound(GameSound.BUTTON_TAP)
                onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                    horizontal = 30.dp
                ),
            text = text,
            fontSize = 20.sp,
            fontWeight = SemiBold,
            color =  MaterialTheme.colorScheme.surface
        )
    }
    Spacer(modifier = modifier.height(10.dp))
}