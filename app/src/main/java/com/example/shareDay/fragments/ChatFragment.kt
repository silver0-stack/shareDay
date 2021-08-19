package com.example.shareDay

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.Chat
import com.example.shareDay.chats.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.util.ArrayList

class ChatFragment : Fragment(R.layout.chat_fragment), UserListAdapter.ClickListener {

    private lateinit var adapter: UserListAdapter
    val listData: ArrayList<UserInfo> = ArrayList()

    private lateinit var chatBtn: ImageButton
    private lateinit var db: DatabaseReference

    var USER_NAME_ME: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.chat_fragment, container, false)

        //유저 식별
        val USER_UID = Firebase.auth.currentUser?.uid.toString()
        val USER_Email = Firebase.auth.currentUser?.email

        initRecyclerView(view)
        buildDisplayData()

        chatBtn = view.findViewById(R.id.chatBtn)
        chatBtn.setOnClickListener{ view->
            //Log.d("btn", "click")
            Toast.makeText(context, "버튼 : database 입력", Toast.LENGTH_SHORT).show()
            val newChatRoom = Chat(USER_Email, "새로운 채팅방 입니다.")
            //FirebaseDatabase.getInstance().getReference("chatRoom").child(USER_UID).setValue(newChatRoom)
        }

        return view
    }

    private fun initRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = UserListAdapter(listData, this)
        recyclerView.adapter = adapter
    }

    private fun buildDisplayData(){

        db = FirebaseDatabase.getInstance().reference

        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear() //기존 리스트 초기화
                val chat_room = snapshot.child("chatRoom")
                for(item in chat_room.children){
                    val chatKey : String = item.key.toString()
                    val chat_count = item.children.count()
                    val lastMsg = item.children.last().child("msg").value

                    val users = UserInfo(chatKey, "", lastMsg.toString(), chat_count)
                    listData.add(users)
                    //listData.add(UserInfo("","","",)) //위의 코드를 축약한 형태
                }
                adapter.notifyDataSetChanged() //저장 및 새로고침해서 반영시키기
                Log.e("snap", listData.toString())
            }

            override fun onCancelled(error: DatabaseError) {}
        })
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