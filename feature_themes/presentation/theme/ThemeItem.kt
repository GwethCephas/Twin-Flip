package com.twinflip.feature_themes.presentation.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.twinflip.R
import com.twinflip.core.domain.model.Theme

@Composable
fun ThemeItem(
    theme: Theme,
    onThemeClick: () -> Unit
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    // Let ThemeItem define its own size and modifier, not accept one from the parent.
    Column(
        modifier = Modifier
            .size(width = 100.dp, height = 120.dp) // Define a fixed size
            .clickable {
                if (theme.isUnLocked) {
                    onThemeClick()
                }
            }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!theme.isUnLocked) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(R.drawable.padlock),
                contentDescription = "Lock Icon"
            )
            Text(
                text = "Locked",
                fontWeight = FontWeight.SemiBold
            )

        } else {
            AsyncImage(
                modifier = Modifier.size(64.dp), // Give image a specific size
                model = "file:///android_asset/${theme.images.first().imagePath}",
                imageLoader = imageLoader,
                contentDescription = theme.themeName
            )
            Text(
                text = theme.themeName,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}