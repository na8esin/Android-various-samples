package com.example.various.overlap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentOverlapBinding

class OverlapFragment : Fragment() {

    companion object {
        fun newInstance() = OverlapFragment()
    }

    private lateinit var viewModel: OverlapViewModel
    private lateinit var binding: FragmentOverlapBinding
    private lateinit var parentContainer: RelativeLayout
    private lateinit var overlap: ImageView

    /**
     * RelativeLayoutの子供にImageViewを追加しているサンプル
     * これだと、どこをクリックしても`secondLayout clicked`になるので、
     * 元々配置している、ImageViewよりもaddViewしたものが、z軸方向で上になってる
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverlapBinding.inflate(inflater, container, false)
        parentContainer = binding.parentContainer
        overlap = binding.overlap

        val secondLayout = RelativeLayout(requireContext())
        secondLayout.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        secondLayout.setBackgroundColor(0x7f0000ff)
        secondLayout.setOnClickListener {
            println("secondLayout clicked")
        }

        /** addViewしたものが上にきてる */
        parentContainer.addView(secondLayout)
        overlap.setOnClickListener {
            println("overlap clicked")
        }

        return binding.root
    }

}