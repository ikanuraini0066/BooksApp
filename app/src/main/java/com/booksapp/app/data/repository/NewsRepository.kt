package com.booksapp.app.data.repository

import com.booksapp.app.data.model.ActionState
import com.booksapp.app.data.model.News
import com.booksapp.app.data.remote.NewsService
import com.booksapp.app.data.remote.RetrofitApp
import retrofit2.await
import java.lang.Exception

class NewsRepository {
    private val newsService:NewsService by lazy { RetrofitApp.newsService() }

    suspend fun listNews() : ActionState<List<News>>{
        return try{
            val list = newsService.listNews().await()
            ActionState(list.manga_list)
        }catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }
    suspend fun detailNews(endpoint:String) : ActionState<News>{
        return try {
            val list = newsService.detailNews(endpoint).await()
            ActionState(list.manga_list.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
}
    suspend fun sesrchNews(query: String) : ActionState<List<News>> {
        return try{
            val list = newsService.searchNews(query).await()
            ActionState(list.manga_list)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }
}