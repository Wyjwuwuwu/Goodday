package com.example.goodday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class VidImageAdapter (
    // on below line we are passing variables
    // as course list and context
    private val VidImageList: ArrayList<VidImageModel>

) : RecyclerView.Adapter<VidImageAdapter.VidImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VidImageHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.vid_image_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return VidImageHolder(itemView)
    }

    override fun onBindViewHolder(holder: VidImageHolder, position: Int) {
        val currentItem = VidImageList[position]
        // on below line we are setting data to our text view and our image view.
        holder.VidImageNameTV.text = currentItem.VidImageName
        holder.VidImageIV.setImageResource(currentItem.VidImageImg)
    }

    override fun getItemCount(): Int {
        return VidImageList.size
    }
    class VidImageHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        // on below line we are initializing our course name text view and our image view.
        val VidImageNameTV: TextView = itemView.findViewById<TextView>(R.id.idTVImage)
        val VidImageIV: ShapeableImageView = itemView.findViewById<ShapeableImageView>(R.id.idIVImage)
    }

}