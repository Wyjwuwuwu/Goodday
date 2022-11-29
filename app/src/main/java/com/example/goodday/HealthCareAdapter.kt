package com.example.goodday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class HealthCareAdapter(private val HealthCareList: ArrayList<HealthCareModel>) :
    RecyclerView.Adapter<HealthCareAdapter.HealthCareHolder>()  {



    class HealthCareHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val tvPhone: TextView = itemView.findViewById(R.id.tvPhone)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthCareHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.health_care_item,
            parent,false)
        return HealthCareAdapter.HealthCareHolder(itemView)
    }

    override fun onBindViewHolder(holder: HealthCareHolder, position: Int) {
        val currentItem = HealthCareList[position]
        holder.titleImage.setImageResource(currentItem.Image)
        holder.tvHeading.text = currentItem.title
        holder.tvAddress.text = currentItem.address
        holder.tvPhone.text = currentItem.phone
    }

    override fun getItemCount(): Int {
        return HealthCareList.size
    }
}