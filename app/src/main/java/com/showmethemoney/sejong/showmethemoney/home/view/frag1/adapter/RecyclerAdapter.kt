package com.mukcha.mukcha.home.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.home.model.PubInfoResponse
import com.showmethemoney.sejong.showmethemoney.home.view.frag1.presenter.FirstFragmentPresenter


class RecyclerAdapter(var activity: Activity?, var pubs : List<PubInfoResponse>, var presenter: FirstFragmentPresenter) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var holder: ViewHolder? = null
    override fun getItemCount(): Int {
        return this.pubs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, vi9ewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.getContext()).inflate(R.layout.cardview, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val pub = pubs.get(position)
        this.holder = holder
        getUrlImage(pub.image_url.replace("\\", ""))
        holder?.title?.setText(pub.bar_name)
        holder?.point?.setText(pub.current_price+"P")
        holder?.code = pub.bar_code
        holder?.invest_num?.setText(pub.invest_num)
        holder?.cardview?.setOnClickListener {
            makeConfirmDialog(holder,position)
        }
    }

    private fun getUrlImage(url : String) {
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .into(holder?.image)
    }

    private fun makeConfirmDialog(holder: ViewHolder?,position: Int){
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder
                .setMessage("원하시는 거래를 선택하세요!\n(매입가는 현재가+5P 입니다)")
                .setCancelable(true)
                .setNegativeButton("매입",
                        DialogInterface.OnClickListener { dialog, id ->
                            presenter.buyStock(holder)
                        })
                .setNeutralButton("매각",
                        DialogInterface.OnClickListener { dialog, id ->
                            presenter.sellStock(holder)
                        })
                .setPositiveButton("주점정보",
                        DialogInterface.OnClickListener { dialog, id ->
                            makeInfoDialog(holder,position)
                        })

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun makeInfoDialog(holder: ViewHolder?, position: Int){
        val pub = pubs.get(position)
        var dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_barinfo)
        var imageView = dialog.findViewById(R.id.bar_info_image) as ImageView
        var textView = dialog.findViewById(R.id.bar_info_text) as TextView
        presenter.selectMenu(textView,holder?.code)
        Glide.with(dialog.context)
                .load(pub.image_url.replace("\\", ""))
                .centerCrop()
                .into(imageView)
        dialog.show()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardview : CardView = itemView.findViewById(R.id.cardview) as CardView
        val title : TextView = itemView.findViewById(R.id.bar_title) as TextView
        val point : TextView = itemView.findViewById(R.id.bar_point) as TextView
        val invest_num : TextView = itemView.findViewById(R.id.bar_invest_num) as TextView
        var code : String? = null
        val image : ImageView = itemView.findViewById(R.id.bar_image) as ImageView
    }
}