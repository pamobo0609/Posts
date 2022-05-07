package com.pamobo.zemogaposts.screen.postdetail

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pamobo.zemogaposts.R
import com.pamobo.zemogaposts.application.ApiStatus
import com.pamobo.zemogaposts.application.App
import com.pamobo.zemogaposts.application.ext.hide
import com.pamobo.zemogaposts.application.ext.show
import com.pamobo.zemogaposts.databinding.FragmentPostDetailBinding
import com.pamobo.zemogaposts.model.domain.Comment
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.domain.User
import com.pamobo.zemogaposts.screen.container.Router
import com.pamobo.zemogaposts.screen.postdetail.di.DaggerPostDetailComponent
import com.pamobo.zemogaposts.screen.postlist.adapter.PostAdapter
import javax.inject.Inject

const val POST_KEY = "post"

class PostDetailFragment : Fragment() {

    @Inject
    lateinit var factory: PostDetailViewModelFactory

    private val viewModel: PostDetailViewModelContract by viewModels<PostDetailViewModel> { factory }

    private var containerActivity: Router? = null

    private var _binding: FragmentPostDetailBinding? = null
    private val binding get() = _binding!!

    private var tappedPost: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tappedPost = it.getParcelable(POST_KEY)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            DaggerPostDetailComponent
                .builder()
                .appComponent((it.application as App).appComponent)
                .build()
                .inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We get the container activity to call the navigation component and navigate properly.
        containerActivity = (activity as? Router)

        // Toggles the favorite button
        configureToolbar()

        // Configure the recyclerview general aspects
        configureList()

        // Set the data we already have
        setUpPostData()

        // Get the user who made the post
        tappedPost?.let { thePost ->
            viewModel.getUserLiveData(thePost.userId).observe(viewLifecycleOwner) {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        binding.loadingUserIndicator.hide()
                        it.data?.let { theUser ->
                            setUpUserData(theUser)
                        }
                    }
                    ApiStatus.ERROR -> binding.loadingUserIndicator.hide()
                    ApiStatus.LOADING -> binding.loadingUserIndicator.show()
                }
            }

            viewModel.getCommentsLiveData(thePost.id).observe(viewLifecycleOwner) {
                when (it.status) {
                    ApiStatus.SUCCESS -> {
                        binding.loadingCommentsIndicator.hide()
                        it.data?.let { theComments ->
                            if (theComments.isNullOrEmpty()) {
                                binding.noItemsIndicator.show()
                            } else {
                                setUpCommentsData(theComments)
                            }
                        }
                    }
                    ApiStatus.ERROR -> {
                        binding.loadingCommentsIndicator.hide()
                        binding.noItemsIndicator.show()
                    }
                    ApiStatus.LOADING -> binding.loadingCommentsIndicator.show()
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            containerActivity?.navigateUp()
        }

        binding.toolbar.navigationIcon?.setTint(Color.WHITE)

        tappedPost?.let {
            binding.toolbar.menu.findItem(R.id.action_favorite).apply {
                isVisible = !it.isFavorite
                setOnMenuItemClickListener {
                    tappedPost?.let { thePost ->
                        viewModel.onAddToFavoritesTap(thePost.apply {
                            isFavorite = true
                        })
                        toggleFavoriteButton(false)
                    }
                    true
                }
            }

            binding.toolbar.menu.findItem(R.id.action_remove_favorite).apply {
                isVisible = it.isFavorite
                setOnMenuItemClickListener {
                    tappedPost?.let { thePost ->
                        viewModel.onRemoveFromFavoritesTap(thePost.apply {
                            isFavorite = false
                        })
                        toggleFavoriteButton(true)
                    }
                    true
                }
            }
        }
    }

    private fun toggleFavoriteButton(isCurrentPostFavorite: Boolean) {
        binding.toolbar.menu.findItem(R.id.action_remove_favorite).isVisible =
            !isCurrentPostFavorite
        binding.toolbar.menu.findItem(R.id.action_favorite).isVisible =
            isCurrentPostFavorite
    }

    private fun configureList() {
        binding.comments.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpPostData() {
        tappedPost?.let {
            binding.description.text = it.body
        }
    }

    private fun setUpUserData(theUser: User) {
        binding.name.text = getString(R.string.name_placeholder, theUser.name)
        binding.email.text = getString(R.string.email_placeholder, theUser.email)
        binding.phone.text = getString(R.string.phone_placeholder, theUser.phone)
        binding.website.text = getString(R.string.website_placeholder, theUser.website)
    }

    private fun setUpCommentsData(theComments: List<Comment>) {
        binding.comments.adapter =
            PostAdapter(null, theComments, null)
    }

    companion object {

        @JvmStatic
        fun newInstance(tappedPost: Post) =
            PostDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(POST_KEY, tappedPost)
                }
            }
    }
}