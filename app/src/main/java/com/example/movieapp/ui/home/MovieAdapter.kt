package com.example.movieapp.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.db.MovieEntity
import com.example.movieapp.databinding.ItemMovieBinding

class MovieAdapter(
    private val onMovieClick: (MovieEntity) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var list: List<MovieEntity> = emptyList()

    fun submitList(newList: List<MovieEntity>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = list[position]

        holder.binding.tvTitle.text = movie.title

        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
        Log.d("IMAGE_URL", imageUrl)

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.holo_red_dark)
            .into(holder.binding.ivPoster)

        holder.itemView.setOnClickListener {
            onMovieClick(movie)
        }
    }
}