package com.booksapp.app.data.model

data class NewsList(
val status : Boolean = true,
val message : String = "",
val manga_list : List<News> = arrayListOf()
)
