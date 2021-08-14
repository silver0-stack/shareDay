package com.example.shareDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.UserListAdapter
import com.example.shareDay.chats.UserInfo
import java.util.ArrayList

class ChatFragment : Fragment(R.layout.chat_fragment), UserListAdapter.ClickListener {
//ㅅㄷㄴㅅ
    private lateinit var adapter: UserListAdapter
    val listData: ArrayList<UserInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)

        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter(listData, this)
        recyclerView.adapter = adapter

        buildDisplayData()
    }

    private fun buildDisplayData(){
        listData.add(UserInfo("test1"))
        listData.add(UserInfo("test2"))
        listData.add(UserInfo("test3"))
    }

    //커밋테스트~
    override fun onItemClick(dataModel: UserInfo) {
        //val fragment: Fragment = DetailFragment.newInstance(UserInfo.name!!)
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        //transaction.hide(activity?.supportFragmentManager!!.findFragmentByTag(""))

    }

    companion object{
        fun newInstance() =
            ChatFragment().apply{
                arguments = Bundle().apply{

                }
            }
    }
}