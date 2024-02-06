package com.example.charlestest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.charlestest.databinding.FragmentTopBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TopFragment : Fragment() {

    companion object {
        fun newInstance() = TopFragment()
    }

    private val viewModel: TopViewModel by viewModels {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        TopViewModelFactory(retrofit)
    }

    private lateinit var binding: FragmentTopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            this.viewmodel = viewModel

            // Specify the fragment view as the lifecycle owner of the binding.
            // This is used so that the binding can observe LiveData updates
            lifecycleOwner = viewLifecycleOwner
        }

        // Setup a click listener for the Submit and Skip buttons.
    }

}