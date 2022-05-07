package com.pamobo.zemogaposts.application.rest

import com.pamobo.zemogaposts.model.domain.Post
import retrofit2.http.GET

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
interface PostRestClient {

    @GET("/posts")
    suspend fun getPosts(): ArrayList<Post>

}
