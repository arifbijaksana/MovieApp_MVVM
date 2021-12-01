package com.arifbm.movieapp.data.local

import javax.inject.Inject


class FavoriteMovieRepository @Inject constructor(
    private  val favoriteMovieDao: FavoriteMovieDao
){
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie)= favoriteMovieDao.addToFavorite(favoriteMovie)
    fun getFavoriteMovies()= favoriteMovieDao.getFavoriteMovie()
    suspend fun checkMovie(id:String)=favoriteMovieDao.checkedMovie(id)
    suspend fun removeFromFavorite(id: String){
        favoriteMovieDao.removeMovie(id)
    }
}