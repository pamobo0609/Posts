package com.pamobo.zemogaposts.screen.postlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pamobo.zemogaposts.R
import com.pamobo.zemogaposts.application.ApiStatus
import com.pamobo.zemogaposts.application.App
import com.pamobo.zemogaposts.application.ext.hide
import com.pamobo.zemogaposts.application.ext.shortSnackBar
import com.pamobo.zemogaposts.application.ext.show
import com.pamobo.zemogaposts.application.util.OnItemTapped
import com.pamobo.zemogaposts.databinding.FragmentPostListBinding
import com.pamobo.zemogaposts.model.domain.Post
import com.pamobo.zemogaposts.model.room.PostDAO
import com.pamobo.zemogaposts.screen.container.Router
import com.pamobo.zemogaposts.screen.postlist.adapter.PostAdapter
import com.pamobo.zemogaposts.screen.postlist.di.DaggerPostListComponent
import javax.inject.Inject

private const val IS_FAVORITES_KEY = "favs"

class PostListFragment : Fragment() {
    @Inject
    lateinit var factory: PostListViewModelFactory

    @Inject
    lateinit var dao: PostDAO

    private var containerActivity: Router? = null
    private var isFavorites: Boolean = false

    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PostListViewModelContract by activityViewModels<PostListViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFavorites = it.getBoolean(IS_FAVORITES_KEY)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            DaggerPostListComponent.builder()
                .appComponent((it.application as App).appComponent)
                .build()
                .inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We get the container activity to call the navigation component and navigate properly.
        containerActivity = (activity as? Router)

        // Adds the general aspects of the recycler view list.
        configureList()

        // Set up all general UI aspects of this specific fragment.
        setUpUi()

        // There's a different behavior is we're talking about favorites. When favorites is true, we'll
        // get the data from a database implementation, otherwise we go web.
        if (isFavorites) {
            viewModel.getFavoritesLiveData().observe(viewLifecycleOwner) {
                if (it.isNullOrEmpty()) {
                    binding.noItemsIndicator.show()
                } else {
                    binding.noItemsIndicator.hide()
                }
                binding.progressIndicator.hide()
                setUpList(it as ArrayList<Post>)
            }
        } else {
            viewModel.getPostsLiveData().observe(viewLifecycleOwner) {
                when(it.status) {
                    ApiStatus.SUCCESS -> {
                        binding.progressIndicator.hide()
                        it.data?.let { thePosts ->
                            if (thePosts.isNullOrEmpty()) {
                                binding.noItemsIndicator.show()
                            } else {
                                binding.noItemsIndicator.hide()
                            }
                            setUpList(thePosts)
                        }
                    }
                    ApiStatus.ERROR -> {
                        binding.progressIndicator.hide()
                    }
                    ApiStatus.LOADING -> {
                        binding.noItemsIndicator.hide()
                        binding.progressIndicator.show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureList() {
        binding.posts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setUpList(posts: ArrayList<Post>) {
        binding.posts.apply {
            adapter = PostAdapter(posts, null, object : OnItemTapped<Post> {
                override fun onItemTapped(tappedItem: Post, adapterPosition: Int) {
                    containerActivity?.navigateToPostDetail(tappedItem)
                }
            })
        }
    }

    private fun setUpUi() {
        // When we are looking at favorite posts, these are stored in a database. We clear them from there.
        binding.deletePosts.setOnClickListener {
            if (isFavorites) {
                viewModel.onDeleteFavoritePostsTap()
                shortSnackBar(getString(R.string.favorites_deleted))
            } else {
                viewModel.onDeleteInMemoryPostsTap()
                shortSnackBar(R.string.posts_deleted)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(isFavorites: Boolean) =
            PostListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_FAVORITES_KEY, isFavorites)
                }
            }
    }
}