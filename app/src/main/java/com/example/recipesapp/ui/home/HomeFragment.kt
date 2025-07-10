package com.example.recipesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipesapp.BaseFragment
import com.example.recipesapp.R
import com.example.recipesapp.RootActivity
import com.example.recipesapp.databinding.FragmentHomeBinding
import com.example.recipesapp.presentation.home.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // если не от поставщика услуг
        val user = (activity as RootActivity).auth.currentUser
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            binding.profileName.text = name
            binding.profileEmail.text = email
            Glide.with(this)
                .load(photoUrl)
                .placeholder(R.drawable.ic_add_photo)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.profileImage)

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
        }

        // если от поставщика услуг
        // ...

        binding.logoutButton.setOnClickListener {
            (activity as RootActivity).auth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }

}