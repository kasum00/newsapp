package com.huyen.newsapp.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.huyen.newsapp.R
import com.huyen.newsapp.data.local.ArticleEntity
import com.huyen.newsapp.databinding.FragmentNewsBinding
import com.huyen.newsapp.ui.adapter.NewsAdapter
import com.huyen.newsapp.ui.web.WebViewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsBinding.bind(view)

        newsAdapter = NewsAdapter(
            emptyList(),
            { article ->

                val entity = ArticleEntity(
                    url = article.url,
                    title = article.title,
                    description = article.description,
                    image = article.image,
                    publishedAt = article.publishedAt
                )

                viewModel.saveArticle(entity)

            },
            { article ->

                val intent = Intent(requireContext(), WebViewActivity::class.java)

                intent.putExtra("url", article.url)

                startActivity(intent)

            }
        )

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        binding.recyclerView.adapter = newsAdapter

        // gọi API khi mở Fragment
        viewModel.loadNews()

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.newsState.collect { list ->

                newsAdapter.updateData(list)

            }
        }

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {

                    query?.let {

                        viewModel.searchNews(it)

                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false

                }
            }
        )
    }
}