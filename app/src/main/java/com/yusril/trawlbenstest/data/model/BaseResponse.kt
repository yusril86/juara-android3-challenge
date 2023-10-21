package com.yusril.trawlbenstest.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("count") var count: Int?,
    @SerializedName("next") var next: String?,
    @SerializedName("previous") var previous: String?,
    @SerializedName("results") var results: List<T>,
)
