package com.arifbm.movieapp.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arifbm.movieapp.data.local.FavoriteMovie
import com.arifbm.movieapp.data.local.FavoriteMovieDatabase
import com.arifbm.movieapp.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
                .Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(20L, TimeUnit.SECONDS)
                .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext app:Context
    )= Room.databaseBuilder(
        app,
        FavoriteMovieDatabase::class.java,
        "movie_db"
    ).build()

    @Provides
    @Singleton
    fun provideFavMovieDao(db:FavoriteMovieDatabase) = db.getFavoriteMovieDao()
}