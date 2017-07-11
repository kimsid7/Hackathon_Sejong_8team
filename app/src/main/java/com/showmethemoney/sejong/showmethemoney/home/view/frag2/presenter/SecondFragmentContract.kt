package com.showmethemoney.sejong.showmethemoney.home.view.frag2.presenter

import android.app.Activity
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.view.ListChild
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.view.TabFragment2

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class SecondFragmentContract {
    interface View{
        fun myRankRefresh(rank : String)
    }
    interface Presenter{
        fun getRank(activity: Activity, adapter: TabFragment2.CustomAdapter, items: ArrayList<ListChild>)
        fun getMyRank(activity: Activity)
    }
}