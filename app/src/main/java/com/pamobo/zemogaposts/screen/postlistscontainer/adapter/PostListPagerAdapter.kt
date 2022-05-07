package com.pamobo.zemogaposts.screen.postlistscontainer.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pamobo.zemogaposts.screen.postlist.PostListFragment

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostListPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> PostListFragment.newInstance(false)
        1 -> PostListFragment.newInstance(true)
        else -> throw IllegalStateException("Unknown fragment!")
    }
}
