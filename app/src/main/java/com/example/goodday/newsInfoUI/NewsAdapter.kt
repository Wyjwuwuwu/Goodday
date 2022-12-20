package com.example.goodday.newsInfoUI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.NewsModel
import com.example.goodday.R
import com.example.goodday.databinding.ListItemBinding
import com.example.goodday.newsInfoModel.NewsArticle
import com.example.goodday.newsInfoModel.NewsResponse
import com.google.android.material.imageview.ShapeableImageView

class NewsAdapter(var context: Context, articles: ArrayList<NewsArticle>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var binding: ListItemBinding? = null
    var onItemClick:((NewsArticle)-> Unit)? = null
    var articles: ArrayList<NewsArticle>

    init {
        this.articles = articles
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(
//            R.layout.list_item,
//        parent,false)
//        return NewsViewHolder(itemView)
        binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return NewsViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(articles!![position])
//        val currentItem = articles[position]
//        holder.titleImage.setImageResource(currentItem.titleImage)
//        holder.tvHeading.text = currentItem.heading
//        holder.tvAuthor.text = currentItem.author

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(this.articles[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
//    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
//        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
//        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
//    }
    inner class NewsViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsArticle) {
            binding.tvHeading.text = item.title
            binding.tvAuthor.text = item.description
            Glide.with(context)
                .load(item.urlToImage)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.titleImage)
        }
    }

}