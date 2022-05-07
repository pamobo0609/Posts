package com.pamobo.zemogaposts.screen.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pamobo.zemogaposts.model.interactor.AddToFavorites
import com.pamobo.zemogaposts.model.interactor.GetComments
import com.pamobo.zemogaposts.model.interactor.GetUser
import com.pamobo.zemogaposts.model.interactor.RemoveFromFavorites

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class PostDetailViewModelFactory(
    private val getUser: GetUser,
    private val getComments: GetComments,
    private val addToFavorites: AddToFavorites,
    private val removeFromFavorites: RemoveFromFavorites
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PostDetailViewModel(getUser, getComments, addToFavorites, removeFromFavorites) as T

}