package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseMypageActivity:AppCompatActivity() {
    lateinit var howtousemypage_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_mypage)

        howtousemypage_back=findViewById(R.id.howtousemypage_back)
        howtousemypage_back.setOnClickListener{
            onBackPressed()
        }
    }
}