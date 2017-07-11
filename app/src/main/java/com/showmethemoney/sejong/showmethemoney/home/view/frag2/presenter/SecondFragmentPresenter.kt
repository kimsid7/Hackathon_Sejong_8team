package com.showmethemoney.sejong.showmethemoney.home.view.frag2.presenter

import android.app.Activity
import com.showmethemoney.sejong.showmethemoney.UserUtil
import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.view.ListChild
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.view.TabFragment2
import com.showmethemoney.sejong.showmethemoney.network.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by LeeWoochan on 2017-06-26.
 */
class SecondFragmentPresenter(var view : SecondFragmentContract.View) : SecondFragmentContract.Presenter {

    companion object{
        internal var cmpAsc: Comparator<ListChild> = Comparator { o1, o2 -> o1.point.compareTo(o2.point) }
    }

    override fun getRank(activity:Activity,adapter: TabFragment2.CustomAdapter,items: ArrayList<ListChild>) {
        NetworkUtil.request()
                .getRankInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    items.clear()
                    var num = 0
                    for(child in it){
                        items.add(ListChild(child.id,child.point))
                    }
                    //Collections.sort(items, cmpAsc)

                    adapter.notifyDataSetChanged()
                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }

    override fun getMyRank(activity: Activity){
        NetworkUtil.request()
                .getMyRankInfo(LoginInfoRequest(UserUtil.userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it ->
                    activity.runOnUiThread({
                        view.myRankRefresh(it.myrank)
                    })
                },{
                    it ->
                    it.printStackTrace()
                },{

                })
    }
}