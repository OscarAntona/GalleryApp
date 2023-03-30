package com.antgut.myapplication.features.photo.presentiaton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.databinding.FragmentPhotoDialogBinding
import com.antgut.myapplication.features.photo.domain.Photo
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<PhotoDialogViewModel>()
    private var _binding: FragmentPhotoDialogBinding? = null
    private val binding: FragmentPhotoDialogBinding
        get() = _binding!!
    private val args: PhotoDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoDialogBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getPhoto(args.photoId)
    }

    private fun setUpView() {
        binding.apply {
            saveButton.setOnClickListener {
                val photo = viewModel.uiModel.value?.photo?.let { photo ->
                    Photo(
                        title = inputTitle.text.toString(),
                        id = args.photoId,
                        albumId = photo.albumId,
                        url = photo.url,
                        thumbnailUrl = photo.thumbnailUrl
                    )
                }
                if (photo != null) {
                    viewModel.savePhoto(photo)
                }
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
            deleteButton.setOnClickListener {
                viewModel.deletePhoto(args.photoId)
                findNavController().navigateUp()
            }
        }
    }

    private fun setUpObserver() {
        val subscriber = Observer<PhotoDialogViewModel.UiModel> {
            when {
                it.photo != null -> bind(it)
                it.isSaved -> {
                    view?.showSnackBar("Se ha guardado correctamente")
                }
                it.isSuccess -> {
                    view?.showSnackBar("Se ha borrado correctamente")
                    findNavController().navigateUp()
                }
                else -> {
                    view?.showSnackBar("Se ha producido un error")
                }
            }
        }
        viewModel.uiModel.observe(viewLifecycleOwner, subscriber)
    }

    private fun bind(model: PhotoDialogViewModel.UiModel) {
        binding.apply {
            model.photo?.let { photo ->
                inputTitle.setText(photo.title)
                inputUrl.setText(photo.url)
                inputThumbnail.setText(photo.thumbnailUrl)
            }
        }
    }


}