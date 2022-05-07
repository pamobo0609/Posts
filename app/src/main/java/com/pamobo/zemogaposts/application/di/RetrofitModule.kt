package com.pamobo.zemogaposts.application.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pamobo.zemogaposts.application.rest.CommentRestClient
import com.pamobo.zemogaposts.application.rest.PostRestClient
import com.pamobo.zemogaposts.application.rest.UserRestClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providePostClient(retrofit: Retrofit): PostRestClient =
        retrofit.create(PostRestClient::class.java)

    @Singleton
    @Provides
    fun provideUserClient(retrofit: Retrofit): UserRestClient =
        retrofit.create(UserRestClient::class.java)

    @Singleton
    @Provides
    fun provideCommentClient(retrofit: Retrofit): CommentRestClient =
        retrofit.create(CommentRestClient::class.java)

    @Reusable
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

}
