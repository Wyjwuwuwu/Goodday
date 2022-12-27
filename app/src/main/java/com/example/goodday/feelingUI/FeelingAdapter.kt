package com.example.goodday.feelingUI

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.Key.VISIBILITY
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.R
import com.example.goodday.databinding.ActivityHealthMeasureBinding
import com.example.goodday.databinding.HealthMeasureItemBinding
import com.example.goodday.feelingModel.Feeling

class FeelingAdapter(var context: Context, feeling: ArrayList<Feeling>)
    : RecyclerView.Adapter<FeelingAdapter.FeelingViewHolder>() {
    var symbol_blood: Int = 0
    var intro_blood: String = "The Blood glucose level is "
    var symbol_shiver: Int = 0
    var intro_shiver: String = "Feeling "
    var symbol_temp: Int = 0
    var intro_temp: String = "The Temperature is "
    var symbol_puls: Int = 0
    var intro_puls: String = "The pulse rate is "
    var symbol_pain: Int = 0
    var intro_pain: String = "Feeling "
    var date :String = ""

    //
    private var binding: HealthMeasureItemBinding? = null
    private var activityHealthMeasureBinding:ActivityHealthMeasureBinding? = null
    var feeling: ArrayList<Feeling>

    init {
        this.feeling = feeling

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeelingViewHolder {
        binding = HealthMeasureItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FeelingViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: FeelingViewHolder, position: Int) {
//        val current = getItem(position)
//        holder.bind(current.breath.toString())

        holder.bind(feeling!![position])
    }

    override fun getItemCount(): Int {
        return feeling.size
    }

    inner class FeelingViewHolder(private val binding: HealthMeasureItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Feeling) {
            if(item.equals(null)){

            }
            if(Integer.parseInt((item.temperature).toString())>3){
                symbol_temp = R.drawable.circle_blue
                intro_temp = "Very High"
            }else {
                symbol_temp = R.drawable.circle_green
                intro_temp = "Normal"
            }

            if(Integer.parseInt((item.breath).toString())==5){
                symbol_pain = R.drawable.circle_blue
                intro_pain = "Very Painful"
            }else if(Integer.parseInt((item.breath).toString())==4) {
                symbol_pain = R.drawable.circle_ora
                intro_pain = "Painful"
            }
            else if(Integer.parseInt((item.breath).toString())==3) {
                symbol_pain = R.drawable.circle_green
                intro_pain = "Normal"
            }else if(Integer.parseInt((item.breath).toString())==2) {
                symbol_pain = R.drawable.circle_ora
                intro_pain= "Polypnea"
            }else if(Integer.parseInt((item.breath).toString())==1) {
                symbol_pain = R.drawable.circle_blue
                intro_pain= "Dyspnea"
            }

            if(Integer.parseInt((item.bloPressure).toString())==5){
                symbol_blood = R.drawable.circle_blue
                intro_blood= "Very High"
            }else if(Integer.parseInt((item.bloPressure).toString())==4) {
                symbol_blood = R.drawable.circle_ora
                intro_blood= "High"
            }
            else if(Integer.parseInt((item.bloPressure).toString())==3) {
                symbol_blood = R.drawable.circle_green
                intro_blood= "Normal"
            }else if(Integer.parseInt((item.bloPressure).toString())==2) {
                symbol_blood = R.drawable.circle_ora
                intro_blood= "Low"
            }else if(Integer.parseInt((item.bloPressure).toString())==1) {
                symbol_blood = R.drawable.circle_blue
                intro_blood= "Very Low"
            }

            if(Integer.parseInt((item.shiver).toString())==5){
                symbol_shiver = R.drawable.circle_blue
                intro_shiver= "Very Thirsty and Confusion"
            }else if(Integer.parseInt((item.shiver).toString())==4) {
                symbol_shiver = R.drawable.circle_ora
                intro_shiver= "Thirsty and Confusion"
            }
            else if(Integer.parseInt((item.shiver).toString())==3) {
                symbol_shiver = R.drawable.circle_green
                intro_shiver= "Normal"
            }else if(Integer.parseInt((item.shiver).toString())==2) {
                symbol_shiver = R.drawable.circle_ora
                intro_shiver= "Shiver and Dizzy"
            }else if(Integer.parseInt((item.shiver).toString())==1) {
                symbol_shiver = R.drawable.circle_blue
                intro_shiver= "Very Shiver and Dizzy"
            }

            if(Integer.parseInt((item.pulse).toString())==5){
                symbol_puls = R.drawable.circle_blue
                intro_puls= "Very Fast"
            }else if(Integer.parseInt((item.pulse).toString())==4) {
                symbol_puls = R.drawable.circle_ora
                intro_puls= "Fast"
            }
            else if(Integer.parseInt((item.pulse).toString())==3) {
                symbol_puls = R.drawable.circle_green
                intro_puls= "Normal"
            }else if(Integer.parseInt((item.pulse).toString())==2) {
                symbol_puls = R.drawable.circle_ora
                intro_puls= "Low"
            }else if(Integer.parseInt((item.pulse).toString())==1) {
                symbol_puls = R.drawable.circle_blue
                intro_puls= "Very Low"
            }
            date = item.date

            binding.tvIntroTemp.text = intro_temp
            binding.tvIntroBlood.text = intro_blood
            binding.tvIntroPain.text = intro_pain
            binding.tvIntroShiver.text = intro_shiver
            binding.tvIntroPuls.text = intro_puls
            binding.date.text = date
            Glide.with(context)
                .load(symbol_blood)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.symbolBlood)
            Glide.with(context)
                .load(symbol_pain)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.symbolPain)
            Glide.with(context)
                .load(symbol_puls)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.symbolPuls)
            Glide.with(context)
                .load(symbol_temp)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.symbolTemp)
            Glide.with(context)
                .load(symbol_shiver)
                .apply(RequestOptions.centerCropTransform())
                .into(binding.symbolShiver)


        }
    }
}
//            binding.tvIntroBlood.text = item.bloPressure.toString()
//            binding.tvIntroPain.text = item.breath.toString()
//            binding.tvIntroShiver.text = item.shiver.toString()
//            binding.tvIntroPuls.text = item.pulse.toString()

//    class FeelingViewHolder(itemView: HealthMeasureItemBinding) : RecyclerView.ViewHolder(itemView) {
//        private val feelingItemView: TextView = itemView.findViewById(R.id.)
//
//        fun bind(text: Feeling) {
//            feelingItemView.text = text
//        }
//
//        companion object {
//            fun create(parent: ViewGroup): FeelingViewHolder {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.health_measure_item, parent, false)
//                return FeelingViewHolder(view)
//            }
//        }
//    }
//
//    companion object {
//        private val FEELING_COMPARATOR = object : DiffUtil.ItemCallback<Feeling>() {
//            override fun areItemsTheSame(oldItem: Feeling, newItem: Feeling): Boolean {
//                return oldItem === newItem
//            }
//
//            override fun areContentsTheSame(oldItem: Feeling, newItem: Feeling): Boolean {
//                return oldItem.breath == newItem.breath
//            }
//        }
//    }
