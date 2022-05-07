package com.pamobo.zemogaposts.model.data

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class CommentRepository(private val dataSource: CommentDataSource) {

    suspend fun getComments(postId: Int) = dataSource.getComments(postId)

}
