package com.showmethemoney.sejong.showmethemoney.main.presenter

import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.main.model.SigninPost

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class MainContract{
    interface View{
        fun makeToast(message: String)
        fun moveToHome()
        fun finishActivity()
    }
    interface Presenter{
        fun signRequest(signinPost: SigninPost, id : String)
    }
}