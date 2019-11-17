package com.rizalferdian.moviecatalogue.movie.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.google.android.material.chip.Chip
import com.rizalferdian.moviecatalogue.BuildConfig
import com.rizalferdian.moviecatalogue.R
import com.rizalferdian.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.rizalferdian.moviecatalogue.service.ApiStatus
import com.rizalferdian.moviecatalogue.util.*
import java.util.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(packageContext: Context, movieId: Int): Intent {
            return Intent(packageContext, DetailMovieActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        val viewModelFactory = DetailMovieViewModelFactory(movieId)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailMovieViewModel::class.java)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_favorite) {
                viewModel.setFavorite()
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun setupViewModel() {
        viewModel.movieDetailState.observe(this) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    binding.shimmerView.makeGone()
                    binding.shimmerView.stopShimmer()
                    binding.containerDetail.makeVisible()
                    setupData(result.data!!)
                }
                ApiStatus.LOADING -> {
                    binding.shimmerView.makeVisible()
                    binding.shimmerView.startShimmer()
                    binding.containerDetail.makeGone()
                }
                ApiStatus.ERROR -> {
                    binding.shimmerView.makeGone()
                    binding.shimmerView.stopShimmer()
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupData(data: DetailMovieModel) {
        binding.textTitle.text = data.title
        val imageUri =
            Uri.parse(BuildConfig.IMAGE_MEDIUM_SIZE_URL).buildUpon().appendEncodedPath(data.posterPath).build()
        binding.imgPoster.setImage(imageUri)

        binding.textVote.text = data.voteAverage.toString()
        binding.textVoteCount.text = data.voteCount.toString()
        binding.textRevenue.text = data.revenue.toCurrency()
        binding.textBudget.text = data.budget.toCurrency()
        binding.textReleaseDate.text = data.releaseDate.toString("d MMMM yyyy", Locale.US)

        data.genres.forEach {
            val chip = Chip(this).apply {
                text = it.name
            }
            binding.chipGroupGenre.addView(chip)
        }

        binding.textOverview.text = data.overview
    }
}
