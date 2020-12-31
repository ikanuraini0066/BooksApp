package com.booksapp.app.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.booksapp.app.BR

abstract class BaseAdapter<T>(@field:LayoutRes private var layoutId:Int):
    RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>(), BindableAdapter<List<T>> {

     private var listManga_list:List<T>?=null

     init {
         this.setHasStableIds(true)
     }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding:ViewDataBinding?=DataBindingUtil.bind(itemView)

        fun onBind(variale:Int?, manga_list:Any?):ViewDataBinding?{
           return variale?.takeIf { manga_list!= null }?.let {
               binding?.apply {
                   setVariable(it,manga_list)
                   executePendingBindings()
               }
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listManga_list?.let {
            val manga_list = it[position]
            val binding = holder.onBind(BR.model,manga_list)
            onBind(binding, manga_list)
            holder.itemView.setOnClickListener { onClick(binding, manga_list) }
        }
    }
    abstract fun onBind(binding: ViewDataBinding?, manga_list:T)

    abstract fun onClick(binding: ViewDataBinding?,manga_list:T)
    override fun getItemCount(): Int {
        return listManga_list?.size?:0
    }

    override fun setData(manga_list: List<T>?) {
       listManga_list = manga_list
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

interface BindableAdapter<T> {
    fun setData(manga_list:T?)
}
@Suppress( "UNCHECKED_CAST")
@BindingAdapter("manga_list")
fun <T> recyclerViewData(recyclerView: RecyclerView, manga_list:T?){
    if (recyclerView.adapter is BindableAdapter<*>){
        (recyclerView.adapter as BindableAdapter<T>).setData(manga_list)
    }
}