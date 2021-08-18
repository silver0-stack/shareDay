package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class userInfoChangeActivity:AppCompatActivity() {
    lateinit var mypage_userinfochange_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_userinfo_change)

        mypage_userinfochange_back=findViewById(R.id.mypage_userinfochange_back)
        mypage_userinfochange_back.setOnClickListener{
            onBackPressed()
        }


    }
}