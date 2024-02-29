package com.example.various.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentOldPagerBinding
import com.ogaclejapan.smarttablayout.SmartTabLayout

/**
 * - 現在放送中のチャンネルを探す
 *
 */
class OldPagerFragment : Fragment() {

    companion object {
        private const val TAG = "OldPagerFragment"
        fun newInstance() = OldPagerFragment()
    }

    private lateinit var viewModel: OldPagerViewModel
    private lateinit var binding: FragmentOldPagerBinding
    private lateinit var tab: SmartTabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOldPagerBinding.inflate(inflater, container, false)
        val adapter = MyFragmentPagerAdapter(childFragmentManager)
        val pager = binding.viewPager
        pager.adapter = adapter

        //pager.addOnPageChangeListener(OnPageChangeListenerImpl(adapter))

        tab = binding.strip
        tab.setViewPager(pager)

        // これで初期表示のタブを選択できる
        pager.currentItem = 1
        binding.obstructiveContainer.setOnClickListener {
            println("obstructiveContainer clicked")
        }

        return binding.root
    }
}