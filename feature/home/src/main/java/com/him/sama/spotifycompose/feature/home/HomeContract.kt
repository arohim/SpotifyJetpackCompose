package com.him.sama.spotifycompose.feature.home

import androidx.compose.runtime.Immutable
import com.him.sama.spotifycompose.common.core.base.MviIntent
import com.him.sama.spotifycompose.common.core.base.MviSingleEvent
import com.him.sama.spotifycompose.common.core.base.MviViewState
import com.him.sama.spotifycompose.common.core.domain.model.HomeDomainItem
import com.him.sama.spotifycompose.common.core.domain.model.UserError
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList


enum class HomeLayoutType {
    GRID,
    ALBUM,
    PLAY_WIDGET
}

data class HomeItem(
    val layoutType: HomeLayoutType,
    val title: String,
    val items: List<HomeDetailItem> = listOf()
) {
    constructor(domain: HomeDomainItem) : this(
        layoutType = HomeLayoutType.valueOf(domain.layoutType.name),
        title = domain.title,
        items = domain.items.map {
            HomeDetailItem(
                image = it.image,
                title = it.title,
                categoryHierarchy = it.categoryHierarchy,
                categoryName = it.categoryName
            )
        }
    )
}

data class HomeDetailItem(
    val image: String,
    val title: String,
    val categoryHierarchy: String,
    val categoryName: String
)

@Immutable
sealed interface HomeViewIntent : MviIntent {
    data object Initial : HomeViewIntent
    data object Refresh : HomeViewIntent
    data object Retry : HomeViewIntent
}

@Immutable
data class HomeViewState(
    val homeItems: PersistentList<HomeItem>,
    val isLoading: Boolean,
    val error: UserError?,
    val isRefreshing: Boolean
) : MviViewState {
    companion object {
        fun initial() = HomeViewState(
            homeItems = persistentListOf(),
            isLoading = true,
            error = null,
            isRefreshing = false
        )
    }
}

internal sealed interface PartialStateChange {
    fun reduce(homeViewState: HomeViewState): HomeViewState

    sealed interface Homes : PartialStateChange {
        override fun reduce(homeViewState: HomeViewState): HomeViewState {
            return when (this) {
                Loading -> homeViewState.copy(
                    isLoading = true,
                    error = null
                )

                is Data -> homeViewState.copy(
                    isLoading = false,
                    error = null,
                    homeItems = homes.toPersistentList()
                )

                is Error -> homeViewState.copy(
                    isLoading = false,
                    error = error
                )
            }
        }

        data object Loading : Homes
        data class Data(val homes: List<HomeItem>) : Homes
        data class Error(val error: UserError) : Homes
    }

    sealed interface Refresh : PartialStateChange {
        override fun reduce(homeViewState: HomeViewState): HomeViewState {
            return when (this) {
                is Success -> homeViewState.copy(isRefreshing = false)
                is Failure -> homeViewState.copy(isRefreshing = false)
                Loading -> homeViewState.copy(isRefreshing = true)
            }
        }

        data object Loading : Refresh
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }
}

sealed interface HomeSingleEvent : MviSingleEvent {
    sealed interface Refresh : HomeSingleEvent {
        data object Success : Refresh
        data class Failure(val error: UserError) : Refresh
    }

    data class GetUsersError(val error: UserError) : HomeSingleEvent
}
