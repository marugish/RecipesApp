package com.example.recipesapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.presentation.auth.ResetPasswordViewModel
import com.example.recipesapp.RootActivity
import com.example.recipesapp.databinding.FragmentProfileBinding
import com.example.recipesapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : BaseFragment<FragmentResetPasswordBinding>() {

    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentResetPasswordBinding {
        return FragmentResetPasswordBinding.inflate(inflater, container, false)
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