package com.example.movieapp.fragments.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieListAdapter
import com.example.movieapp.data.MovieUiState
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.enums.MovieType
import com.example.movieapp.fragments.BaseFragment
import kotlinx.coroutines.launch


class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var movieAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar, getString(R.string.titlePopularMovies))
        initMenuToolBar(R.menu.menu_movie)
        initObserve()
        movieViewModel.getMovies(MovieType.Popular)
    }

    private fun initObserve() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieViewModel.movieLiveData.collect { uiState ->
                    when(uiState) {
                        is MovieUiState.Success -> {
                            val movies = uiState.movies
                            movieAdapter = MovieListAdapter(
                                movies,
                                onItemClick = { selectedProduct ->
                                    val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(selectedProduct)
                                    findNavController().navigate(action)
                                }
                            )
                            binding.recyclerViewMovie.adapter = movieAdapter
                        }
                        is MovieUiState.Error -> {
                            Toast.makeText(context, "Error: ${uiState.exception}", Toast.LENGTH_LONG).show()
                        }
                        is MovieUiState.Loading -> {
                            if (uiState.isLoad)
                                showCustomProgressDialog()
                            else
                                hideProgressDialog()
                        }
                    }
                }
            }
        }
    }
}