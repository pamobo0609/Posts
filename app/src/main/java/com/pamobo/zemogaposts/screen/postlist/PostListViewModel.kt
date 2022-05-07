package com.pamobo.zemogaposts.screen.postlist

import androidx.lifecycle.*
import com.pamobo.zemogaposts.application.ApiResult
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.interactor.ClearFavorites
import com.pamobo.zemogaposts.model.interactor.GetFavorites
import com.pamobo.zemogaposts.model.interactor.GetPosts
import kotlinx.coroutines.launch

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostListViewModel(
    private val getPosts: GetPosts,
    private val getFavorites: GetFavorites,
    private val clearFavorites: ClearFavorites
) : ViewModel(),
    PostListViewModelContract {

    private var getPostsLiveData: MutableLiveData<ApiResult<ArrayList<Post>>>? = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        getPostsLiveData = null
    }

    override fun getPostsLiveData(): MutableLiveData<ApiResult<ArrayList<Post>>> {
        if (null == getPostsLiveData?.value) {
            getPostsLiveData?.postValue(ApiResult.Loading())

            viewModelScope.launch {
                getFavoritesFlow().collect { favorites ->
                    val posts = getPosts.invoke()
                    posts.forEachIndexed { index, post ->
                        favorites.forEach { favoritePost ->
                            if (post.id == favoritePost.id) {
                                posts[index].apply { isFavorite = true }
                            }
                        }
                    }

                    posts.sortByDescending { item -> item.isFavorite }

                    getPostsLiveData?.postValue(ApiResult.Success(posts))
                }
            }

        }
        return getPostsLiveData!!
    }

    override fun getFavoritesLiveData(): LiveData<List<Post>> =
        getFavoritesFlow().asLiveData(viewModelScope.coroutineContext)

    override fun onDownloadPostsTap() {
        getPostsLiveData?.postValue(ApiResult.Loading())

        viewModelScope.launch {
            getFavoritesFlow().collect { favorites ->

                getPostsLiveData?.let {
                    val posts = it.value?.data ?: getPosts.invoke()
                    posts.forEachIndexed { index, post ->
                        favorites.forEach { favoritePost ->
                            if (post.id == favoritePost.id) {
                                posts[index].apply { isFavorite = true }
                            }
                        }
                    }

                    posts.sortByDescending { item -> item.isFavorite }

                    getPostsLiveData?.postValue(ApiResult.Success(posts))
                }
            }
        }
    }

    override fun onDeleteInMemoryPostsTap() {
        getPostsLiveData?.postValue(ApiResult.Success(arrayListOf()))
    }

    override fun onDeleteFavoritePostsTap() {
        viewModelScope.launch {
            clearFavorites.invoke()
            getPostsLiveData?.value?.data?.let { currentList ->
                getPostsLiveData?.postValue(ApiResult.Success(currentList.apply {
                    map {
                        it.isFavorite = false
                    }
                }))
            }
        }
    }

    private fun getFavoritesFlow() = getFavorites.invoke()

}