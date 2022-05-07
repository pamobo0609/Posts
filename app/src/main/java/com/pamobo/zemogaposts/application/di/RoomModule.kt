package com.pamobo.zemogaposts.application.di

import android.content.Context
import androidx.room.Room
import com.pamobo.zemogaposts.model.room.PostDAO
import com.pamobo.zemogaposts.model.room.PostsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): PostsDatabase = Room
        .databaseBuilder(context, PostsDatabase::class.java, "favorite-posts").build()

    @Singleton
    @Provides
    fun providePostDAO(database: PostsDatabase): PostDAO = database.postsDAO()

}
