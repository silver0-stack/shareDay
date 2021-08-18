package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseMapActivity:AppCompatActivity() {

    lateinit var howtousemap_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_map)

        howtousemap_back.setOnClickListener{
            onBackPressed()
        }
    }
}