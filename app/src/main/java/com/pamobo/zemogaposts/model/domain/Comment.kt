package com.pamobo.zemogaposts.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
@Parcelize
data class Comment(
    val postId: Int = -1,
    val id: Int = -1,
    val name: String = "",
    val email: String = "",
    val body: String = ""
) : Parcelable