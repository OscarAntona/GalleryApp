package com.antgut.myapplication.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.antgut.myapplication.app.extensions.showSnackBar
import com.antgut.myapplication.databinding.FragmentUserDialogBinding
import com.antgut.myapplication.features.user.domain.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDialogFragment: BottomSheetDialogFragment() {
    private val viewModel by viewModels<UserDialogViewModel>()
    private var _binding: FragmentUserDialogBinding? = null
    private val binding: FragmentUserDialogBinding
        get() = _binding!!

    private val args: UserDialogFragmentArgs by navArgs()

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
        Log.d("@dev",args.userId.toString())
        viewModel.getUser(args.userId)
    }
    private fun setUpView() {
        binding.apply {

            saveButton.setOnClickListener {
                val user = viewModel.uiModel.value?.user?.let { user ->
                    User(
                        name = inputName.text.toString(),
                        id = args.userId,
                        username = user.username,
                        email = user.email
                    )
                }
                if (user != null) {
                    viewModel.saveUser(user)
                }
                findNavController().navigateUp()
                findNavController().navigateUp()
            }
            deleteButton.setOnClickListener {
                viewModel.deleteUser(args.userId)
                findNavController().navigateUp()
            }
        }
    }

    private fun setUpObserver() {
        val subscriber = Observer<UserDialogViewModel.UiModel> {
            when {
                it.user != null -> bind(it)
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

    private fun bind(model: UserDialogViewModel.UiModel) {
        binding.apply {
            model.user?.let { user ->
                inputName.setText(user.name)
                inputEmail.setText(user.email)
                inputUsername.setText(user.username)
            }
        }
    }

}