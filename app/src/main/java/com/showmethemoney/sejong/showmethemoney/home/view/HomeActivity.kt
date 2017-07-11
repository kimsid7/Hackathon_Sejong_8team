package com.showmethemoney.sejong.showmethemoney.home.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.home.adapter.TabPagerAdapter
import com.showmethemoney.sejong.showmethemoney.home.presenter.HomeContract
import com.showmethemoney.sejong.showmethemoney.home.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private val presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tabLayout.addTab(tabLayout.newTab().setText("투자"))
        tabLayout.addTab(tabLayout.newTab().setText("순위"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        //Create TabPagerAdapter
        val tabPagerAdapter = TabPagerAdapter(supportFragmentManager, tabLayout.tabCount)
        home_viewPager.adapter = tabPagerAdapter
        home_viewPager.setOffscreenPageLimit(2);
        home_viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                home_viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
