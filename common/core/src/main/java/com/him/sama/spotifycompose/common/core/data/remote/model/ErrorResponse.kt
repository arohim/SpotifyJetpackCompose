package com.him.sama.spotifycompose.common.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("statusCode")
    val statusCode: Int, // 404
    @SerializedName("error")
    val error: String? = null, // Not Found
    @SerializedName("message")
    val message: String, // Cannot GET /23
    @SerializedName("data")
    val data: Any? = null
)
