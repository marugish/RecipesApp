package com.example.recipesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipesapp.databinding.FragmentHomeBinding
import com.example.recipesapp.databinding.FragmentProfileBinding
import com.example.recipesapp.presentation.home.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
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
                .placeholder(R.drawable.add_photo)
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
    }

}