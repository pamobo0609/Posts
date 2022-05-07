package com.pamobo.zemogaposts.model.interactor

import com.pamobo.zemogaposts.model.data.CommentRepository

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class GetComments(private val repository: CommentRepository) {

    suspend operator fun invoke(postId: Int) = repository.getComments(postId)

}
