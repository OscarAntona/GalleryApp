package com.antgut.myapplication.features.album.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.databinding.FragmentAlbumDialogBinding
import com.antgut.myapplication.features.album.domain.Album
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<AlbumDialogViewModel>()
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
    }

    private fun setUpView() {
        binding.apply {

            saveButton.setOnClickListener {
                viewModel.saveAlbum(album = ())

            }
            deleteButton.setOnClickListener {
                viewModel.deleteAlbum(args.albumId)
                findNavController().navigateUp()
            }
        }
    }

}