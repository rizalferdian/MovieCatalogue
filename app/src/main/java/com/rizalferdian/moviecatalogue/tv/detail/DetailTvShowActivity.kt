package com.rizalferdian.moviecatalogue.tv.detail

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
import com.rizalferdian.moviecatalogue.databinding.ActivityDetailTvShowBinding
import com.rizalferdian.moviecatalogue.service.ApiStatus
import com.rizalferdian.moviecatalogue.util.makeGone
import com.rizalferdian.moviecatalogue.util.makeVisible
import com.rizalferdian.moviecatalogue.util.setImage
import com.rizalferdian.moviecatalogue.util.toString
import java.util.*

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTvShowViewModel

    companion object {
        private const val EXTRA_TV_SHOw_ID = "EXTRA_TV_SHOw_ID"

        fun newIntent(packageContext: Context, movieId: Int): Intent {
            return Intent(packageContext, DetailTvShowActivity::class.java).apply {
                putExtra(EXTRA_TV_SHOw_ID, movieId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvId = intent.getIntExtra(EXTRA_TV_SHOw_ID, 0)
        val viewModelFactory = DetailTvShowViewModelFactory(tvId)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailTvShowViewModel::class.java)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
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

    private fun setupData(data: DetailTvShowModel) {
        binding.textTitle.text = data.name
        val imageUri =
            Uri.parse(BuildConfig.IMAGE_MEDIUM_SIZE_URL).buildUpon().appendEncodedPath(data.posterPath).build()
        binding.imgPoster.setImage(imageUri)

        binding.textVote.text = data.voteAverage.toString()
        binding.textVoteCount.text = data.voteCount.toString()
        binding.textNumberOfSeasons.text = data.numberOfSeasons.toString()
        binding.textNumberOfEpisodes.text = data.numberOfEpisodes.toString()
        binding.textFirstAirDate.text = data.firstAirDate.toString("d MMMM yyyy", Locale.US)

        data.genres.forEach {
            val chip = Chip(this).apply {
                text = it.name
            }
            binding.chipGroupGenre.addView(chip)
        }

        binding.textOverview.text = data.overview
    }
}
