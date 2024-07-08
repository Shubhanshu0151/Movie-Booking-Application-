package com.example.moviebookingapp.RecyleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebookingapp.R

class BannerViewPageAdapter(
    val imageBanner: List<Int>
) : RecyclerView.Adapter<BannerViewPageAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.banner_item_view, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageBanner.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = imageBanner[position]
        holder.itemView.apply {
            val itImage = findViewById<ImageView>(R.id.iVImageBanner)
            // Now you can use tvTitle and cbDone as needed
            itImage.setImageResource(curImage)
        }
    }


}