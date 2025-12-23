package com.twinflip.feature_multiplayer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.twinflip.R
import com.twinflip.feature_multiplayer.model.Player

@Composable
fun PlayerCardItem(
    modifier: Modifier = Modifier,
    player: Player
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = if (player.isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "Player icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(50.dp)
            )
        }
        Text(
            text = player.name,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.SemiBold
        )
    }

}
