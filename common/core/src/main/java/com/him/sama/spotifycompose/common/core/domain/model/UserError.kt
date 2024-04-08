package com.him.sama.spotifycompose.common.core.domain.model


sealed class UserError : Throwable() {
    data object NetworkError : UserError()
    data object ServerError : UserError()
    data object Unexpected : UserError()
}
