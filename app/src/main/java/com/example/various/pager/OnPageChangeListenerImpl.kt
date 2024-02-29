package com.example.various.pager

import android.util.Log
import androidx.viewpager.widget.ViewPager

/**
 * - 初期表示時はプレイヤーを起動しない
 * - 放送があるチャンネルに移動させる
 */
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
            "onPageScrollStateChanged: $state, " +
                    "currentPos: $currentPos, " +
                    "currentItem ${adapter.getItem(currentPos).arguments}")

        /*
         * ページングが完了した時にプレイヤーを初期化する
         */
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            // ここで、無理やり、adapter.getItemを呼び出してもonCreateView()が呼ばれないから意味ない
            // さらに、このメソッドの後に、setPrimaryItem()が呼ばれるから、ここで、Fragmentのメソッドを呼び出すとズレる
//            val f = adapter.currentFragment()
//            f?.initPlayer()
        }
    }
}