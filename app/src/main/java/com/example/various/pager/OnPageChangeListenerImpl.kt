package com.example.various.pager

import android.util.Log
import androidx.viewpager.widget.ViewPager

class OnPageChangeListenerImpl(
    private val adapter: MyFragmentPagerAdapter
): ViewPager.OnPageChangeListener {
    private var currentPos: Int = 0

    /**
     * スライドしている途中もめっちゃ通知される。
     * 初期表示も通知される。
     */
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        Log.d("OnPageChangeListenerImpl", "onPageScrolled: $position")
    }

    /**
     * スライドが完了したときに通知される。
     * 逆に、最初の画面が表示されたときは通知されない。
     */
    override fun onPageSelected(position: Int) {
        Log.d("OnPageChangeListenerImpl", "onPageSelected: $position")
        currentPos = position
    }

    /**
     * 引数だけだと、状態がわかってもポジションがわからんが、
     * メンバ変数に入れておけばわかる。
     */
    override fun onPageScrollStateChanged(state: Int) {
        Log.d(
            "OnPageChangeListenerImpl",
            "onPageScrollStateChanged: $state, currentPos: $currentPos, currentItem ${adapter.getItem(currentPos).arguments}")

    }
}