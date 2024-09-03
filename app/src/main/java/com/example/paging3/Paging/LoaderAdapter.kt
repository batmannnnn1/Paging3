package com.example.paging3.Paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder( item:View):RecyclerView.ViewHolder(item){
        val progressBar = item.findViewById<ProgressBar>(R.id.progress_bar)

        fun bind(loadState: LoadState){
            progressBar.isVisible = loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_item,parent,false)
        return LoaderViewHolder(view)
    }

}