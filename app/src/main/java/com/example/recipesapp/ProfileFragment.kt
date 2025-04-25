package com.example.recipesapp

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.recipesapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewModel: ProfileViewModel

    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private var photoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Регистрируем событие, которое вызывает Photo picker
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.add_photo)
                    .into(binding.addPhoto)

                photoUri = uri
                binding.addPhotoButton.isEnabled = photoUri != null
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

        binding.addPhoto.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.addPhotoButton.setOnClickListener {
            val user = (activity as RootActivity).auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(photoUri.toString()))
                .build()

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("myTest", "User profile updated.")
                        findNavController().navigate(R.id.action_profileFragment_to_searchFragment)
                    }
                }
        }

        binding.skipButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_searchFragment)
        }
    }

}