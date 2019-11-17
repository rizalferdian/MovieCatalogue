package com.rizalferdian.moviecatalogue.movie

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizalferdian.moviecatalogue.BuildConfig
import com.rizalferdian.moviecatalogue.databinding.ItemMovieBinding
import com.rizalferdian.moviecatalogue.movie.detail.DetailMovieActivity
import com.rizalferdian.moviecatalogue.util.setImage

class MovieAdapter : PagedListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.bind(data)
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.context
            binding.root.setOnClickListener {
                val data = getItem(layoutPosition) ?: return@setOnClickListener
                val context = it.context
                startActivity(context, DetailMovieActivity.newIntent(context, data.id), null)
            }
        }

        fun bind(data: MovieModel) {
            val imageUri =
                Uri.parse(BuildConfig.IMAGE_URL).buildUpon().appendEncodedPath(data.posterPath)
                    .build()
            binding.imgPoster.setImage(imageUri)
            binding.textTitle.text = data.title
            binding.textSubtitle.text = data.overview
        }
    }
}