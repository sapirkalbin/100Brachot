/*
package tmdb.arch.movieapp.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmdb.arch.movieapp.BuildConfig
import tmdb.arch.movieapp.domain.local.MoviesDao
import tmdb.arch.movieapp.domain.remote.MoviesService
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import java.util.concurrent.TimeUnit

val remoteModel
    get() = module {
        single { MoviesRepository(moviesService, get<MoviesDao>()) }
    }

private val httpClient
    get() = OkHttpClient.Builder().callTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(
            Interceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.API_KEY}")
                    .build()
                chain.proceed(newRequest)
            },
        ).build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient)
    .build()

private val moviesService = retrofit.create(MoviesService::class.java)
*/
