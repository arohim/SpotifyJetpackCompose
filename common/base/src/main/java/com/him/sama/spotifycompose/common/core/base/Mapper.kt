package com.him.sama.spotifycompose.common.core.base

interface Mapper<T, R> {
  operator fun invoke(param: T): R
}
