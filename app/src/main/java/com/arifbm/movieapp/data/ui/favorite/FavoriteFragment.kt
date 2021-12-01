package com.arifbm.movieapp.data.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arifbm.movieapp.data.local.FavoriteMovie
import com.arifbm.movieapp.data.remote.Movie
import com.arifbm.movieapp.data.ui.movie.MovieViewModel
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoriteBinding.bind(view)

        val adapter = FavoriteAdapter()

        viewModel.movies.observe(viewLifecycleOwner){
            adapter.setMovieList(it)
            binding.apply {
                rvMovie.setHasFixedSize(true)
                rvMovie.adapter = adapter
            }
        }

       adapter.setOnItemClickCallback(object :FavoriteAdapter.OnItemClickCallback{
           override fun onItemClick(favoriteMovie: FavoriteMovie) {
             val movie = Movie(
                 favoriteMovie.id_movie,
                 favoriteMovie.overview,
                 favoriteMovie.poster_path,
                 favoriteMovie.original_title
             )
               val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetails(movie)
               findNavController().navigate(action)
           }

       })
    }
}