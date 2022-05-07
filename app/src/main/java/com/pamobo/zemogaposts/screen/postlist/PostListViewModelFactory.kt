package com.pamobo.zemogaposts.screen.postlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamobo.zemogaposts.model.interactor.*

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostListViewModelFactory(
    private val getPosts: GetPosts,
    private val getFavorites: GetFavorites,
    private val clearFavorites: ClearFavorites
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostListViewModel(
            getPosts,
            getFavorites,
            clearFavorites
        ) as T
    }
}