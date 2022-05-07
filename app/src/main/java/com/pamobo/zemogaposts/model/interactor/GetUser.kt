package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.UserRepository

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class GetUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: Int) = userRepository.getUser(userId)

}