package com.showmethemoney.sejong.showmethemoney.home.presenter

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class HomeContract{
    interface View{
        fun makeToast(message: String)
        //fun renewPoint(point : String)
        //fun getPubInfo(pubs: List<PubInfoResponse>)
    }
    interface Presenter{
        //fun getLoginInfo(loginInfoRequest: LoginInfoRequest)
        //fun getPubInfo()
    }
}