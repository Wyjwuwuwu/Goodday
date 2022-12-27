//package com.example.goodday
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.imageview.ShapeableImageView
//
//class HealthMSAdapter(private val HealthMSList: ArrayList<HealthMSModel>) :
//    RecyclerView.Adapter<HealthMSAdapter.HealthMSHolder>()   {
//    class HealthMSHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
//        val symbol_blood: ImageView = itemView.findViewById(R.id.symbol_blood)
//        val intro_blood: TextView = itemView.findViewById(R.id.tvIntro_blood)
//        val symbol_shiver: ImageView = itemView.findViewById(R.id.symbol_shiver)
//        val intro_shiver: TextView = itemView.findViewById(R.id.tvIntro_shiver)
//        val symbol_temp: ImageView = itemView.findViewById(R.id.symbol_temp)
//        val intro_temp: TextView = itemView.findViewById(R.id.tvIntro_temp)
//        val symbol_puls: ImageView = itemView.findViewById(R.id.symbol_puls)
//        val intro_puls: TextView = itemView.findViewById(R.id.tvIntro_puls)
//        val symbol_pain: ImageView = itemView.findViewById(R.id.symbol_pain)
//        val intro_pain: TextView = itemView.findViewById(R.id.tvIntro_pain)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthMSHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.health_measure_item,
//            parent,false)
//        return HealthMSAdapter.HealthMSHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: HealthMSHolder, position: Int) {
//        val currentItem = HealthMSList[position]
//        holder.symbol_puls.setImageResource(currentItem.symbol_puls)
//        holder.intro_puls.text = currentItem.intro_puls
//        holder.symbol_temp.setImageResource(currentItem.symbol_temp)
//        holder.intro_temp.text = currentItem.intro_temp
//        holder.symbol_shiver.setImageResource(currentItem.symbol_shiver)
//        holder.intro_shiver.text = currentItem.intro_shiver
//        holder.symbol_blood.setImageResource(currentItem.symbol_blood)
//        holder.intro_blood.text = currentItem.intro_blood
//        holder.symbol_pain.setImageResource(currentItem.symbol_pain)
//        holder.intro_pain.text = currentItem.intro_pain
//    }
//
//    override fun getItemCount(): Int {
//        return HealthMSList.size
//    }
//}