package com.antgut.myapplication.features.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.antgut.myapplication.R
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.hideWithDelay
import com.antgut.myapplication.app.extensions.showWithDelay
import com.antgut.myapplication.databinding.FragmentAlbumListBinding
import com.antgut.myapplication.features.album.presentation.adapter.AlbumListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsListFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var binding: FragmentAlbumListBinding? = null
    private val albumAdapter = AlbumListAdapter()
    private val viewModel by viewModels<AlbumListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumListBinding.inflate(inflater)
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
            albumList.apply {
                adapter = albumAdapter
                skeleton = applySkeleton(R.layout.view_item_album,)
            }
        }
    }

    private fun setupObservers() {
        val albumFeedSubscriber =
            Observer<AlbumListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiState.error?.let {
                        ErrorApp.DataError
                    } ?: run {
                        albumAdapter.submitList(uiState.albumFeed)

                    }
                }

            }
        viewModel.uiState.observe(viewLifecycleOwner, albumFeedSubscriber)
    }

    private fun navigateToPhoto(albumId: Int) {
        findNavController().navigate(
            AlbumFeedFragmentDirections.actionToPhoto(albumId)
        )
    }
}