package com.example.shareDay

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.Chat
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.shareDay.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareDay.chats.ChatAdapter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import java.util.ArrayList

class ChatActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var chatList: List<Chat>? = null
    private val nickname = "익명1"
    private val chatText: EditText? = null
    private val sendButton: Button? = null
    private var myRef: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        //chatText = findViewById(R.id.chatText)
        //sendButton = findViewById(R.id.sendButton)
        sendButton?.setOnClickListener(View.OnClickListener {
            //입력창에 메시지를 입력 후 버튼클릭했을 때
            val msg = this.chatText?.getText().toString()
            if (msg != null) {
                val chat = Chat()
                chat.name = nickname
                chat.msg = msg

                //메시지를 파이어베이스에 보냄.
                myRef!!.push().setValue(chat)
                chatText?.setText("")
            }
        })
        //리사이클러뷰에 어댑터 적용
        //recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)
        chatList = ArrayList()
        adapter = ChatAdapter(chatList as ArrayList<Chat>, nickname)
        recyclerView?.setAdapter(adapter)
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("message")

        //데이터들을 추가, 변경, 제거, 이동, 취소
        myRef!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                //어댑터에 DTO추가
                val chat = snapshot.getValue(Chat::class.java)
                (adapter as ChatAdapter).addChat(chat!!)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onChildRemoved(snapshot: DataSnapshot) {}
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}