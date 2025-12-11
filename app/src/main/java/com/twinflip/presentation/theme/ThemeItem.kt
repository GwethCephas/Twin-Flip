package com.twinflip.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.twinflip.domain.model.Theme

@Composable
fun ThemeItem(
    modifier: Modifier = Modifier,
    theme: Theme,
    onThemeClick: () -> Unit
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    Column(
        modifier = modifier
            .padding(10.dp)
            .clickable { onThemeClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(8.dp),
            model = "file:///android_asset/${theme.images.first().imagePath}",
            imageLoader = imageLoader,
            contentDescription = theme.themeName
        )
        Text(
            text = theme.themeName,
            maxLines = 1,
            fontWeight = SemiBold
        )
    }

}