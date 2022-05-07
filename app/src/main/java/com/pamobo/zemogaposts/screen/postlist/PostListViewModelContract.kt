package com.pamobo.zemogaposts.screen.postlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pamobo.zemogaposts.application.ApiResult
import com.pamobo.zemogaposts.model.domain.Post
import kotlinx.coroutines.flow.Flow

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
interface PostListViewModelContract {

    fun getPostsLiveData(): MutableLiveData<ApiResult<ArrayList<Post>>>

    fun getFavoritesLiveData(): LiveData<List<Post>>

    fun onDownloadPostsTap()

    fun onDeleteInMemoryPostsTap()

    fun onDeleteFavoritePostsTap()

}
