package com.pamobo.zemogaposts.model.data

import com.pamobo.zemogaposts.model.domain.Comment

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface CommentDataSource {

    suspend fun getComments(postId: Int): List<Comment>

}
