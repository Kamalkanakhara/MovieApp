package com.example.movieapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ItemCastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    private var list: List<CastUiModel> = emptyList()

    fun submitList(newList: List<CastUiModel>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class CastViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CastViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = list[position]

        holder.binding.tvCastName.text = cast.name

        val imageUrl = "https://image.tmdb.org/t/p/w185${cast.profilePath}"

        Glide.with(holder.binding.ivCast.context)
            .load(imageUrl)
            .circleCrop()
            .placeholder(android.R.color.darker_gray)
            .error(android.R.color.darker_gray)
            .into(holder.binding.ivCast)
    }
}