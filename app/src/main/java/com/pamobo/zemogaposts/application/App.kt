package com.pamobo.zemogaposts.application

import android.app.Application
import com.pamobo.zemogaposts.application.di.AppComponent
import com.pamobo.zemogaposts.application.di.AppModule
import com.pamobo.zemogaposts.application.di.DaggerAppComponent

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class App : Application() {

    val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()

    }
}
