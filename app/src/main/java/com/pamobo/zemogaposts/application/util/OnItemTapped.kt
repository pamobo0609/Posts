package com.pamobo.zemogaposts.application.util

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
interface OnItemTapped<T> {

    fun onItemTapped(tappedItem: T, adapterPosition: Int)

}
