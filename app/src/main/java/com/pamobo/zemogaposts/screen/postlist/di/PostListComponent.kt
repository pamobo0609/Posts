package com.pamobo.zemogaposts.screen.postlist.di

import com.pamobo.zemogaposts.application.di.AppComponent
import com.pamobo.zemogaposts.application.scope.ActivityScope
import com.pamobo.zemogaposts.screen.postdetail.PostDetailFragment
import com.pamobo.zemogaposts.screen.postlist.PostListFragment
import com.pamobo.zemogaposts.screen.postlistscontainer.PostListsContainerFragment
import dagger.Component

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [PostListModule::class])
interface PostListComponent {

    fun inject(fragment: PostListFragment)

    fun inject(fragment: PostListsContainerFragment)

}