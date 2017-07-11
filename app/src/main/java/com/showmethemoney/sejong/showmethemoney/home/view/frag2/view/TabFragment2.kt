package com.showmethemoney.sejong.showmethemoney.home.view.frag2.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.showmethemoney.sejong.showmethemoney.R
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.presenter.SecondFragmentContract
import com.showmethemoney.sejong.showmethemoney.home.view.frag2.presenter.SecondFragmentPresenter


/**
 * Created by LeeWoochan on 2017-06-26.
 */
class TabFragment2 : android.support.v4.app.Fragment(), SecondFragmentContract.View{

    private val presenter = SecondFragmentPresenter(this)


    private var myRank : TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.tab_fragment_2, container, false)
        val listView = rootView?.findViewById(R.id.rank_list_view) as ListView?
        myRank = rootView?.findViewById(R.id.my_rank) as TextView

        var items = ArrayList<ListChild>()
        var adapter = CustomAdapter(items)
        listView?.adapter = adapter
        presenter.getRank(activity,adapter,items)
        presenter.getMyRank(activity)

        rootView?.findViewById(R.id.refresh_rank)?.setOnClickListener {
            presenter.getRank(activity,adapter,items)
            presenter.getMyRank(activity)
        }

        return rootView
    }

    override fun myRankRefresh(rank : String){
        myRank?.setText(""+ rank +"위")
    }

    class CustomAdapter(var items : ArrayList<ListChild>) : BaseAdapter() {

        // 문자열을 보관 할 ArrayList
        private var m_List= items

        private var holder : CustomViewHolder? = null

        // 현재 아이템의 수를 리턴
        override fun getCount(): Int {
            return m_List.size
        }

        // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
        override fun getItem(position: Int): Any {
            return m_List[position]
        }

        // 아이템 position의 ID 값 리턴
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getViewTypeCount(): Int {
            if (m_List.size === 0) {
                return 1
            }
            return m_List.size
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        // 출력 될 아이템 관리
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val context = parent.context

                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.list_child, parent, false)


                holder = CustomViewHolder()
                holder?.rank  = convertView?.findViewById(R.id.list_child_rank) as TextView
                holder?.rank?.setText(""+(position+1) + ". ")
                holder?.id = convertView?.findViewById(R.id.list_child_id) as TextView
                holder?.id?.setText(""+items.get(position).id)
                holder?.point = convertView?.findViewById(R.id.list_child_point) as TextView
                holder?.point?.setText(""+items.get(position).point)


            return convertView
        }

        // 외부에서 아이템 추가 요청 시 사용
        fun add(listChild: ListChild) {
            m_List.add(listChild)
        }

        // 외부에서 아이템 삭제 요청 시 사용
        fun remove(position: Int) {
            m_List.removeAt(position)
        }
    }

   class CustomViewHolder {
       var point : TextView? = null
       var rank : TextView? = null
       var id : TextView? = null
   }
}