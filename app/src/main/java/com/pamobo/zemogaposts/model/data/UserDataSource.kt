package com.pamobo.zemogaposts.model.data

import com.pamobo.zemogaposts.model.domain.User

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
interface UserDataSource {

    suspend fun getUser(userId: Int): User

}
