package com.twinflip.feature_themes.presentation

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
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.twinflip.R
import com.twinflip.core.domain.model.Theme

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
            .clickable {
                if (theme.isUnLocked) {
                    onThemeClick()
                }

            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!theme.isUnLocked) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(R.drawable.padlock),
                contentDescription = "Lock Icon"
            )
            Text(
                text = "Locked",
                fontWeight = SemiBold
            )

        } else {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp),
                model = "file:///android_asset/${theme.images.first().imagePath}",
                imageLoader = imageLoader,
                contentDescription = theme.themeName
            )
            Text(
                text = theme.themeName,
                fontWeight = SemiBold
            )
        }


    }

}