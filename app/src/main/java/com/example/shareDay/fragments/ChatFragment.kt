package com.example.shareDay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.chats.UserInfo
import com.example.shareDay.chats.UserListAdapter
import java.util.ArrayList

class ChatFragment : Fragment() {

    var userList: RecyclerView? = null
    var adapter: UserListAdapter? = null
    var items: ArrayList<UserInfo> = ArrayList<UserInfo>()

    private var linearLayoutManager: RecyclerView.LayoutManager? = null
    private var recyclerAdapter: RecyclerView.Adapter<UserListAdapter.Holder>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.chat_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.list)

        recyclerAdapter = UserListAdapter(userList)
        linearLayoutManager = LinearLayoutManager(activity)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = recyclerAdapter
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}