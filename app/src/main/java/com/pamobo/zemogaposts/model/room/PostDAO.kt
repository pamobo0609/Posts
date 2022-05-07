package com.pamobo.zemogaposts.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pamobo.zemogaposts.model.domain.Post
import kotlinx.coroutines.flow.Flow

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
@Dao
interface PostDAO {

    @Query("SELECT * FROM posts")
    fun getFavorites(): Flow<List<Post>>

    @Insert
    suspend fun addToFavorites(aPost: Post)

    @Delete
    suspend fun removeFromFavorites(aPost: Post)

    @Query("DELETE FROM posts")
    suspend fun clearFavorites()

}
