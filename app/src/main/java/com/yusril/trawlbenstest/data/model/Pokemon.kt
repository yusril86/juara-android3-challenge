package com.yusril.trawlbenstest.data.model

import com.google.gson.annotations.SerializedName
import com.yusril.trawlbenstest.utils.lastPathSegmentAsAnInt

data class Pokemon(
    var id: Int?,
    @SerializedName("name" )
    var name : String? = null,
    @SerializedName("url"  )
    var url  : String? = null
)
{
    val number
        get() = url?.lastPathSegmentAsAnInt()
}