package com.example.shareDay.mypage


//마이메이지-공지사항 페이지

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class settingsActivity :AppCompatActivity(){
    lateinit var mypage_notice_back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_notice)

        mypage_notice_back=findViewById(R.id.mypage_notice_back)
        mypage_notice_back.setOnClickListener{
           onBackPressed()
        }
    }
}