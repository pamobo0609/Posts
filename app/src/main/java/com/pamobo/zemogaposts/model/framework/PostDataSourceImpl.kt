package com.pamobo.zemogaposts.model.framework

import com.pamobo.zemogaposts.application.rest.PostRestClient
import com.pamobo.zemogaposts.model.data.PostDataSource

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostDataSourceImpl(private val client: PostRestClient) : PostDataSource {

    override suspend fun getPosts() = client.getPosts()

}