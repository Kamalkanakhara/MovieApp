package com.example.movieapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.utils.UiState
import com.example.movieapp.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter

    private var trailerKey: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt("movieId") ?: -1
        if (movieId == -1) {
            Toast.makeText(requireContext(), "Invalid movie", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            return
        }

        castAdapter = CastAdapter()
        binding.rvCast.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCast.adapter = castAdapter

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnTrailer.setOnClickListener {
            val key = trailerKey
            if (!key.isNullOrBlank()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$key"))
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Trailer not available", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBookTickets.setOnClickListener {
            Toast.makeText(requireContext(), "Booking screen can be added next", Toast.LENGTH_SHORT).show()
        }

        observeData()
        viewModel.loadMovieDetails(movieId)
    }

    private fun observeData() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                is UiState.Success -> binding.progressBar.visibility = View.GONE
                is UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
            trailerKey = movie.trailerKey

            binding.tvTitle.text = movie.title
            binding.tvGenre.text = movie.genreText
            binding.tvCertification.text = movie.certification
            binding.tvDuration.text = movie.durationText
            binding.tvReleaseDate.text = movie.releaseDateText
            binding.tvLanguage.text = movie.languageText
            binding.tvOverview.text = movie.overview

            val imagePath = movie.backdropPath ?: movie.posterPath
            val imageUrl = "https://image.tmdb.org/t/p/w780$imagePath"

            Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.ivBackdrop)

            castAdapter.submitList(movie.cast)

            binding.btnTrailer.visibility =
                if (movie.trailerKey.isNullOrBlank()) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}