package com.twinflip.feature_multiplayer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            .width(200.dp)
            .height(100.dp)
            .padding(horizontal = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 4.dp,
                    color = if (player.isActive) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Player icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                )

                Text(
                    text = player.name,
                    color =  MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }

            VerticalDivider(modifier = Modifier.width(1.dp).padding(vertical = 20.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Score : ${player.score}",
                    color =  MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Matched pairs : ${player.matchedPairs}",
                    color =  MaterialTheme.colorScheme.onBackground,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PlayerCardItemPreview() {
    PlayerCardItem(
        player = Player(
            name = "Player 1",
            isActive = true
        )
    )
}