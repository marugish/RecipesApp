package com.example.recipesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val updateButtonState = {
            val email = binding.mailEditText.text?.toString().orEmpty()
            val password = binding.passwordEditText.text?.toString().orEmpty()

            binding.signInButton.isEnabled = email.isNotEmpty() && password.isNotEmpty()
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


        binding.signInButton.setOnClickListener {
            val email = binding.mailEditText.text?.toString() ?: ""
            val password = binding.passwordEditText.text?.toString() ?: ""

            (activity as RootActivity).auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("myTest", "signInWithEmail:success")

                        //val user = auth.currentUser
                        //updateUI(user)

                        findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
                    } else {
                        Log.i("myTest", "signInWithEmail:failure ${task.exception?.message.toString()}")
                        Toast.makeText(
                            requireContext(), "Authentication failed.", Toast.LENGTH_SHORT
                        ).show()

                        //updateUI(null)
                    }
                }

        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }

    }
}