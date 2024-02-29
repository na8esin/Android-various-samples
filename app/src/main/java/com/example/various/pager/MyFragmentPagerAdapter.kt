package com.example.various.pager

import android.database.DataSetObserver
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyFragmentPagerAdapter(
    fm: FragmentManager
): FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private var mCurrentFragment: OldContentFragment? = null

    override fun getCount(): Int {
        return 2
    }

//    fun currentFragment(): OldContentFragment? {
//        return mCurrentFragment
//    }

    /**
     * スワイプすると、getItem()の後に、onCreateView()が呼ばれない
     */
    override fun getItem(position: Int): Fragment {

        Log.d("MyFragmentPagerAdapter", "getItem: $position")
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
     * これも、初期はやたらと呼びすぎ
     */
    override fun setPrimaryItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        // mCurrentFragment = `object` as OldContentFragment
        super.setPrimaryItem(container, position, `object`)
        Log.d("MyFragmentPagerAdapter", "setPrimaryItem")
    }
}