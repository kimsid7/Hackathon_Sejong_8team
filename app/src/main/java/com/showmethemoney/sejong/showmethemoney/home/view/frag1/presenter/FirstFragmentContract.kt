package com.showmethemoney.sejong.showmethemoney.home.view.frag1.presenter

import android.widget.TextView
import com.mukcha.mukcha.home.adapter.RecyclerAdapter
import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoResponse

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class FirstFragmentContract{
    interface View{
        fun makeToast(message: String)
        fun renewPoint(point : String)
        fun getPubInfo(pubs: List<PubInfoResponse>)
    }
    interface Presenter{
        fun buyStock(holder : RecyclerAdapter.ViewHolder?)
        fun sellStock(holder : RecyclerAdapter.ViewHolder?)
        fun getLoginInfo(loginInfoRequest: LoginInfoRequest)
        fun getPubInfo()
        fun selectMenu(textView: TextView, bar_code : String?)
    }
}
