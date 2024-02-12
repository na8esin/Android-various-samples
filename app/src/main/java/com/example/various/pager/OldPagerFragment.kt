package com.example.various.pager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.various.R
import com.example.various.databinding.FragmentOldPagerBinding

class OldPagerFragment : Fragment() {

    companion object {
        fun newInstance() = OldPagerFragment()
    }

    private lateinit var viewModel: OldPagerViewModel
    private lateinit var binding: FragmentOldPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOldPagerBinding.inflate(inflater, container, false)
        val adapter = MyFragmentPagerAdapter(childFragmentManager)
        val pager = binding.viewPager
        pager.adapter = adapter

        pager.setOnPageChangeListener(OnPageChangeListenerImpl(adapter))

        binding.strip.setViewPager(pager)
        return binding.root
    }
}