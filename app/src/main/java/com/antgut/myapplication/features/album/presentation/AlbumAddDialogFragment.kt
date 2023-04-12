package com.antgut.myapplication.features.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.databinding.FragmentAlbumDialogBinding
import com.antgut.myapplication.features.album.domain.Album
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumAddDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<AlbumAddDialogViewModel>()
    private var _binding: FragmentAlbumDialogBinding? = null
    private val binding: FragmentAlbumDialogBinding
        get() = _binding!!
    private val args: AlbumAddDialogFragmentArgs by navArgs()

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
    }

    private fun setUpView() {
        binding.apply {

            saveButton.setOnClickListener {
                viewModel.saveAlbum(
                    album = Album(
                        userId = args.userId,
                        title = inputTitle.text.toString()
                    )
                )
                findNavController().navigateUp()
            }
            deleteButton.visibility = View.GONE
        }
    }

    private fun setUpObserver() {
        val subscriber = Observer<AlbumAddDialogViewModel.UiModel> {
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