package com.antgut.myapplication.features.photo.presentiaton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.app.extensions.hideWithDelay
import com.antgut.myapplication.app.extensions.loadUrl
import com.antgut.myapplication.app.extensions.showWithDelay
import com.antgut.myapplication.databinding.FragmentPhotoDetailBinding
import com.antgut.myapplication.features.photo.domain.Photo
import com.faltenreich.skeletonlayout.Skeleton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {
    private var skeleton: Skeleton? = null
    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding: FragmentPhotoDetailBinding
        get() = _binding!!
    private val viewModel by viewModels<PhotoDetailViewModel>()
    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDetailBinding.inflate(inflater)
        setupView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.loadPhoto(args.photoId)
    }

    private fun setupView() {
        binding.apply {
            layoutToolbar.viewToolbar.title = "Photo"
            layoutToolbar.viewToolbar.apply {
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun setupObservers() {
        val photoDetailSubscriber =
            Observer<PhotoDetailViewModel.UiModel> { uiModel ->
                if (uiModel.isLoading) {
                    skeleton?.showWithDelay()
                } else {
                    skeleton?.hideWithDelay()
                    uiModel.error?.let {
                        ErrorApp.DataError
                    } ?: run {
                        viewModel.uiModel.value?.photo?.let { bind(it) }
                    }
                }

            }
        viewModel.uiModel.observe(viewLifecycleOwner, photoDetailSubscriber)
    }

    private fun bind(photo: Photo) {
        binding.apply {
            photoUrl.apply {
                photoUrl.loadUrl(photo.url)
                labelTitlePhoto.text = photo.title
            }
        }
    }
}