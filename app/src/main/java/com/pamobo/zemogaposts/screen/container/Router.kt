package com.pamobo.zemogaposts.screen.container

import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface Router {

    fun navigateToPostDetail(tappedPost: Post)

    fun navigateUp()

}
