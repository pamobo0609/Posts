package com.pamobo.zemogaposts.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
@Database(entities = [Post::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postsDAO(): PostDAO

}