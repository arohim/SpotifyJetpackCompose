package com.him.sama.spotifycompose.common.musicplayer

import androidx.compose.runtime.Immutable
import com.him.sama.spotifycompose.common.core.base.MviIntent
import com.him.sama.spotifycompose.common.core.base.MviSingleEvent
import com.him.sama.spotifycompose.common.core.base.MviViewState
import com.him.sama.spotifycompose.common.core.domain.model.UserError


data class MusicItem(
    val image: String,
    val title: String,
    val description: String? = null
)

@Immutable
sealed interface MusicPlayerViewIntent : MviIntent {
    data object Initial : MusicPlayerViewIntent
    data object Refresh : MusicPlayerViewIntent
}

@Immutable
data class MusicPlayerViewState(
    val items: List<MusicItem> = listOf(),
    val selectedItem: MusicItem? = null,
    val isLoading: Boolean,
    val error: UserError?
) : MviViewState {
    companion object {
        fun initial() = MusicPlayerViewState(
            items = listOf(),
            isLoading = true,
            error = null
        )
    }
}

internal sealed interface PartialStateChange {
    fun reduce(musicPlayerViewState: MusicPlayerViewState): MusicPlayerViewState

    sealed interface Pause : PartialStateChange {
        override fun reduce(musicPlayerViewState: MusicPlayerViewState): MusicPlayerViewState {
            return when (this) {
                Loading -> musicPlayerViewState.copy(
                    isLoading = true,
                    error = null
                )

                is Data -> musicPlayerViewState.copy(
                    isLoading = false,
                    error = null,
                    items = items
                )

                is Error -> musicPlayerViewState.copy(
                    isLoading = false,
                    error = error
                )
            }
        }

        data object Loading : Pause
        data class Data(
            val items: List<MusicItem>,
            val selectedItem: MusicItem? = null
        ) : Pause

        data class Error(val error: UserError) : Pause
    }
}

sealed interface MusicPlayerSingleEvent : MviSingleEvent {

    sealed interface Play : MusicPlayerSingleEvent {
        data object Success : Play
        data class Failure(val error: UserError) : Play
    }

    sealed interface Pause : MusicPlayerSingleEvent {
        data object Success : Play
        data class Failure(val error: UserError) : Pause
    }

    sealed interface Next : MusicPlayerSingleEvent {
        data object Success : Play
        data class Failure(val error: UserError) : Next
    }

    sealed interface Previous : MusicPlayerSingleEvent {
        data object Success : Play
        data class Failure(val error: UserError) : Previous
    }

    data class GetUsersError(val error: UserError) : MusicPlayerSingleEvent
}
