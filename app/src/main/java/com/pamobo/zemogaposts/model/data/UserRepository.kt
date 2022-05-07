package com.pamobo.zemogaposts.model.data

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
class UserRepository(private val dataSource: UserDataSource) {

    suspend fun getUser(userId: Int) = dataSource.getUser(userId)

}