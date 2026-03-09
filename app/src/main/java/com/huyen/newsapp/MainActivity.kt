package com.huyen.newsapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.huyen.newsapp.databinding.ActivityMainBinding
import com.huyen.newsapp.ui.adapter.NewsAdapter
import com.huyen.newsapp.ui.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel.loadNews()

        lifecycleScope.launch {

            viewModel.newsState.collect { list ->

                binding.recyclerView.adapter =
                    NewsAdapter(
                        list,
                        { article ->

                            // bookmark (nếu cần)

                        },
                        { article ->

                            // click mở bài báo

                        }
                    )
            }
        }
    }
}