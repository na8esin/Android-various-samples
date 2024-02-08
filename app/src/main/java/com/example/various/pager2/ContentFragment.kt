package com.example.various.pager2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.various.R
import com.example.various.databinding.FragmentContentBinding

class ContentFragment : Fragment() {

    companion object {
        fun newInstance(position: Int): ContentFragment {
            return ContentFragment().also {
                 it.arguments = Bundle().apply {
                     putInt("position", position)
                }
            }
        }
    }

    private lateinit var viewModel: ContentViewModel
    private lateinit var binding: FragmentContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        binding.textView.text = arguments?.getInt("position").toString()
        return binding.root
    }
}