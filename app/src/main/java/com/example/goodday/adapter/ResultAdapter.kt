package com.example.goodday.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.goodday.HealthView
import com.example.goodday.R


class ResultAdapter(private val context: HealthView,
                    private val content: ArrayList<String>,
                    private val imgid: ArrayList<Int>
)
    :ArrayAdapter<String>(context, R.layout.recycle_view_layout,content){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.recycle_view_layout, null, true)

        val titleText = rowView.findViewById(R.id.tv_result) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView

        titleText.text = content[position]
        imageView.setImageResource(imgid[position])

        return rowView
    }

}