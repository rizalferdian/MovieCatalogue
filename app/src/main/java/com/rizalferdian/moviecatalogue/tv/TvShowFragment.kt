package com.rizalferdian.moviecatalogue.tv


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalferdian.moviecatalogue.databinding.FragmentTvShowBinding
import com.rizalferdian.moviecatalogue.movie.MovieAdapter
import com.rizalferdian.moviecatalogue.service.ApiStatus
import com.rizalferdian.moviecatalogue.util.makeGone
import com.rizalferdian.moviecatalogue.util.makeVisible

class TvShowFragment : Fragment() {

    private lateinit var binding: FragmentTvShowBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel::class.java)

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        adapter = TvShowAdapter()
        binding.include.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TvShowFragment.adapter
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
