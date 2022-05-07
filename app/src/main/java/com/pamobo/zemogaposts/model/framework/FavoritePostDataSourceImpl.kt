package com.pamobo.zemogaposts.model.framework

import com.pamobo.zemogaposts.model.data.FavoritePostDataSource
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.room.PostDAO
import javax.inject.Inject

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class FavoritePostDataSourceImpl @Inject constructor(val dao: PostDAO) : FavoritePostDataSource {

    override fun getFavorites() = dao.getFavorites()

    override suspend fun addToFavorites(aPost: Post) = dao.addToFavorites(aPost)

    override suspend fun removeFromFavorites(post: Post) = dao.removeFromFavorites(post)

    override suspend fun clearFavorites() = dao.clearFavorites()

}
