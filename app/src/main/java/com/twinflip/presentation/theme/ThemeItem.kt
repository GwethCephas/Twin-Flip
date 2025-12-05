package com.twinflip.presentation.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import com.twinflip.domain.model.Theme

@Composable
fun ThemeItem(
    theme: Theme,
    onThemeClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(10.dp).clickable { onThemeClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id= theme.images.first().imageRes),
            contentDescription = theme.themeName
        )
        Text(
            text = theme.themeName,
            maxLines = 1,
            fontWeight = SemiBold
        )
    }

}