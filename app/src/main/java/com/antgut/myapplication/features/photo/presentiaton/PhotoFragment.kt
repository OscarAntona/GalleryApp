package com.antgut.myapplication.features.photo.presentiaton

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
import com.antgut.myapplication.databinding.FragmentPhotoListBinding
import com.antgut.myapplication.features.photo.presentiaton.adapter.PhotoListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var _binding: FragmentPhotoListBinding? = null
    private val binding: FragmentPhotoListBinding
        get() = _binding!!
    private val photoAdapter = PhotoListAdapter()
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadAllPhotos()
    }

    private fun setupView() {
        binding.apply {
            layoutToolbar.viewToolbar.title = "Photos"
            layoutToolbar.viewToolbar.apply {
                navigationIcon = null
            }
            photoList.apply {
                adapter = photoAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                skeleton = applySkeleton(R.layout.view_item_photo)
            }
        }
    }

    private fun setupObservers() {
        val photoSubscriber =
            Observer<PhotoViewModel.UiModel> { uiModel ->
                if (uiModel.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiModel.error?.let {
                        ErrorApp.DataError
                    } ?: run {
                        photoAdapter.submitList(uiModel.photoList)
                        photoAdapter.setOnClickItem {
                            navigateToPhotoDetail(it)
                        }
                        photoAdapter.onLongClickItem {
                            navigateToPhotoDialog(it)
                        }
                    }
                }

            }
        viewModel.uiModel.observe(viewLifecycleOwner, photoSubscriber)
    }

    private fun navigateToPhotoDetail(photoId: Int) {
        findNavController().navigate(
            PhotoFragmentDirections.actionPhotoFragmentToPhotoDetailFragment(photoId)
        )
    }

    private fun navigateToPhotoDialog(photoId: Int) {
        findNavController().navigate(
            PhotoFragmentDirections.actionPhotoFragmentToPhotoDialogFragment(photoId)
        )
    }
}