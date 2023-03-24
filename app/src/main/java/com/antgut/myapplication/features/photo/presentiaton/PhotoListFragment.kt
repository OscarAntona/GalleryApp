package com.antgut.myapplication.features.photo.presentiaton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.R
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.hideWithDelay
import com.antgut.myapplication.app.extensions.showWithDelay
import com.antgut.myapplication.databinding.FragmentPhotoListBinding
import com.antgut.myapplication.features.photo.presentiaton.adapter.PhotoListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var binding: FragmentPhotoListBinding? = null
    private val photoAdapter = PhotoListAdapter()
    private val viewModel by viewModels<PhotoListViewModel>()
    private val args: PhotoListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoListBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadPhotos(args.albumId)
    }

    private fun setupView() {
        binding?.apply {
            photoList.apply {
                adapter = photoAdapter
                skeleton = applySkeleton(R.layout.view_item_photo)
            }
        }
    }

    private fun setupObservers() {
        val photoListSubscriber =
            Observer<PhotoListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiState.error?.let {
                        ErrorApp.DataError
                    } ?: run {
                        photoAdapter.submitList(uiState.photoList)
                        photoAdapter.setOnClickItem {
                            //navigateToPhotoDetail(it)
                        }
                    }
                }

            }
        viewModel.uiState.observe(viewLifecycleOwner, photoListSubscriber)
    }

    /*private fun navigateToPhotoDetail(photoId: Int) {
        findNavController().navigate(
            PhotoListFragmentDirections.actionPhotoListFragmentToPhotoDetail(photoId)
        )
    }*/
}