package com.example.shareDay.chats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shareDay.R

//1:1채팅방에서 상대방 프로필을 눌렀을 때 뜨는 프로필창
class ProfilePopUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_pop_up)
    }
}