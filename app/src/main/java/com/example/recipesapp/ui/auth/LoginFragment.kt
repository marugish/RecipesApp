package com.example.recipesapp.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recipesapp.presentation.auth.LoginViewModel
import com.example.recipesapp.R
import com.example.recipesapp.RootActivity
import com.example.recipesapp.databinding.FragmentLoginBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import kotlinx.coroutines.launch

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

        binding.googleLoginButton.setOnClickListener {
            launchCredentialManager()
        }

        val pendingResultTask = FirebaseAuth.getInstance().pendingAuthResult
        pendingResultTask?.addOnSuccessListener { authResult ->
            handleGitHubSignInSuccess(authResult)
        }?.addOnFailureListener {
            Log.e("GitHubAuth", "Pending auth failed: ${it.localizedMessage}")
        }

        binding.githubLoginButton.setOnClickListener {
            signInWithGitHub()
        }

    }

    private fun signInWithGitHub() {
        val provider = OAuthProvider.newBuilder("github.com")
            .addCustomParameter("allow_signup", "false") // можно добавить параметры
            .setScopes(listOf("user:email")) // нужно добавить хотя бы базовый scope!

        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            // Пользователь уже вошёл — привязываем GitHub к аккаунту
            currentUser.startActivityForLinkWithProvider(requireActivity(), provider.build())
                .addOnSuccessListener { authResult ->
                    Log.d("GitHubAuth", "GitHub account linked to existing user")
                    handleGitHubSignInSuccess(authResult)
                }
                .addOnFailureListener { e ->
                    if (e is FirebaseAuthWebException) {
                        Log.e("GitHubAuth", "Web Exception: ${e.errorCode} - ${e.localizedMessage}", e)
                    } else {
                        Log.e("GitHubAuth", "Other Exception: ${e.localizedMessage}", e)
                    }
                    //Log.e("GitHubAuth", "Sign-in failed", it)
                    //Log.e("GitHubAuth", "Linking failed: ${it.localizedMessage}")
                }
        } else {
            // Первый вход через GitHub
            FirebaseAuth.getInstance()
                .startActivityForSignInWithProvider(requireActivity(), provider.build())
                .addOnSuccessListener { authResult ->
                    handleGitHubSignInSuccess(authResult)
                }
                .addOnFailureListener {
                    Log.e("GitHubAuth", "Sign-in failed: ${it.localizedMessage}")
                }
        }
    }

    private fun handleGitHubSignInSuccess(authResult: AuthResult) {
        val user = authResult.user
        val profile = authResult.additionalUserInfo?.profile
        val credential = authResult.credential as? OAuthCredential


        val accessToken = credential?.accessToken
        Log.d("GitHubAuth", "Access token: $accessToken")

        // Переход к экрану профиля
        findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
    }

    private fun launchCredentialManager() {
        // Instantiate a Google sign-in request
        val googleIdOption = GetGoogleIdOption.Builder()
            // Your server's client ID, not your Android client ID.
            .setServerClientId(getString(R.string.default_web_client_id))
            // True - Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(false) //true)
            .build()

        // Create the Credential Manager request
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                // Launch Credential Manager UI
                val result = (activity as RootActivity).credentialManager.getCredential(
                    context = requireContext(),
                    request = request
                )
                // Extract credential from the result returned by Credential Manager
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                Log.e("myTest", "Couldn't retrieve user's credentials: ${e.localizedMessage}")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            // Sign in to Firebase with using the token
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w("myTest", "Credential is not of type Google ID!")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        (activity as RootActivity).auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("myTest", "signInWithCredential:success")

                    findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user
                    Log.w("myTest", "signInWithCredential:failure", task.exception)
                    //updateUI(null)
                }
            }
    }
}