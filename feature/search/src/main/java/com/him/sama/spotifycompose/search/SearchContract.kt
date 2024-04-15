package com.him.sama.spotifycompose.search

import androidx.compose.runtime.Immutable
import com.him.sama.spotifycompose.common.core.base.MviIntent
import com.him.sama.spotifycompose.common.core.base.MviSingleEvent
import com.him.sama.spotifycompose.common.core.base.MviViewState
import com.him.sama.spotifycompose.common.core.domain.model.SearchPageDomainModel
import com.him.sama.spotifycompose.common.core.domain.model.UserError


data class SearchPageModel(
    val categories: List<CategoryModel> = listOf(),
    val story: List<StoryItemModel> = listOf()
) {
    constructor(data: SearchPageDomainModel) : this(
        categories = data.categories.map {
            CategoryModel(
                bgColor = it.bgColor,
                image = it.image,
                title = it.title
            )
        },
        story = data.story.map {
            StoryItemModel(
                image = it.image,
                title = it.title
            )
        }
    )
}

data class StoryItemModel(
    val image: String,
    val title: String
)

data class CategoryModel(
    val bgColor: String,
    val image: String,
    val title: String
)

@Immutable
sealed interface SearchViewIntent : MviIntent {
    data object Initial : SearchViewIntent
    data object Refresh : SearchViewIntent
    data object Retry : SearchViewIntent
}

@Immutable
data class SearchViewState(
    val data: SearchPageModel = SearchPageModel(),
    val isLoading: Boolean,
    val error: UserError?,
    val isRefreshing: Boolean
) : MviViewState {
    companion object {
        fun initial() = SearchViewState(
            data = SearchPageModel(),
            isLoading = true,
            error = null,
            isRefreshing = false
        )
    }
}

internal sealed interface PartialStateChange {
    fun reduce(searchViewState: SearchViewState): SearchViewState

    sealed interface Search : PartialStateChange {
        override fun reduce(searchViewState: SearchViewState): SearchViewState {
            return when (this) {
                Loading -> searchViewState.copy(
                    isLoading = true,
                    error = null
                )

                is Data -> searchViewState.copy(
                    isLoading = false,
                    error = null,
                    data = data
                )

                is Error -> searchViewState.copy(
                    isLoading = false,
                    error = error
                )
            }
        }

        data object Loading : Search
        data class Data(val data: SearchPageModel) : Search
        data class Error(val error: UserError) : Search
    }

    sealed interface Refresh : PartialStateChange {
        override fun reduce(searchViewState: SearchViewState): SearchViewState {
            return when (this) {
                is Success -> searchViewState.copy(isRefreshing = false)
                is Failure -> searchViewState.copy(isRefreshing = false)
                Loading -> searchViewState.copy(isRefreshing = true)
            }
        }

        data object Loading : Refresh
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }
}

sealed interface SearchSingleEvent : MviSingleEvent {
    sealed interface Refresh : SearchSingleEvent {
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }

    data class GetUsersError(val error: UserError) : SearchSingleEvent
}
