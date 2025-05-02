package com.example.recipesapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.presentation.auth.RegisterViewModel
import android.widget.Toast
import com.example.recipesapp.databinding.FragmentRegisterBinding
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.R
import com.example.recipesapp.RootActivity
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    private var email: String = ""
    private var password: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val updateButtonState = {
            val email = binding.mailEditText.text?.toString().orEmpty()
            val password = binding.passwordEditText.text?.toString().orEmpty()
            val confirmPassword = binding.confirmPasswordEditText.text?.toString().orEmpty()

            binding.signupButton.isEnabled =
                email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && confirmPassword == password
        }

        binding.mailEditText.addTextChangedListener(
            afterTextChanged = { s ->
                //email = s?.toString() ?: ""
                updateButtonState()
            }
        )

        binding.passwordEditText.addTextChangedListener(
            afterTextChanged = { s ->
                //password = s?.toString() ?: ""
                updateButtonState()
            }
        )

        binding.confirmPasswordEditText.addTextChangedListener(
            afterTextChanged = { s ->
                //confirmPassword = s?.toString() ?: ""
                if (s?.toString() != binding.passwordEditText.text?.toString()) {
                    binding.confirmPasswordEditText.error = getString(R.string.passwords_dont_match)
                }
                updateButtonState()
            }
        )

        binding.signupButton.setOnClickListener {
            val email = binding.mailEditText.text?.toString() ?: ""
            val password = binding.passwordEditText.text?.toString() ?: ""
            val userName = binding.fullNameEditText.text?.toString() ?: ""

            (activity as RootActivity).auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("myTest", "createUserWithEmail:success")
                        // Установить имя пользователя
                        val user = (activity as RootActivity).auth.currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(userName)
                            .build()
                        user!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("myTest", "User profile updated.")

                                }
                            }
                        //updateUI(user)
                        // Переходим на экран добавления фото
                        findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
                    } else {
                        Log.i("myTest", "createUserWithEmail:failure ${task.exception?.message.toString()}")
                        Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
                        //updateUI(null)
                    }
                }
        }

        binding.signinButton.setOnClickListener {

        }

    }
}