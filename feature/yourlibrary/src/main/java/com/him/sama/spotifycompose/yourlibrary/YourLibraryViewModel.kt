package com.him.sama.spotifycompose.yourlibrary

import androidx.lifecycle.viewModelScope
import com.him.sama.spotifycompose.common.core.base.AbstractMviViewModel
import com.him.sama.spotifycompose.common.core.domain.usecase.GetYourLibraryUseCase
import com.him.sama.spotifycompose.yourlibrary.PartialStateChange.Refresh.Failure
import com.him.sama.spotifycompose.yourlibrary.PartialStateChange.Refresh.Loading
import com.him.sama.spotifycompose.yourlibrary.PartialStateChange.YourLibrary
import com.him.sama.spotifycompose.yourlibrary.PartialStateChange.YourLibrary.Data
import com.him.sama.spotifycompose.yourlibrary.YourLibrarySingleEvent.GetUsersError
import com.him.sama.spotifycompose.yourlibrary.YourLibraryViewIntent.Initial
import com.him.sama.spotifycompose.yourlibrary.YourLibraryViewIntent.Retry
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
class YourLibraryViewModel @Inject constructor(
    private val getYourLibraryUseCase: GetYourLibraryUseCase
) : AbstractMviViewModel<YourLibraryViewIntent, YourLibraryViewState, YourLibrarySingleEvent>() {

    override val viewState: StateFlow<YourLibraryViewState>

    init {
        val initialVS = YourLibraryViewState.initial()

        viewState = merge(
            intentSharedFlow.filterIsInstance<Initial>().take(1),
            intentSharedFlow.filterNot { it is Initial }
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
                PartialStateChange.Refresh.Success -> YourLibrarySingleEvent.Refresh.Success
                is YourLibrary.Error -> GetUsersError(change.error)
                is Failure -> YourLibrarySingleEvent.Refresh.Failure(change.error)
                is Data -> return@onEach
                Loading -> return@onEach
                YourLibrary.Loading -> return@onEach
            }
            sendEvent(event)
        }
    }

    private fun SharedFlow<YourLibraryViewIntent>.toPartialStateChangeFlow(): Flow<PartialStateChange> {
        val userChanges = defer(getYourLibraryUseCase::invoke)
            .onEach { either -> Timber.tag(logTag).d("Emit your library $either}") }
            .map { result ->
                result.fold(
                    ifLeft = { YourLibrary.Error(it) },
                    ifRight = { list ->
                        val playList = list.first()
                        Data(YourLibraryModel(playList))
                    }
                )
            }
            .startWith(YourLibrary.Loading)

        return merge(
            merge(
                filterIsInstance<Initial>(),
                filterIsInstance<Retry>()
                    .filter { viewState.value.error != null }
            ).flatMapLatest {
                userChanges
            }
        )
    }
}