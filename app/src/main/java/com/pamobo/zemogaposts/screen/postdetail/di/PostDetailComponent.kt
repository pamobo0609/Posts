package com.pamobo.zemogaposts.screen.postdetail.di

import com.pamobo.zemogaposts.application.di.AppComponent
import com.pamobo.zemogaposts.application.scope.ActivityScope
import com.pamobo.zemogaposts.screen.postdetail.PostDetailFragment
import dagger.Component

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [PostDetailModule::class])
interface PostDetailComponent {

    fun inject(fragment: PostDetailFragment)

}