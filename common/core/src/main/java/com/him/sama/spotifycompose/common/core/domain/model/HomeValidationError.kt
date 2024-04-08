package com.him.sama.spotifycompose.common.core.domain.model

import arrow.core.EitherNel
import arrow.core.ValidatedNel
import arrow.core.invalidNel
import arrow.core.leftNel

enum class HomeValidationError {
    INVALID;

    val asInvalidNel: EitherNel<HomeValidationError, Nothing> = this.leftNel()
}
