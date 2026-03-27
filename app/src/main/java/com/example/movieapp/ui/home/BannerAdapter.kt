package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.db.MovieEntity
import com.example.movieapp.databinding.ItemBannerBinding

class BannerAdapter(
    private val onMovieClick: (MovieEntity) -> Unit
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private var list: List<MovieEntity> = emptyList()

    fun submitList(newList: List<MovieEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class BannerViewHolder(val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val movie = list[position]

        val imageUrl = "https://image.tmdb.org/t/p/w780${movie.backdropPath}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.ivBanner)

        holder.itemView.setOnClickListener {
            onMovieClick(movie)
        }
    }
}