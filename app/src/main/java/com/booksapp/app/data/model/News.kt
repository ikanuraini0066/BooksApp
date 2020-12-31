package com.booksapp.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title:String = "",
    val thumb:String = "",
    val type:String= " ",
    val update_on:String=" ",
    val endpoint:String="",
    val chapter: String
) : Parcelable
