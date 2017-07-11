package com.showmethemoney.sejong.showmethemoney.home.view.frag1.presenter

import android.widget.TextView
import com.mukcha.mukcha.home.adapter.RecyclerAdapter
import com.showmethemoney.sejong.showmethemoney.UserUtil
import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.SelectMenuRequest
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.model.StockRequest
import com.showmethemoney.sejong.showmethemoney.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class FirstFragmentPresenter(var view : FirstFragmentContract.View) : FirstFragmentContract.Presenter {
    override fun getLoginInfo(loginInfoRequest: LoginInfoRequest){
        NetworkUtil.request()
                .postLoginInfo(loginInfoRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    view.renewPoint(it.final_money)
                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }

    override fun getPubInfo() {
        NetworkUtil.request()
                .getPubInfo(PubInfoRequest(UserUtil.userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    System.out.println(it)
                    view.getPubInfo(it)
                },{
                    it ->
                    view.makeToast("에러")
                    it.printStackTrace()
                },{

                })
    }

    override fun buyStock(holder : RecyclerAdapter.ViewHolder?) {
        NetworkUtil.request()
                .buyStock(StockRequest(UserUtil.userId, holder?.code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    if(it.result_code == "0000"){
                        view.makeToast("구매 성공")
                        getPubInfo()
                        getLoginInfo(LoginInfoRequest(UserUtil.userId))
                    }else{
                        view.makeToast("잔액 부족")
                    }

                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }

    override fun sellStock(holder : RecyclerAdapter.ViewHolder?) {
        NetworkUtil.request()
                .sellStock(StockRequest(UserUtil.userId, holder?.code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    if(it.result_code == "0000"){
                        view.makeToast("판매 성공")
                        getPubInfo()
                        getLoginInfo(LoginInfoRequest(UserUtil.userId))
                    }else{
                        view.makeToast("보유 주식 없음")
                    }

                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }

    override fun selectMenu(textView: TextView,bar_code : String?){
        NetworkUtil.request()
                .selectMenu(SelectMenuRequest(bar_code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    var menu : String = ""
                    for(menuResponse in it){
                        menu += menuResponse.name + "  :  " + menuResponse.price + "\n"
                    }
                    textView.setText(menu)
                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }
}