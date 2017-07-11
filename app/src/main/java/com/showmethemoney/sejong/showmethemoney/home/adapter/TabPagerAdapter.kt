package com.showmethemoney.sejong.showmethemoney.home.adapter

/**
 * Created by LeeWoochan on 2017-06-26.
 */

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.view.TabFragment1
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.view.TabFragment2

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class TabPagerAdapter(fm : FragmentManager, val tabCount : Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> {
                val tabFragment1 = TabFragment1()
                return tabFragment1
            }
            1 -> {
                val tabFragment2 = TabFragment2()
                return tabFragment2
            }
            else ->{
                return null
            }
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}