package com.pamobo.zemogaposts.model.framework

import com.pamobo.zemogaposts.application.rest.CommentRestClient
import com.pamobo.zemogaposts.model.data.CommentDataSource

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class CommentDataSourceImpl(private val client: CommentRestClient) : CommentDataSource {

    override suspend fun getComments(postId: Int) = client.getCommentsForPost(postId)

}
