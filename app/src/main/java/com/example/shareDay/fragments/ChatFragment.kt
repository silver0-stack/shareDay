package com.example.shareDay

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.UserInfo
import com.google.firebase.database.*
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
                val chat_room = snapshot.child("chatRoom")
                for(item in chat_room.children){
                    //val name : String = item.child("name").value as String
                    //val about : String = item.child("msg").value as String

                    //val user_info = UserInfo(name, "", about)
                    //listData.add(user_info)
                    //listData.add(UserInfo(name,"",about))
                    Log.e("snap", item.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        //예시 데이터
        listData.add(UserInfo("test1", "","ttttttt"))
        listData.add(UserInfo("test2", "", "eeeeeeeeeee"))
        listData.add(UserInfo("test3", "","sssssssssst" ))
        Log.e("snap", listData.toString())
    }

    override fun onItemClick() {}

    companion object{
        fun newInstance() =
            ChatFragment().apply{
                arguments = Bundle().apply{

                }
            }
    }
}