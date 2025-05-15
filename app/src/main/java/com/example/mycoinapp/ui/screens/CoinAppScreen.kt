package com.example.mycoinapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.model.GlideUrl
import com.example.mycoinapp.R
import com.example.mycoinapp.data.model.Album
import com.example.mycoinapp.ui.viewModel.AlbumUiState


@Composable
fun CoinAppScreen(
    albumUiState: AlbumUiState, modifier: Modifier = Modifier
) {
    when (albumUiState) {
        is AlbumUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AlbumUiState.Success -> ResultScreen(
            albumUiState.albuns,
            modifier = modifier.fillMaxWidth()
        )
        is AlbumUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}


/**
 * ResultScreen displaying albuns retrieved.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ResultScreen(albuns: List<Album>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(albuns.size) { index ->
            Row(
                modifier =
                    Modifier.padding(all = 8.dp)
            ) {
                val token = "User-Agent"
                val url = albuns[index].url
                val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", "Bearer $token")) }

                GlideImage(
                    model = glideUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RectangleShape)
                        .border(1.0.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = albuns[index].id.toString(),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = albuns[index].title,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

