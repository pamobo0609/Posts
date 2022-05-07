package com.pamobo.zemogaposts.model.data

import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
interface PostDataSource {

    suspend fun getPosts(): ArrayList<Post>

}
