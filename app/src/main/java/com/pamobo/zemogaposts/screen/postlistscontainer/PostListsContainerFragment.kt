package com.pamobo.zemogaposts.screen.postlistscontainer

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.pamobo.zemogaposts.R
import com.pamobo.zemogaposts.application.App
import com.pamobo.zemogaposts.databinding.FragmentPostsListContainerBinding
import com.pamobo.zemogaposts.screen.postlist.PostListViewModel
import com.pamobo.zemogaposts.screen.postlist.PostListViewModelContract
import com.pamobo.zemogaposts.screen.postlist.PostListViewModelFactory
import com.pamobo.zemogaposts.screen.postlistscontainer.adapter.PostListPagerAdapter
import com.pamobo.zemogaposts.screen.postlist.di.DaggerPostListComponent
import javax.inject.Inject

class PostListsContainerFragment : Fragment() {

    @Inject
    lateinit var factory: PostListViewModelFactory

    private val viewModel: PostListViewModelContract by activityViewModels<PostListViewModel> { factory }

    private var _binding: FragmentPostsListContainerBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            DaggerPostListComponent
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
        // Inflate the layout for this fragment
        _binding = FragmentPostsListContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_reload -> viewModel.onDownloadPostsTap()
            }
            true
        }

        setUpUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUi() {
        setUpViewPager()
        setUpTabs()
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = PostListPagerAdapter(this)
    }

    private fun setUpTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.all)
                1 -> tab.text = getString(R.string.favorites)
            }
        }.attach()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            PostListsContainerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}