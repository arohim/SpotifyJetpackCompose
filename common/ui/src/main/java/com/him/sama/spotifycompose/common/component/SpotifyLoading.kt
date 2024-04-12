import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.him.sama.spotifycompose.common.ui.theme.tertiary_background


@Composable
fun SpotifyCircleLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.width(52.dp),
        color = tertiary_background,
        trackColor = tertiary_background.copy(alpha = 0.5f),
    )
}