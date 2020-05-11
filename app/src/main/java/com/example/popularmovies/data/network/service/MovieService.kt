package com.example.popularmovies.data.network.service

import com.example.popularmovies.data.network.model.MoviesListResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "abb8a511663375ea67357b449f833ffb"

interface MovieService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Deferred<MoviesListResponse>

    @GET("movie/popular")
    fun getMostPopularMovies(): Deferred<MoviesListResponse>

    fun getMovieDetails(id: Int)

    companion object {
        operator fun invoke(): MovieService {

            val requestInterceptor = Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }


}