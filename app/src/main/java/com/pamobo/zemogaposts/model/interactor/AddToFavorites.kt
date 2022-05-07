package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.FavoritePostRepository
import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class AddToFavorites(private val repository: FavoritePostRepository) {

    suspend operator fun invoke(aPost: Post) = repository.addToFavorites(aPost)

}