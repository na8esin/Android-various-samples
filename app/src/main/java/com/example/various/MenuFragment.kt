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
        binding.button6.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_oldPagerFragment)
        }
        binding.button7.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_overlapFragment)
        }
        binding.button8.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_overlap2Fragment)
        }
        binding.button9.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_locationFragment)
        }
        binding.button10.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_location2Fragment)
        }
        binding.button11.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_imageViewFragment)
        }
        binding.button12.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_viewBindFragment)
        }
        binding.button13.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_exoPlayerFragment)
        }
        binding.button14.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_fileFragment)
        }
        binding.button15.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_adidFragment)
        }
        return binding.root
    }
}