package com.pamobo.zemogaposts.screen.postdetail

import androidx.lifecycle.MutableLiveData
import com.pamobo.zemogaposts.application.ApiResult
import com.pamobo.zemogaposts.model.domain.Comment
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.domain.User

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface PostDetailViewModelContract {

    fun getUserLiveData(userId: Int): MutableLiveData<ApiResult<User>>

    fun getCommentsLiveData(postId: Int): MutableLiveData<ApiResult<List<Comment>>>

    fun onAddToFavoritesTap(aPost: Post)

    fun onRemoveFromFavoritesTap(aPost: Post)

}
