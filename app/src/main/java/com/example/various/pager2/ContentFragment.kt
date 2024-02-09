package com.example.various.pager2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentContentBinding

class ContentFragment : Fragment() {

    companion object {
        const val TAG = "ContentFragment"
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
        Log.d(TAG, "onCreateView: $arguments")
        binding = FragmentContentBinding.inflate(inflater, container, false)
        binding.textView.text = arguments?.getInt("position").toString()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: $arguments")
    }
}