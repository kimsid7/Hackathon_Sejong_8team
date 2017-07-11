package com.showmethemoney.sejong.showmethemoney.home.view.frag1.view


import com.mukcha.mukcha.home.adapter.RecyclerAdapter
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.UserUtil
import com.showmethemoney.sejong.showmethemoney.home.model.LoginInfoRequest
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoResponse
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.presenter.FirstFragmentContract
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.presenter.FirstFragmentPresenter
import kotlinx.android.synthetic.main.tab_fragment_1.*


/**
 * Created by LeeWoochan on 2017-04-28.
 */
class TabFragment1 : android.support.v4.app.Fragment() , FirstFragmentContract.View {

    private val presenter = FirstFragmentPresenter(this)

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?, savedInstanceState: android.os.Bundle?): android.view.View? {
        presenter.getLoginInfo(LoginInfoRequest(UserUtil.userId))
        presenter.getPubInfo()

        val rootView = inflater?.inflate(R.layout.tab_fragment_1, container, false)
        rootView?.findViewById(R.id.refresh)?.setOnClickListener {
            presenter.getLoginInfo(LoginInfoRequest(UserUtil.userId))
            presenter.getPubInfo()
        }
        return rootView
    }

    override fun getPubInfo(pubs: List<PubInfoResponse>) {
        val layoutManager = android.support.v7.widget.LinearLayoutManager(activity)
        recyclerView?.adapter = RecyclerAdapter(activity, pubs,presenter)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = layoutManager
    }

    override fun makeToast(message: String) {
        android.widget.Toast.makeText(activity.applicationContext, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun renewPoint(point : String) {
        now_point.text = point + "P"
    }
}