package com.rizalferdian.moviecatalogue.tv

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizalferdian.moviecatalogue.BuildConfig
import com.rizalferdian.moviecatalogue.databinding.ItemMovieBinding
import com.rizalferdian.moviecatalogue.tv.detail.DetailTvShowActivity
import com.rizalferdian.moviecatalogue.util.setImage

class TvShowAdapter : PagedListAdapter<TvShowModel, TvShowAdapter.MovieViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TvShowModel>() {
            override fun areItemsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowModel, newItem: TvShowModel): Boolean {
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
            binding.root.setOnClickListener {
                val data = getItem(layoutPosition) ?: return@setOnClickListener
                val context = it.context
                ContextCompat.startActivity(context, DetailTvShowActivity.newIntent(context, data.id), null)
            }
        }

        fun bind(data: TvShowModel) {
            val imageUri =
                Uri.parse(BuildConfig.IMAGE_URL).buildUpon().appendEncodedPath(data.posterPath)
                    .build()
            binding.imgPoster.setImage(imageUri)
            binding.textTitle.text = data.name
            binding.textSubtitle.text = data.overview
        }
    }
}