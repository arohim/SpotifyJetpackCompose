package com.him.sama.spotifycompose.feature.home

import androidx.lifecycle.ViewModel
import com.him.sama.spotifycompose.common.core.domain.usecase.GetHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

    init {
        
    }
}