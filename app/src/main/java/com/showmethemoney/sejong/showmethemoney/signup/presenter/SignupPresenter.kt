package com.showmethemoney.sejong.showmethemoney.signup.presenter

import com.showmethemoney.sejong.showmethemoney.network.NetworkUtil
import com.showmethemoney.sejong.showmethemoney.signup.model.SignupPost
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class SignupPresenter(var view : SignupContract.View) : SignupContract.Presenter{
    fun signupRequest(signupPost: SignupPost){
        NetworkUtil.request()
                .postSignup(signupPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    if(it.result_code.toString() == "0000") {
                        view.makeToast("회원가입 성공")
                        view.finishActivity()
                    }else{
                        view.makeToast("아이디 중복입니다")
                    }
                },{
                    it ->
                    view.makeToast("에러")
                    it.printStackTrace()
                },{

                })
    }
}