package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseShareboxActivity:AppCompatActivity() {
    lateinit var howtousesharebox_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_sharebox)

        howtousesharebox_back=findViewById(R.id.howtousesharebox_back)
        howtousesharebox_back.setOnClickListener{
            onBackPressed()
        }
    }
}