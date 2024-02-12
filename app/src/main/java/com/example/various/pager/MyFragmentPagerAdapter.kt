package com.example.various.pager

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyFragmentPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return OldContentFragment.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    /**
     * 初期表示しただけでやたら通知される
     * 逆にスワイプした時は、2回通知される
     */
    override fun finishUpdate(container: ViewGroup) {
        super.finishUpdate(container)
        Log.d("MyFragmentPagerAdapter", "finishUpdate")
    }

    /**
     * これもやたらと呼びすぎ
     */
    override fun setPrimaryItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        super.setPrimaryItem(container, position, `object`)
        Log.d("MyFragmentPagerAdapter", "setPrimaryItem")
    }
}