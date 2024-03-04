package com.example.various.pager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
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

    private val viewModel: OldContentViewModel by viewModels()
    private lateinit var binding: FragmentOldContentBinding
    private lateinit var pagerData: PagerData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("OldContentFragment", "onCreateView")

        binding = FragmentOldContentBinding.inflate(inflater, container, false)
        binding.testView.text = arguments?.getInt("position").toString()
        pagerData = PagerData(arguments?.getInt("position") ?: 0)
        Log.d("OldContentFragment", "pagerData: $pagerData")

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("OldContentFragment", "onResume")

        initPlayer()

        viewModel.startCountDownTimer()
    }

    private fun initPlayer() {
        Log.d("OldContentFragment", "initPlayer: $arguments")
        pagerData.toString()
    }

    /* タブが切り替わった時のこれが呼ばれるから、ここでplayer.releaseなどをすればいい */
    override fun onPause() {
        super.onPause()
        Log.d("OldContentFragment", "onPause: $arguments")

        releasePlayer()
        // onPauseだと自動でキャンセルされない
        viewModel.cancelCountDownTimer()
    }

    private fun releasePlayer() {
        Log.d("OldContentFragment", "releasePlayer: $arguments")
    }

    override fun onStop() {
        super.onStop()
        Log.d("OldContentFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("OldContentFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("OldContentFragment", "onDestroy")
    }
}