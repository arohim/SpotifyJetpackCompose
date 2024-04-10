package com.him.sama.spotifycompose.feature.home

import androidx.lifecycle.viewModelScope
import com.him.sama.spotifycompose.common.core.base.AbstractMviViewModel
import com.him.sama.spotifycompose.common.core.domain.usecase.GetHomeDataUseCase
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
class HomeViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
) : AbstractMviViewModel<HomeViewIntent, HomeViewState, HomeSingleEvent>() {

    override val viewState: StateFlow<HomeViewState>

    init {
        val initialVS = HomeViewState.initial()

        viewState = merge(
            intentSharedFlow.filterIsInstance<HomeViewIntent.Initial>().take(1),
            intentSharedFlow.filterNot { it is HomeViewIntent.Initial }
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
                PartialStateChange.Refresh.Success -> HomeSingleEvent.Refresh.Success
                is PartialStateChange.Homes.Error -> HomeSingleEvent.GetUsersError(change.error)
                is PartialStateChange.Refresh.Failure -> HomeSingleEvent.Refresh.Failure(change.error)
                is PartialStateChange.Homes.Data -> return@onEach
                PartialStateChange.Homes.Loading -> return@onEach
                PartialStateChange.Refresh.Loading -> return@onEach
            }
            sendEvent(event)
        }
    }

    private fun SharedFlow<HomeViewIntent>.toPartialStateChangeFlow(): Flow<PartialStateChange> {
        val userChanges = defer(getHomeDataUseCase::invoke)
            .onEach { either -> Timber.tag(logTag).d("Emit users.size=${either.map { it.size }}") }
            .map { result ->
                result.fold(
                    ifLeft = { PartialStateChange.Homes.Error(it) },
                    ifRight = { PartialStateChange.Homes.Data(it.map(::HomeItem)) }
                )
            }
            .startWith(PartialStateChange.Homes.Loading)

        return merge(
            merge(
                filterIsInstance<HomeViewIntent.Initial>(),
                filterIsInstance<HomeViewIntent.Retry>()
                    .filter { viewState.value.error != null }
            ).flatMapLatest {
                userChanges
            }
        )
    }
}