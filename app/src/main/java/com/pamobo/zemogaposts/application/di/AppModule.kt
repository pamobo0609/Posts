package com.pamobo.zemogaposts.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Module
class AppModule(private val app: Context) {

    @Singleton
    @Provides
    fun provideContext() = app

}