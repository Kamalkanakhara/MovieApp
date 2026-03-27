package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.db.MovieEntity
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.utils.UiState
import com.example.movieapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private var selectedBannerMovie: MovieEntity? = null
    private var bannerMovieList: List<MovieEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter { movie ->
            val bundle = Bundle().apply {
                putInt("movieId", movie.id)
            }
            findNavController().navigate(
                com.example.movieapp.R.id.action_homeFragment_to_movieDetailsFragment,
                bundle
            )
        }
        bannerAdapter = BannerAdapter { movie ->
            val bundle = Bundle().apply {
                putInt("movieId", movie.id)
            }

            findNavController().navigate(
                com.example.movieapp.R.id.action_homeFragment_to_movieDetailsFragment,
                bundle
            )
        }
        binding.btnBookTrending.setOnClickListener {
            val movie = selectedBannerMovie ?: return@setOnClickListener

            val bundle = Bundle().apply {
                putInt("movieId", movie.id)
            }

            findNavController().navigate(
                com.example.movieapp.R.id.action_homeFragment_to_movieDetailsFragment,
                bundle
            )
        }
        binding.navMovies.setOnClickListener {
            // already on home, so do nothing
        }

        binding.navSearch.setOnClickListener {
            findNavController().navigate(com.example.movieapp.R.id.action_homeFragment_to_searchFragment)
        }

        binding.navProfile.setOnClickListener {
            findNavController().navigate(com.example.movieapp.R.id.action_homeFragment_to_profileFragment)
        }

        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovies.adapter = movieAdapter

        val bannerLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBanner.layoutManager = bannerLayoutManager
        binding.rvBanner.adapter = bannerAdapter

        val snapHelper = PagerSnapHelper()
        if (binding.rvBanner.onFlingListener == null) {
            snapHelper.attachToRecyclerView(binding.rvBanner)
        }

        binding.rvBanner.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val snappedView = snapHelper.findSnapView(bannerLayoutManager)
                    val position = snappedView?.let { bannerLayoutManager.getPosition(it) }
                        ?: RecyclerView.NO_POSITION

                    if (position != RecyclerView.NO_POSITION && position < bannerMovieList.size) {
                        updateTrendingCard(bannerMovieList[position])
                    }
                }
            }
        })

        viewModel.movies.observe(viewLifecycleOwner) { movieList ->
            movieAdapter.submitList(movieList)
        }

        viewModel.bannerMovies.observe(viewLifecycleOwner) { bannerList ->
            bannerMovieList = bannerList
            bannerAdapter.submitList(bannerList)

            if (bannerList.isNotEmpty()) {
                updateTrendingCard(bannerList[0])
            }
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // optional loader
                }
                is UiState.Success -> {
                    // optional success handling
                }
                is UiState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateTrendingCard(movie: MovieEntity) {
        selectedBannerMovie = movie
        binding.tvTrending.text = "TRENDING"
        binding.tvTrendingTitle.text = movie.title
        binding.tvTrendingLanguage.text = "A • ENGLISH"
        binding.tvTrendingGenre.text = "Now Showing"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}