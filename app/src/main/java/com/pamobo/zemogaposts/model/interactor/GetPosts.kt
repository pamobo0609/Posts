package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.PostRepository

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class GetPosts(private val repository: PostRepository) {

    suspend operator fun invoke() = repository.getPosts()

}