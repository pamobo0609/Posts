package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.FavoritePostRepository

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class GetFavorites(private val favoritePostRepository: FavoritePostRepository) {

    operator fun invoke() = favoritePostRepository.getFavorites()

}