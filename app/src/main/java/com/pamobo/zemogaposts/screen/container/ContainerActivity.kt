package com.pamobo.zemogaposts.screen.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.pamobo.zemogaposts.R
import com.pamobo.zemogaposts.databinding.ActivityContainerBinding
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.screen.postdetail.POST_KEY

class ContainerActivity : AppCompatActivity(), Router {

    private var _binding: ActivityContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun navigateToPostDetail(tappedPost: Post) {
        val data = bundleOf(POST_KEY to tappedPost)
        findNavController(R.id.nav_host_fragment).navigate(
            R.id.action_postListFragment_to_postDetailFragment,
            data
        )
    }

    override fun navigateUp() {
        findNavController(R.id.nav_host_fragment).navigateUp()
    }
}