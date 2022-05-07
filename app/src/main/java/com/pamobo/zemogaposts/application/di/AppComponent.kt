package com.pamobo.zemogaposts.application.di

import android.content.Context
import com.pamobo.zemogaposts.application.rest.CommentRestClient
import com.pamobo.zemogaposts.application.rest.PostRestClient
import com.pamobo.zemogaposts.application.rest.UserRestClient
import com.pamobo.zemogaposts.model.room.PostDAO
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RoomModule::class])
interface AppComponent {

    fun provideContext(): Context

    fun providePostClient(): PostRestClient

    fun provideUserClient(): UserRestClient

    fun provideCommentClient(): CommentRestClient

    fun providePostDAO(): PostDAO

}
