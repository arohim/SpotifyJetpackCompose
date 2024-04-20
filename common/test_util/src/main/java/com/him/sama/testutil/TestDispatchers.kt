package com.him.sama.testutil

import com.him.sama.spotifycompose.common.core.base.dispatcher.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatchers(testCoroutineDispatcher: TestCoroutineDispatcher) : AppCoroutineDispatchers {
    override val main: CoroutineDispatcher = testCoroutineDispatcher
    override val io: CoroutineDispatcher = testCoroutineDispatcher
    override val default: CoroutineDispatcher = testCoroutineDispatcher
}
