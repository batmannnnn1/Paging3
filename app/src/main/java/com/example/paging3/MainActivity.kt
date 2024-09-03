package com.example.paging3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.Paging.LoaderAdapter
import com.example.paging3.Paging.QuotePagingAdapter
import com.example.paging3.ViewModel.QuoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: QuoteViewModel
    lateinit var adapter: QuotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.quoteList)

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = QuotePagingAdapter()

        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = LoaderAdapter(),
            header = LoaderAdapter())

        viewModel = ViewModelProvider(this).get(QuoteViewModel::class)
        viewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle,it)
        })
    }
}