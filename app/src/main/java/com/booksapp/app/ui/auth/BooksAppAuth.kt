package com.booksapp.app.ui.auth

import android.content.Context
import com.booksapp.app.data.model.ActionState
import com.booksapp.app.data.repository.AuthRepository
import kotlinx.coroutines.*
import javax.security.auth.callback.Callback

object BooksAppAuth {
    fun logout(context: Context, callback: ((ActionState<Boolean>)-> Unit) ?= null){
        val repo = AuthRepository(context)
        CoroutineScope(Dispatchers.IO).launch {
            val resp = repo.logout()
            withContext(Dispatchers.Main){
                if (callback !=null) callback.invoke(resp)
            }
        }
    }
}