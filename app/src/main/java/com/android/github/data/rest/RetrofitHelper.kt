package com.android.github.data.rest

import com.android.github.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    val apiGitHub: GitHubApi = createRetrofit().create(GitHubApi::class.java)

    private fun createRetrofit() = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build())
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun getInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}
