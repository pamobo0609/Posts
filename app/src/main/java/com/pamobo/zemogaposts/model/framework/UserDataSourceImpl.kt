package com.pamobo.zemogaposts.model.framework

import com.pamobo.zemogaposts.application.rest.UserRestClient
import com.pamobo.zemogaposts.model.data.UserDataSource

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class UserDataSourceImpl(private val client: UserRestClient) : UserDataSource {

    override suspend fun getUser(userId: Int) = client.getUser(userId)

}
