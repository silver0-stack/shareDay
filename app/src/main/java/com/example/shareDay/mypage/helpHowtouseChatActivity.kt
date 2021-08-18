package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseChatActivity:AppCompatActivity() {
    lateinit var howtousechat_back:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_chat)

        howtousechat_back=findViewById(R.id.howtousechat_back)
        howtousechat_back.setOnClickListener{
            onBackPressed()
        }
    }
}