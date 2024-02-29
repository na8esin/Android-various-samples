package com.example.various.overlap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentOverlap2Binding

class Overlap2Fragment : Fragment() {

    companion object {
        fun newInstance() = Overlap2Fragment()
    }

    private lateinit var viewModel: Overlap2ViewModel
    private lateinit var binding: FragmentOverlap2Binding
    private lateinit var inserted: RelativeLayout
    private lateinit var overlap: ImageView

    /**
     * RelativeLayoutとImageViewが並列のサンプル
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOverlap2Binding.inflate(inflater, container, false)
        inserted = binding.inserted
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

        // こんな感じで、一度もaddViewせずにremoveViewしてもエラーにならない
        inserted.removeView(secondLayout)
        inserted.addView(secondLayout)

        // addViewを2回行うとどうなる？
        // java.lang.IllegalStateException: The specified child already has a parent.
        // で怒られる
        // inserted.addView(secondLayout)

        // こんな感じでremoveViewしてからaddViewするとOK
        inserted.removeView(secondLayout)
        inserted.addView(secondLayout)

        overlap.setOnClickListener {
            println("overlap clicked")
        }

        return binding.root
    }

}