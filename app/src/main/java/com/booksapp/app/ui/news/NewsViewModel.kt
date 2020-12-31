package com.booksapp.app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.booksapp.app.data.model.ActionState
import com.booksapp.app.data.model.News
import com.booksapp.app.data.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.http.Query
import java.net.URL

class NewsViewModel : ViewModel() {
    private val repo: NewsRepository by lazy { NewsRepository () }

    val loading = MutableLiveData(false)
    val actionState = MutableLiveData<ActionState<*>>()

    val listResp = MutableLiveData<List<News>>()
    val detailResp = MutableLiveData<News>()
    val searchResp = MutableLiveData<List<News>>()

    val endpoint = MutableLiveData ( "")
    val query = MutableLiveData("")

    fun listNews(){
        viewModelScope.launch {
            loading.value =true
            val resp = repo.listNews()
            actionState.value=resp
            listResp.value=resp.data
            loading.value = false
        }
    }
    fun detailNews(endpoint: String? = this.endpoint.value){
        endpoint?.let {
            viewModelScope.launch {
                loading.value=true
                val resp = repo.detailNews(it)
                actionState.value = resp
                detailResp.value = resp.data
                loading.value=false
            }
        }
    }
    fun serachNews(query: String?= this.query.value){
        query?.let{
            viewModelScope.launch {
                loading.value=true
                val resp =repo.sesrchNews(it)
                actionState.value = resp
                searchResp.value=resp.data
                loading.value =false
            }
        }
    }
}