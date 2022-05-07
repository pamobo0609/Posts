package com.pamobo.zemogaposts.application.rest

import com.pamobo.zemogaposts.model.domain.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface UserRestClient {

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): User

}
