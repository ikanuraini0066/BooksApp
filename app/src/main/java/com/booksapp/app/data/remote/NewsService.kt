package com.booksapp.app.data.remote

import com.booksapp.app.data.model.NewsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
  @GET("manga/page/1")
  fun listNews() : Call<NewsList>

  @GET("manga/detail")
  fun detailNews(@Query("endpoint")endpoint: String) : Call<NewsList>

  @GET("search/?q=parameter")
  fun searchNews(@Query("query")query: String) : Call<NewsList>
}