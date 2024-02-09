package com.example.various.pager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.various.R

class OldPagerFragment : Fragment() {

    companion object {
        fun newInstance() = OldPagerFragment()
    }

    private lateinit var viewModel: OldPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_old_pager, container, false)
    }
}