package com.example.shareDay.mypage

import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

//마이페이지- 도움내역 페이지

class helpHistoryActivity:AppCompatActivity() {
    lateinit var mypage_helphistory_back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_helphistory)

       mypage_helphistory_back=findViewById(R.id.mypage_helphistory_back)
        mypage_helphistory_back.setOnClickListener{
            onBackPressed()
        }
    }
}