package com.example.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.credentials.CredentialManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipesapp.databinding.ActivityRootBinding
import com.google.android.gms.common.util.CollectionUtils.setOf
import com.google.firebase.auth.FirebaseAuth

class RootActivity : AppCompatActivity() {
    private var _binding: ActivityRootBinding? = null
    private val rootBinding get() = _binding!!

    lateinit var auth: FirebaseAuth
    lateinit var credentialManager: CredentialManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)

        auth = FirebaseAuth.getInstance()
        credentialManager = CredentialManager.create(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        rootBinding.bottomNavigationView.setupWithNavController(navController)

        val setOfVisibleFragments = arrayOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.exploreFragment,
            R.id.favoritesFragment,
            R.id.arcticlesFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            rootBinding.bottomNavigationView.isVisible = destination.id in setOfVisibleFragments
            //rootBinding.navBarDivider.isVisible = destination.id in setOfVisibleFragments
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}