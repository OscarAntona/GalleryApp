package com.antgut.myapplication.features.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.R
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.databinding.FragmentAlbumDialogBinding
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.SaveAlbumUseCase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<AlbumDialogViewModel>()
    private var _binding: FragmentAlbumDialogBinding? = null
    private val binding: FragmentAlbumDialogBinding
        get() = _binding!!

    private val args: AlbumDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDialogBinding.inflate(inflater, container, false)
        setUpView()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getAlbum(args.albumId)
    }
    private fun setUpView() {
        binding.apply {

            saveButton.setOnClickListener {
                val album = viewModel.uiModel.value?.album?.let { it1 -> Album(title = inputTitle.text.toString(), id = args.albumId, userId = it1.userId) }
                if (album != null) {
                    viewModel.saveAlbum(album)
                }
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
            deleteButton.setOnClickListener {
                viewModel.deleteAlbum(args.albumId)
                findNavController().navigateUp()
            }
        }
    }
    private fun setUpObserver() {
        val subscriber = Observer<AlbumDialogViewModel.UiModel> {
            when {
                it.album != null -> bind(it)
                it.isSaved -> {
                    requireActivity().findViewById<View>(R.id.fragment_container_view)
                        .showSnackBar("Se ha guardado correctamente")
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

    private fun bind(model: AlbumDialogViewModel.UiModel) {
        binding.apply {
            model.album?.let { album ->
                inputTitle.setText(album.title)
            }
        }
    }

}