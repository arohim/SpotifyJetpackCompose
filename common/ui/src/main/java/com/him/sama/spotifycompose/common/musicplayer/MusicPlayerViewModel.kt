package com.him.sama.spotifycompose.common.musicplayer

import androidx.lifecycle.viewModelScope
import com.him.sama.spotifycompose.common.core.base.AbstractMviViewModel
import com.hoc081098.flowext.startWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
) : AbstractMviViewModel<MusicPlayerViewIntent, MusicPlayerViewState, MusicPlayerSingleEvent>() {

    override val viewState: StateFlow<MusicPlayerViewState>

    private val mockMusicPlayer = listOf(
        MusicItem(
            image = "https://i.scdn.co/image/ab67706f00000002c1811d50fbd4df02ea3b06a3",
            title = "Peaceful Guitar",
            description = "Music"
        ),
        MusicItem(
            image = "https://i.scdn.co/image/ab67706f0000000231ca8abcbff3f5a3eb1fd339",
            title = "Stress Relief",
            description = "Music"
        ),
        MusicItem(
            image = "https://i.scdn.co/image/ab67706f000000020179e669c5f45b51def86cf8",
            title = "Running Music 2024 Workout",
            description = "Music"
        ),
        MusicItem(
            image = "https://image-cdn-ak.spotifycdn.com/image/ab67706c0000da845ccd0896ef554c0a25d4c2a4",
            title = "Workout Beats",
            description = "Music"
        )
    )

    init {
        val initialVS = MusicPlayerViewState(
            items = mockMusicPlayer,
            selectedItem = mockMusicPlayer.first(),
            isLoading = false,
            error = null
        )

        viewState = merge(
            intentSharedFlow.filterIsInstance<MusicPlayerViewIntent.Initial>().take(1),
            intentSharedFlow.filterNot { it is MusicPlayerViewIntent.Initial }
        )
            .shareWhileSubscribed()
            .toPartialStateChangeFlow()
            .debugLog("PartialStateChange")
            .sendSingleEvent()
            .scan(initialVS) { vs, change -> change.reduce(vs) }
            .debugLog("ViewState")
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                initialVS
            )

    }

    private fun Flow<PartialStateChange>.sendSingleEvent(): Flow<PartialStateChange> {
        return onEach { change ->
            val event = when (change) {
                is PartialStateChange.Pause.Data -> return@onEach
                is PartialStateChange.Pause.Error -> return@onEach
                PartialStateChange.Pause.Loading -> return@onEach
            }
            sendEvent(event)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun SharedFlow<MusicPlayerViewIntent>.toPartialStateChangeFlow(): Flow<PartialStateChange> {
        val changes = flow { emit(mockMusicPlayer) }
            .onEach { either -> Timber.tag(logTag).d("Emit items.size=${either.size}") }
            .map {
                PartialStateChange.Pause.Data(
                    items = it,
                    selectedItem = it.first()
                )
            }
            .startWith(PartialStateChange.Pause.Loading)

        return merge(
            merge(
                filterIsInstance<MusicPlayerViewIntent.Initial>(),
            ).flatMapLatest {
                changes
            }
        )
    }
}