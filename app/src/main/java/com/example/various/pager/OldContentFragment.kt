package com.example.various.pager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.various.R
import com.example.various.databinding.FragmentOldContentBinding

class OldContentFragment : Fragment() {

    companion object {
        fun newInstance(position: Int) : OldContentFragment {
            return OldContentFragment().apply {
                arguments = Bundle().apply {
                    putInt("position", position)
                }
            }
        }
    }

    private lateinit var viewModel: OldContentViewModel
    private lateinit var binding: FragmentOldContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOldContentBinding.inflate(inflater, container, false)
        binding.testView.text = arguments?.getInt("position").toString()
        return binding.root
    }

    fun initPlayer() {

    }
}