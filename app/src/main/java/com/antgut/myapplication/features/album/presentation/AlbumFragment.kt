package com.antgut.myapplication.features.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.antgut.myapplication.R
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.hideWithDelay
import com.antgut.myapplication.app.extensions.showWithDelay
import com.antgut.myapplication.app.presentation.errors.ErrorAppHandler
import com.antgut.myapplication.databinding.FragmentAlbumListBinding
import com.antgut.myapplication.features.album.presentation.adapter.AlbumListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    @Inject
    lateinit var errorAppHandler: ErrorAppHandler
    private var skeleton: Skeleton? = null
    private var _binding: FragmentAlbumListBinding? = null
    private val binding: FragmentAlbumListBinding
        get() = _binding!!
    private val albumAdapter = AlbumListAdapter()
    private val viewModel by viewModels<AlbumViewModel>()
    private val args: AlbumFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumListBinding.inflate(inflater)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadAlbums()
    }

    private fun setupView() {
        binding.apply {
            layoutToolbar.viewToolbar.title = "Albums"
            layoutToolbar.viewToolbar.apply {
                navigationIcon = null
            }
            albumList.apply {
                adapter = albumAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                skeleton = applySkeleton(R.layout.view_item_album)
            }
        }
    }

    private fun setupObservers() {
        val albumSubscriber =
            Observer<AlbumViewModel.UiModel> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiState.error?.let {
                        showError(uiState.error)
                    } ?: run {
                        albumAdapter.submitList(uiState.albumList)
                        albumAdapter.setOnClickItem {
                            navigateToPhoto(it)
                        }
                        albumAdapter.onLongClickItem {
                            navigateToDialog(it)
                        }

                    }
                }
                binding.apply {
                    floatingActionButton.setOnClickListener {
                        navigateToAddAlbumDialog(args.userId)
                    }
                }
            }
        viewModel.uiModel.observe(viewLifecycleOwner, albumSubscriber)
    }

    private fun navigateToDialog(albumId: Int) {
        findNavController().navigate(
            AlbumFragmentDirections.actionAlbumFragmentToAlbumDialogFragment(albumId)
        )
    }

    private fun navigateToPhoto(albumId: Int) {
        findNavController().navigate(
            AlbumFragmentDirections.actionAlbumFragmentToPhotoListFragment(albumId)
        )
    }

    private fun navigateToAddAlbumDialog(userId: Int) {
        findNavController().navigate(
            AlbumFragmentDirections.actionAlbumFragmentToAlbumAddDialogFragment(userId)
        )
    }

    private fun showError(errorApp: ErrorApp) {
        errorAppHandler.navigateToError(errorApp)
    }
}
