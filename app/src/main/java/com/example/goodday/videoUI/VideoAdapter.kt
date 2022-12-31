package com.example.goodday.videoUI

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.databinding.ListItemBinding
import com.example.goodday.databinding.VidImageItemBinding
import com.example.goodday.videoModel.NewsVideo
import com.example.goodday.videoModel.VideoUser

class VideoAdapter(var context: Context, videos: ArrayList<NewsVideo>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var binding: VidImageItemBinding? = null
    var onItemClick:((NewsVideo)-> Unit)? = null
    var videos: ArrayList<NewsVideo>
    var user: ArrayList<VideoUser>? = null

    init {
        this.videos =videos
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(
//            R.layout.list_item,
//        parent,false)
//        return NewsViewHolder(itemView)
        binding = VidImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return VideoViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos!![position])
//        val currentItem = articles[position]
//        holder.titleImage.setImageResource(currentItem.titleImage)
//        holder.tvHeading.text = currentItem.heading
//        holder.tvAuthor.text = currentItem.author

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(this.videos[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }
    //    class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
//        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
//        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
//    }
    inner class VideoViewHolder(private val binding: VidImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsVideo) {
//            binding.idTVImage.text = item.videoFile?.get(0)?.url
            binding.idTVImage.text = "Author:"+item.user?.description.toString()
            Glide.with(context)
                .load(item.urlToImage)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.idIVImage)
        }
    }

}