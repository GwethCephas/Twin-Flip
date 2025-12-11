package com.twinflip.data.local.model

import com.google.gson.annotations.SerializedName

data class CardEntity(
    @SerializedName("path") val imagePath: String,
    val name: String
)