package com.showmethemoney.sejong.showmethemoney.signup.presenter

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class SignupContract{
    interface View{
        fun makeToast(message: String)
        fun finishActivity()
    }
    interface Presenter{

    }
}