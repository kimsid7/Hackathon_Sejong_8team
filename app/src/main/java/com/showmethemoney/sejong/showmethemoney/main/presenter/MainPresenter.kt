package com.showmethemoney.sejong.showmethemoney.main.presenter

import com.showmethemoney.sejong.showmethemoney.UserUtil
import com.showmethemoney.sejong.showmethemoney.main.model.SigninPost
import com.showmethemoney.sejong.showmethemoney.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun signRequest(signinPost: SigninPost,id : String){
        NetworkUtil.request()
                .postSignin(signinPost)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    if(it.result_code.toString() == "0000"){
                        view.makeToast("로그인 성공")
                        UserUtil.userId = id
                        view.moveToHome()
                        view.finishActivity()
                    }else{
                        view.makeToast("아이디나 비밀번호를 확인하세요!")
                    }

                },{
                    it ->
                    view.makeToast("에러")
                    it.printStackTrace()
                },{

                })
    }
}