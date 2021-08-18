package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseHelpmeActivity:AppCompatActivity() {
    lateinit var howtousehelpme_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_helpme)

        howtousehelpme_back=findViewById(R.id.howtousehelpme_back)
        howtousehelpme_back.setOnClickListener{
            onBackPressed()
        }
    }
}