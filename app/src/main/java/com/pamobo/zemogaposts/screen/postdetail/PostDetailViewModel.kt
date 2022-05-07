package com.pamobo.zemogaposts.screen.postdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pamobo.zemogaposts.application.ApiResult
import com.pamobo.zemogaposts.model.domain.Comment
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.domain.User
import com.pamobo.zemogaposts.model.interactor.AddToFavorites
import com.pamobo.zemogaposts.model.interactor.GetComments
import com.pamobo.zemogaposts.model.interactor.GetUser
import com.pamobo.zemogaposts.model.interactor.RemoveFromFavorites
import kotlinx.coroutines.launch

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class PostDetailViewModel(
    private val getUser: GetUser,
    private val getComments: GetComments,
    private val addToFavorites: AddToFavorites,
    private val removeFromFavorites: RemoveFromFavorites
) : ViewModel(), PostDetailViewModelContract {

    private var getUserLiveData: MutableLiveData<ApiResult<User>>? = MutableLiveData()
    private var getCommentsLiveData: MutableLiveData<ApiResult<List<Comment>>>? = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getUserLiveData = null
        getCommentsLiveData = null
    }

    override fun getUserLiveData(userId: Int): MutableLiveData<ApiResult<User>> {
        if (null == getUserLiveData?.value) {
            getUserLiveData?.postValue(ApiResult.Loading())

            viewModelScope.launch {
                val aUser = getUser.invoke(userId)
                getUserLiveData?.postValue(ApiResult.Success(aUser))
            }

        }
        return getUserLiveData!!
    }

    override fun getCommentsLiveData(postId: Int): MutableLiveData<ApiResult<List<Comment>>> {
        if (null == getCommentsLiveData?.value) {
            getCommentsLiveData?.postValue(ApiResult.Loading())
            viewModelScope.launch {
                val theComments = getComments.invoke(postId)
                getCommentsLiveData?.postValue(ApiResult.Success(theComments))
            }
        }

        return getCommentsLiveData!!
    }

    override fun onAddToFavoritesTap(aPost: Post) {
        viewModelScope.launch {
            addToFavorites.invoke(aPost)
        }
    }

    override fun onRemoveFromFavoritesTap(aPost: Post) {
        viewModelScope.launch {
            removeFromFavorites.invoke(aPost)
        }
    }

}
