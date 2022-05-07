package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.FavoritePostRepository

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class ClearFavorites (private val favoritePostRepository: FavoritePostRepository){

    suspend operator fun invoke() = favoritePostRepository.clearFavorites()

}
