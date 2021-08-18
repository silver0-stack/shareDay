package com.example.shareDay

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
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

    private lateinit var chatBtn: ImageButton

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

        chatBtn = view.findViewById(R.id.chatBtn)
        chatBtn.setOnClickListener{ view->
            //Log.d("btn", "click")
            Toast.makeText(context, "버튼 : database에 test 입력됨!", Toast.LENGTH_SHORT).show()
            FirebaseDatabase.getInstance().getReference("chatRoom").push().setValue("test")

            //아래는 fragment에서 activity로 넘어가는 코드
            //val intent = Intent(activity, ChatActivity::class.java)
            //intent.putExtra("", value)
            //startActivity(intent)
        }

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

        //예시 데이터
        listData.add(UserInfo("test1", "","ttttttt"))
        listData.add(UserInfo("test2", "", "eeeeeeeeeee"))
        listData.add(UserInfo("test3", "","sssssssssst" ))
    }

    /*override fun onItemClick(dataModel: UserInfo) {
        val transaction = activity?.supportFragmentManager!!.beginTransaction()

        val intent = Intent(context, ChatActivity::class.java)
        //intent.putExtra("", value)
        startActivity(intent)

    }*/

    override fun onItemClick() {
        //val transaction = activity?.supportFragmentManager!!.beginTransaction()

        //val intent = Intent(activity, ChatActivity::class.java)
        //intent.putExtra("", value)
        //startActivity(intent)
    }

    companion object{
        fun newInstance() =
            ChatFragment().apply{
                arguments = Bundle().apply{

                }
            }
    }
}