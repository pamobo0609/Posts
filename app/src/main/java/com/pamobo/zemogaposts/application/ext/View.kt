package com.pamobo.zemogaposts.application.ext

import android.view.View

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 6/5/22.
 *
 */
fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}