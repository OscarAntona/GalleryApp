package com.antgut.myapplication.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.databinding.FragmentUserDialogBinding
import com.antgut.myapplication.features.user.domain.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserAddDialogFragment : BottomSheetDialogFragment() {
    private val viewModel by viewModels<UserAddDialogViewModel>()
    private var _binding: FragmentUserDialogBinding? = null
    private val binding: FragmentUserDialogBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDialogBinding.inflate(inflater, container, false)
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
                viewModel.saveUser(
                    user = User(
                        name = inputName.text.toString(),
                        username = inputUsername.text.toString(),
                        email = inputEmail.text.toString()
                    )
                )
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
            deleteButton.visibility = View.GONE
        }
    }

    private fun setUpObserver() {
        val subscriber = Observer<UserAddDialogViewModel.UiModel> {
            when {
                it.isSaved -> {
                    Log.d("@dev", "hula")
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