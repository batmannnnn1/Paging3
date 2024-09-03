package com.example.paging3.Paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.Model.Result
import com.example.paging3.R

class QuotePagingAdapter: PagingDataAdapter<Result, QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) {

    companion object{
        private val COMPARATOR = object:DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {

                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

                return oldItem.id== newItem.id
            }

        }
    }

    class QuoteViewHolder(item:View):RecyclerView.ViewHolder(item){
           val quote = item.findViewById<TextView>(R.id.quote)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        holder.quote.text = item?.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote_layout,parent,false)
        return QuoteViewHolder(view)
    }


}