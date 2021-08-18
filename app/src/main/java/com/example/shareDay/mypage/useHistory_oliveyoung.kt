package com.example.shareDay.mypage

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class useHistory_oliveyoung: AppCompatActivity() {
    lateinit var mypage_usehistroy_oliveyoung_back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_usehistory_oliveyoung)

        mypage_usehistroy_oliveyoung_back=findViewById(R.id.mypage_usehistroy_oliveyoung_back)
        mypage_usehistroy_oliveyoung_back.setOnClickListener{
            onBackPressed()
        }
    }
}