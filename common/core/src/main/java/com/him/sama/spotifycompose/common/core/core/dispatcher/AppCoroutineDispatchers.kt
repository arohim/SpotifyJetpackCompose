package com.him.sama.spotifycompose.common.core.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface AppCoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}