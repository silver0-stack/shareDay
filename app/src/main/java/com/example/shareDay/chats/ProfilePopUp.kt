package com.example.shareDay.chats

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.shareDay.R
import android.content.Intent
import android.graphics.Color
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import com.example.shareDay.ChatActivity
import com.example.shareDay.ChatFragment

import com.example.shareDay.MainActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.*
import org.w3c.dom.Text


//1:1채팅방에서 상대방 프로필을 눌렀을 때 뜨는 프로필창
class ProfilePopUp : AppCompatActivity() {

    lateinit var database: DatabaseReference
    private var CHATROOM_NAME: String? = null
    private var USER_NAME: String? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pop_up)

        //데이터베이스
        database = Firebase.database.reference

        // 다른 화면에서 받아온 채팅방 이름, 유저 이름 저장
        val intent = intent
        CHATROOM_NAME = intent.getStringExtra("chatName")
        USER_NAME = intent.getStringExtra("userName")
        //임시로 다른 값 넣어둠
        //CHATROOM_NAME = "테스트채팅방1"

        // id 참조
        val btn_Good : ImageButton = findViewById(R.id.profile_Good)
        val btn_bad : ImageButton = findViewById(R.id.profile_Bad)
        val btn_leave : ImageButton = findViewById(R.id.profile_Leave)
        val btn_close : ImageButton = findViewById(R.id.profileCloseBtn)
        val profile_name : TextView = findViewById(R.id.profile_name)

        // 텍스트 제어
        profile_name.setText(CHATROOM_NAME)

        // 클릭
        // 해결 버튼
        btn_Good.setOnClickListener {
            btn_Good.setBackgroundColor(Color.rgb(226, 52, 141))
        }
        // 미해결 버튼
        btn_bad.setOnClickListener {
            btn_Good.setBackgroundColor(Color.rgb(226, 52, 141))
        }
        // 나가기 버튼
        btn_leave.setOnClickListener {
            //데이터베이스에서 해당 채팅방 삭제
            database.child("chatRoom").child(CHATROOM_NAME!!).removeValue()

            //지금 액티비티에서 채팅 탭 목록으로 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
            startActivity(intent) //인텐트 이동
            finish() //현재 액티비티 종료


        }
        // 닫기 버튼
        btn_close.setOnClickListener{
            //지금 액티비티에서 1:1채팅창 액티비티로 이동하는 인텐트 설정
            val intent = Intent(this, ChatActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
            startActivity(intent) //인텐트 이동
            finish() //현재 액티비티 종료
        }
    }
}