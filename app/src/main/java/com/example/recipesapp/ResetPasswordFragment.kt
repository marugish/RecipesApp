package com.example.recipesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val user = (activity as RootActivity).auth.currentUser


        val newPassword = "SOME-SECURE-PASSWORD"

        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("myTest", "User password updated.")
                }
            }

    }

}