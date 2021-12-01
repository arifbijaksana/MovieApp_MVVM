package com.arifbm.movieapp.data.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arifbm.movieapp.data.local.FavoriteMovie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private lateinit var list: List<FavoriteMovie>
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(list: List<FavoriteMovie>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(favoriteMovie: FavoriteMovie) {
            with(binding) {
                Glide.with(itemView).load("${favoriteMovie.baseUrl}${favoriteMovie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error).into(ivMoviePoster)

                tvMovieTitle.text = favoriteMovie.original_title
                binding.root.setOnClickListener {
                    onItemClickCallback?.onItemClick(favoriteMovie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.binding(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClick(favoriteMovie: FavoriteMovie)
    }
}