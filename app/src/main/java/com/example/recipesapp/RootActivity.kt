package com.example.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipesapp.databinding.ActivityRootBinding
import com.google.android.gms.common.util.CollectionUtils.setOf
import com.google.firebase.auth.FirebaseAuth

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

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