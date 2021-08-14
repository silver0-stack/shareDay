package com.example.shareDay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.UserListAdapter
import com.example.shareDay.chats.UserInfo
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.ArrayList

class ChatFragment : Fragment(R.layout.chat_fragment), UserListAdapter.ClickListener {

    private lateinit var adapter: UserListAdapter
    val listData: ArrayList<UserInfo> = ArrayList()

    private val sendButton: Button? = null

    private lateinit var db: DatabaseReference

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

        db = FirebaseDatabase.getInstance().reference
        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        listData.add(UserInfo("test1", "","ttttttt"))
        listData.add(UserInfo("test2", "", "eeeeeeeeeee"))
        listData.add(UserInfo("test3", "","sssssssssst" ))
    }

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