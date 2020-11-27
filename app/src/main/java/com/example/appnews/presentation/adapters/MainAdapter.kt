package com.example.appnews.presentation.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.data.model.Article
import com.example.appnews.data.repositories.NewsRepository
import com.example.appnews.utils.downloadAndSetImage
import kotlinx.android.synthetic.main.main_item.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleHolder>(){

    inner class ArticleHolder(view: View) : RecyclerView.ViewHolder(view)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            poster_imageView.downloadAndSetImage(article.urlToImage)
            title_textView.text = article.title
            description_textView.text = article.description
            news_title_textView.text = article.source?.name

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}

