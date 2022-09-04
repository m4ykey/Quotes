package com.example.quotes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quotes (
    val text : String,
    val author : String
) : Parcelable