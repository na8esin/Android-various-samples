package com.example.various

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.various.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.button4.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_topFragment)
        }
        binding.button5.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_pagerFragment)
        }
        return binding.root
    }
}