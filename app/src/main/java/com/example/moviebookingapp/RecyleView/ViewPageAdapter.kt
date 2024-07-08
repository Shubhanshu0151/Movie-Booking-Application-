package com.example.moviebookingapp.RecyleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebookingapp.R

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class ViewPageAdapter(
    private val images: List<Int>,
    private val texts: List<String>,
    private val listener: OnItemClickListener,
    private val prices: List<String>, // Add prices parameter
) : RecyclerView.Adapter<ViewPageAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iVImage)
        val textView: TextView = itemView.findViewById(R.id.tvMovieName)
        val priceTextView: TextView = itemView.findViewById(R.id.tvPrice) // Add price TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_page, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.imageView.setImageResource(curImage)

        val curPrice = prices[position] // Get the price for this position
        holder.priceTextView.text = curPrice // Set price text

        val curText = texts[position]
        holder.textView.text = curText
        // Add click listener to the item view
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)

        }
    }
}