package com.him.sama.spotifycompose.yourlibrary

import androidx.compose.runtime.Immutable
import com.him.sama.spotifycompose.common.core.base.MviIntent
import com.him.sama.spotifycompose.common.core.base.MviSingleEvent
import com.him.sama.spotifycompose.common.core.base.MviViewState
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import com.him.sama.spotifycompose.common.core.domain.model.YourLibraryDomainModel


data class YourLibraryModel(
    val title: String = "",
    val items: List<YourLibraryItemModel> = listOf()
) {
    constructor(data: YourLibraryDomainModel) : this(
        title = data.title,
        items = data.items.map {
            YourLibraryItemModel(
                categoryHierarchy = it.categoryHierarchy,
                image = it.image,
                title = it.title
            )
        }
    )
}

data class YourLibraryItemModel(
    val categoryHierarchy: String,
    val image: String,
    val title: String
)

@Immutable
sealed interface YourLibraryViewIntent : MviIntent {
    data object Initial : YourLibraryViewIntent
    data object Refresh : YourLibraryViewIntent
    data object Retry : YourLibraryViewIntent
}

@Immutable
data class YourLibraryViewState(
    val data: List<YourLibraryModel> = listOf(),
    val isLoading: Boolean,
    val error: UserError?,
    val isRefreshing: Boolean
) : MviViewState {

    val fistPlayList: YourLibraryModel?
        get() = data.firstOrNull()

    companion object {
        fun initial() = YourLibraryViewState(
            data = listOf(),
            isLoading = true,
            error = null,
            isRefreshing = false
        )
    }
}

internal sealed interface PartialStateChange {
    fun reduce(viewState: YourLibraryViewState): YourLibraryViewState

    sealed interface YourLibrary : PartialStateChange {
        override fun reduce(viewState: YourLibraryViewState): YourLibraryViewState {
            return when (this) {
                Loading -> viewState.copy(
                    isLoading = true,
                    error = null
                )

                is Data -> viewState.copy(
                    isLoading = false,
                    error = null,
                    data = data
                )

                is Error -> viewState.copy(
                    isLoading = false,
                    error = error
                )
            }
        }

        data object Loading : YourLibrary
        data class Data(val data: List<YourLibraryModel>) : YourLibrary
        data class Error(val error: UserError) : YourLibrary
    }

    sealed interface Refresh : PartialStateChange {
        override fun reduce(viewState: YourLibraryViewState): YourLibraryViewState {
            return when (this) {
                is Success -> viewState.copy(isRefreshing = false)
                is Failure -> viewState.copy(isRefreshing = false)
                Loading -> viewState.copy(isRefreshing = true)
            }
        }

        data object Loading : Refresh
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }
}

sealed interface YourLibrarySingleEvent : MviSingleEvent {
    sealed interface Refresh : YourLibrarySingleEvent {
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }

    data class GetUsersError(val error: UserError) : YourLibrarySingleEvent
}
