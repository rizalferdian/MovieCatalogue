package com.rizalferdian.moviecatalogue.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalferdian.moviecatalogue.databinding.FragmentMovieBinding
import com.rizalferdian.moviecatalogue.service.ApiStatus
import com.rizalferdian.moviecatalogue.util.makeGone
import com.rizalferdian.moviecatalogue.util.makeVisible

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel::class.java)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        adapter = MovieAdapter()
        binding.include.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MovieFragment.adapter
        }
    }


    private fun setupViewModel() {
        viewModel.refreshState.observe(this) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    binding.include.shimmerView.makeGone()
                    binding.include.shimmerView.stopShimmer()
                    binding.include.recyclerView.makeVisible()
                }
                ApiStatus.LOADING -> {
                    binding.include.shimmerView.makeVisible()
                    binding.include.shimmerView.startShimmer()
                    binding.include.recyclerView.makeGone()
                }
                ApiStatus.ERROR -> {
                    binding.include.shimmerView.makeGone()
                    binding.include.shimmerView.stopShimmer()
                    Toast.makeText(this.context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.movieLive.observe(this) { items ->
            adapter.submitList(items)
        }
    }
}
