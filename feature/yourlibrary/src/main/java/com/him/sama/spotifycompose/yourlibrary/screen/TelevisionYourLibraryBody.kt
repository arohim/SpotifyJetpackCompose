import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.R
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color

@Composable
fun TelevisionYourLibraryBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .graphicsLayer(clip = false)
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = "Playlist",
            style = MaterialTheme.typography.titleSmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = "Liked Songs",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false)
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                TelevisionCategoryItem()
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = "Albums",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false)
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                TelevisionCategoryItem()
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(horizontal = 48.dp),
            text = "Albums 2",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(clip = false)
                .padding(horizontal = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) {
                TelevisionCategoryItem()
            }
        }
    }
}


@Composable
private fun TelevisionCategoryItem() {
    Box(
        modifier = Modifier
            .size(140.dp)
            .clickable(onClick = {})
            .background(Color.Black)
            .onFocusChanged {
            }
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.baseline_grid_view_24),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@TelevisionPreview
@Composable
private fun PreviewBody() {
    AppTheme {
        TelevisionYourLibraryBody()
    }
}