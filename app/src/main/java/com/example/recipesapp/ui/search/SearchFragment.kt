package com.example.recipesapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipesapp.data.network.ApiKeyInterceptor
import com.example.recipesapp.presentation.search.SearchViewModel
import com.example.recipesapp.databinding.FragmentSearchBinding
import com.example.recipesapp.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()



    }
}