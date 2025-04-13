package com.example.recipesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.recipesapp.databinding.FragmentRegisterBinding
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
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

            (activity as RootActivity).auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("myTest", "createUserWithEmail:success")
                        //val user = auth.currentUser
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