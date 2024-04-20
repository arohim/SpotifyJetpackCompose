import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.him.sama.spotifycompose.common.ui.preview.TelevisionPreview
import com.him.sama.spotifycompose.common.ui.theme.AppTheme
import com.him.sama.spotifycompose.common.ui.theme.background_color
import com.him.sama.spotifycompose.yourlibrary.YourLibraryItemModel
import com.him.sama.spotifycompose.yourlibrary.YourLibraryViewState

@Composable
fun TelevisionYourLibraryBody(viewState: YourLibraryViewState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
            .graphicsLayer(clip = false)
            .padding(top = 48.dp)
            .verticalScroll(rememberScrollState())
    ) {
        viewState.data.forEach {
            Text(
                modifier = Modifier.padding(horizontal = 48.dp),
                text = it.title,
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
                items(it.items) {
                    TelevisionCategoryItem(it)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}


@Composable
private fun TelevisionCategoryItem(item: YourLibraryItemModel) {
    AsyncImage(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(4.dp)),
        contentDescription = null,
        model = item.image,
        contentScale = ContentScale.Crop
    )
}

@TelevisionPreview
@Composable
private fun PreviewBody() {
    AppTheme {
        TelevisionYourLibraryBody(YourLibraryViewState.initial())
    }
}