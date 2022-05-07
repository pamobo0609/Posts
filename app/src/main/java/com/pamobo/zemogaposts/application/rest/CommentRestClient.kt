package com.pamobo.zemogaposts.application.rest

import com.pamobo.zemogaposts.model.domain.Comment
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface CommentRestClient {

    @GET("/posts/{postId}/comments")
    suspend fun getCommentsForPost(@Path("postId") postId: Int): List<Comment>
}
