package com.pamobo.zemogaposts.screen.postlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pamobo.zemogaposts.application.util.OnItemTapped
import com.pamobo.zemogaposts.databinding.ItemPostBinding
import com.pamobo.zemogaposts.model.domain.Comment
import com.pamobo.zemogaposts.model.domain.Post

/**
 * Zemoga Posts
 *
 * Created by Jose Monge on 5/5/22.
 *
 */
class PostAdapter(
    private val posts: ArrayList<Post>?,
    private val comments: List<Comment>?,
    private val postTapListener: OnItemTapped<Post>?
) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        when {
            null != posts -> holder.bind(posts[position])
            null != comments -> holder.bind(comments[position])
        }
    }

    override fun getItemCount() = when {
        null != posts -> posts.size
        null != comments -> comments.size
        else -> 0
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(aPost: Post) {
            binding.root.setOnClickListener {
                postTapListener?.onItemTapped(aPost, adapterPosition)
            }

            binding.postTitle.text = aPost.title
            binding.favoriteIndicator.visibility =
                if (aPost.isFavorite) View.VISIBLE else View.GONE
        }

        fun bind(aComment: Comment) {
            binding.postTitle.text = aComment.body
            binding.favoriteIndicator.visibility = View.GONE
        }

    }

}
