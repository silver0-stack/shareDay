package com.example.shareDay

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.chats.Chat
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.content.Intent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class ChatActivity : AppCompatActivity() { //1:1 채팅방
    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var chatList: List<Chat>? = null
    private var chatText: EditText? = null
    private var sendButton: Button? = null
    private var chatToolbar: TextView? = null

    //데이터베이스 읽고 쓰기 위해서
    private var myRef: DatabaseReference? = null
    val database = FirebaseDatabase.getInstance()

    lateinit var chatBackBtn: ImageButton

    var CHAT_NAME: String? = null
    var USER_NAME: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //유저 식별
        val user = Firebase.auth.currentUser

        // 다른 화면에서 받아온 채팅방 이름, 유저 이름 저장
        val intent = intent
        CHAT_NAME = intent.getStringExtra("chatName")
        USER_NAME = intent.getStringExtra("userName")

        // xml ID 참조 및 클릭리스너와 텍스트 제어
        chatText = findViewById<EditText>(R.id.chatText)
        sendButton = findViewById<Button>(R.id.sendButton)
        chatToolbar = findViewById<TextView>(R.id.chatToolbar)
        chatToolbar?.setText(CHAT_NAME)
        sendButton?.setOnClickListener(View.OnClickListener {
            //입력창에 메시지를 입력 후 버튼클릭했을 때
            val msg = this.chatText?.text.toString()
            if (msg != null) {
                val chat = Chat()
                chat.name = USER_NAME
                chat.msg = msg
                //val currentDateTime = Calendar.getInstance().time
                //var dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
                //chat.cTime =

                //메시지를 파이어베이스에 보냄.
                myRef!!.push().setValue(chat)
                chatText?.setText("")
            }
        })
        chatBackBtn = findViewById(R.id.chatBackBtn)
        chatBackBtn.setOnClickListener{
            //뒤로가기 버튼 : 여기 ChatActivity에서 채팅 탭 목록으로 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
            startActivity(intent) //인텐트 이동
            finish() //현재 액티비티 종료
        }

        //리사이클러뷰에 어댑터 적용
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)
        chatList = ArrayList()
        adapter = ChatAdapter(chatList as ArrayList<Chat>, USER_NAME!!, CHAT_NAME!!)
        recyclerView?.setAdapter(adapter)
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("chatRoom").child(CHAT_NAME!!)

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