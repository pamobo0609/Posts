package com.pamobo.zemogaposts.model.data

import com.pamobo.zemogaposts.model.domain.Post
import kotlinx.coroutines.flow.Flow

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface FavoritePostDataSource {

    fun getFavorites(): Flow<List<Post>>

    suspend fun addToFavorites(aPost: Post)

    suspend fun removeFromFavorites(post: Post)

    suspend fun clearFavorites()

}
