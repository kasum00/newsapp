package com.huyen.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.huyen.newsapp.data.model.Article
import com.huyen.newsapp.databinding.ItemNewsBinding

class NewsAdapter(

    private var list: List<Article>,
    private val onBookmarkClick: (Article) -> Unit,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {

        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {

        val article = list[position]

        holder.binding.title.text = article.title

        Glide.with(holder.itemView.context)
            .load(article.image)
            .into(holder.binding.image)

        holder.binding.bookmarkBtn.setOnClickListener {

            onBookmarkClick(article)

        }

        holder.itemView.setOnClickListener {

            onItemClick(article)

        }
    }

    fun updateData(newList: List<Article>) {

        list = newList
        notifyDataSetChanged()

    }
}