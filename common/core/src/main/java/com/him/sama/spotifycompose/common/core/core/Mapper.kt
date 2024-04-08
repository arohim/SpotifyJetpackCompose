package com.him.sama.spotifycompose.common.core.core

interface Mapper<T, R> {
  operator fun invoke(param: T): R
}
