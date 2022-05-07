package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.FavoritePostRepository
import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class RemoveFromFavorites(private val favoritePostRepository: FavoritePostRepository) {

    suspend operator fun invoke(post: Post) = favoritePostRepository.removeFromFavorites(post)

}
