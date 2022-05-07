package com.pamobo.zemogaposts.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Entity(tableName = "posts")
@Parcelize
data class Post(
    @PrimaryKey
    val id: Int = -1,
    val title: String = "",
    val body: String = "",
    val userId: Int = -1,
    var isFavorite: Boolean = false
) : Parcelable