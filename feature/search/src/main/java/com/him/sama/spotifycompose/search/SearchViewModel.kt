package com.him.sama.spotifycompose.search

import androidx.lifecycle.viewModelScope
import com.him.sama.spotifycompose.common.core.base.AbstractMviViewModel
import com.him.sama.spotifycompose.common.core.domain.usecase.GetSearchPageUseCase
import com.him.sama.spotifycompose.search.PartialStateChange.Refresh
import com.him.sama.spotifycompose.search.PartialStateChange.Search
import com.him.sama.spotifycompose.search.SearchSingleEvent.GetUsersError
import com.him.sama.spotifycompose.search.SearchSingleEvent.Refresh.Failure
import com.him.sama.spotifycompose.search.SearchSingleEvent.Refresh.Success
import com.hoc081098.flowext.defer
import com.hoc081098.flowext.startWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchPageUseCase: GetSearchPageUseCase
) : AbstractMviViewModel<SearchViewIntent, SearchViewState, SearchSingleEvent>() {

    override val viewState: StateFlow<SearchViewState>

    init {
        val initialVS = SearchViewState.initial()

        viewState = merge(
            intentSharedFlow.filterIsInstance<SearchViewIntent.Initial>().take(1),
            intentSharedFlow.filterNot { it is SearchViewIntent.Initial }
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
                Refresh.Success -> Success
                is Search.Error -> GetUsersError(change.error)
                is Refresh.Failure -> Failure(change.error)
                is Search.Data -> return@onEach
                Search.Loading -> return@onEach
                Refresh.Loading -> return@onEach
            }
            sendEvent(event)
        }
    }

    private fun SharedFlow<SearchViewIntent>.toPartialStateChangeFlow(): Flow<PartialStateChange> {
        val userChanges = defer(getSearchPageUseCase::invoke)
            .onEach { either -> Timber.tag(logTag).d("Emit search $either}") }
            .map { result ->
                result.fold(
                    ifLeft = { Search.Error(it) },
                    ifRight = { Search.Data(SearchPageModel(it)) }
                )
            }
            .startWith(Search.Loading)

        return merge(
            merge(
                filterIsInstance<SearchViewIntent.Initial>(),
                filterIsInstance<SearchViewIntent.Retry>()
                    .filter { viewState.value.error != null }
            ).flatMapLatest {
                userChanges
            }
        )
    }
}