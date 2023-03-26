package com.antgut.myapplication.features.user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.antgut.myapplication.R
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.hideWithDelay
import com.antgut.myapplication.app.extensions.showWithDelay
import com.antgut.myapplication.databinding.FragmentUserListBinding
import com.antgut.myapplication.features.user.presentation.adapter.UserListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var binding: FragmentUserListBinding? = null
    private val userAdapter = UserListAdapter()
    private val viewModel by viewModels<UserListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadAlbums()
    }

    private fun setupView() {
        binding?.apply {
            userList.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                skeleton = applySkeleton(R.layout.view_item_album)
            }
        }
    }

    private fun setupObservers() {
        val userListSubscriber =
            Observer<UserListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiState.error?.let {
                        ErrorApp.DataError
                    } ?: run {
                        userAdapter.submitList(uiState.userList)
                        userAdapter.setOnClickItem {
                            navigateToAlbum(it)
                        }
                    }
                }

            }
        viewModel.uiState.observe(viewLifecycleOwner, userListSubscriber)
    }

    private fun navigateToAlbum(userId: Int) {
        findNavController().navigate(
            UserListFragmentDirections.actionUserListFragmentToAlbumsListFragment(userId)
        )
    }
}