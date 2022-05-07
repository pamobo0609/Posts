package com.pamobo.zemogaposts.model.data

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostRepository(private val dataSource: PostDataSource) {

    suspend fun getPosts() = dataSource.getPosts()

}
