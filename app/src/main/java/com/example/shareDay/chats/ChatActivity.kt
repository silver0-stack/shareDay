package com.example.shareDay.chats

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener
import android.widget.EditText
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.google.firebase.auth.FirebaseAuth

class ChatActivity : AppCompatActivity() {
    private var CHAT_NAME: String? = null
    private var USER_NAME: String? = null
    lateinit var adapter: ChatAdapter
    private var recyclerView: RecyclerView? = null
    private var chatText: EditText? = null
    private var sendButton: Button? = null
    private var mAuth: FirebaseAuth? = null
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 위젯 ID 참조
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        chatText = findViewById<View>(R.id.chatText) as EditText
        sendButton = findViewById<View>(R.id.sendButton) as Button

        // 로그인 화면에서 받아온 채팅방 이름, 유저 이름 저장
        val intent = intent
        CHAT_NAME = intent.getStringExtra("채팅방")
        USER_NAME = intent.getStringExtra("익명")

        // 채팅 방 입장
        openChat(CHAT_NAME)

        // 메시지 전송 버튼에 대한 클릭 리스너 지정
        sendButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (chatText!!.text.toString().trim().isEmpty())  {
                    Toast.makeText(this@ChatActivity, "글 제목을 작성해주세요.", Toast.LENGTH_LONG).show()
                }
                val chat = Chat(USER_NAME, chatText!!.text.toString().trim()) //ChatDTO를 이용하여 데이터를 묶는다.
                databaseReference.child("chat").child(CHAT_NAME!!).push().setValue(chat) // 데이터 푸쉬
                chatText!!.setText("") //입력창 초기화
            }
        })
    }

    private fun openChat(chatName: String?) {
        // 리스트 어댑터 생성 및 세팅
        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 등..리스너 관리
        databaseReference.child("chat").child(chatName!!)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    addMessage(dataSnapshot, adapter)
                    Log.e("LOG", "s:$s")
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
                override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    removeMessage(dataSnapshot, adapter)
                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
                override fun onCancelled(databaseError: DatabaseError) {}
            })
    }

    private fun addMessage(dataSnapshot: DataSnapshot, adapter: ChatAdapter) {
        val chat: Chat? = dataSnapshot.getValue(Chat::class.java)
        if (chat != null) {
            adapter.addChat(Chat("익명1", "생리대 하나 드립니다."))
        }
    }

    private fun removeMessage(dataSnapshot: DataSnapshot, adapter: ChatAdapter) {
        val chat: Chat? = dataSnapshot.getValue(Chat::class.java)
        if (chat != null) {
            adapter.remove(Chat("익명1", "생리대 하나 드립니다."))
        }
    }
}