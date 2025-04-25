package com.example.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipesapp.databinding.ActivityRootBinding
import com.google.android.gms.common.util.CollectionUtils.setOf
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    lateinit var auth: FirebaseAuth
    lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        credentialManager = CredentialManager.create(this)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val setOfVisibleFragments = setOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.exploreFragment,
            R.id.favoritesFragment,
            R.id.arcticlesFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.isVisible = destination.id in setOfVisibleFragments
            //binding.navBarDivider.isVisible = destination.id in setOfVisibleFragments
        }

        binding.bottomNavigationView.setupWithNavController(navController)

    }



}