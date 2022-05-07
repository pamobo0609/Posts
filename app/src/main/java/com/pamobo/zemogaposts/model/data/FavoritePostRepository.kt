package com.pamobo.zemogaposts.model.data

import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class FavoritePostRepository(private val dataSource: FavoritePostDataSource) {

    fun getFavorites() = dataSource.getFavorites()

    suspend fun addToFavorites(aPost: Post) = dataSource.addToFavorites(aPost)

    suspend fun removeFromFavorites(post: Post) = dataSource.removeFromFavorites(post)

    suspend fun clearFavorites() = dataSource.clearFavorites()

}
