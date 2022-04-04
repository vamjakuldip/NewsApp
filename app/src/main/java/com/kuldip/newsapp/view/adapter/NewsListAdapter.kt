package com.kuldip.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuldip.newsapp.R
import com.kuldip.presentation.model.NewsArticleUIModel
import javax.inject.Inject


class NewsListAdapter @Inject constructor() : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    // Properties.
    var newsArticles = arrayListOf<NewsArticleUIModel>()

    // View Holder.
    inner class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivImage: ImageView = itemView.findViewById(R.id.image)
        val tvTitle: TextView = itemView.findViewById(R.id.title)
        val tvAdditionalInfo: TextView = itemView.findViewById(R.id.additional_info)

    }

    // RecyclerView.Adapter Methods.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsArticle = newsArticles.get(position)

        val additionalInfo = "${newsArticle.source} - ${newsArticle.publishedAt}"

        Glide.with(holder.ivImage.context)
            .load(newsArticle.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_default_thumbnail)
            .into(holder.ivImage)

        holder.tvTitle.text = newsArticle.title
        holder.tvAdditionalInfo.text = additionalInfo
    }

    override fun getItemCount(): Int {
        return newsArticles.size
    }
}