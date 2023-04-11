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
class PhotoAddDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<PhotoAddDialogViewModel>()
    private var _binding: FragmentPhotoDialogBinding? = null
    private val binding: FragmentPhotoDialogBinding
        get() = _binding!!
    private val args: PhotoAddDialogFragmentArgs by navArgs()

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
    }

    private fun setUpView() {
        binding.apply {

            saveButton.setOnClickListener {
                viewModel.savePhoto(
                    photo = Photo(
                        albumId = args.albumId,
                        title = inputTitle.text.toString(),
                        thumbnailUrl = inputThumbnail.text.toString(),
                        url = inputUrl.text.toString()
                    )
                )
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
            deleteButton.visibility = View.GONE
        }
    }

    private fun setUpObserver() {
        val subscriber = Observer<PhotoAddDialogViewModel.UiModel> {
            when {
                it.isSaved -> {
                    view?.showSnackBar("Se ha guardado correctamente")
                }
                else -> {
                    view?.showSnackBar("Se ha producido un error")
                }
            }
        }
        viewModel.uiModel.observe(viewLifecycleOwner, subscriber)
    }
}