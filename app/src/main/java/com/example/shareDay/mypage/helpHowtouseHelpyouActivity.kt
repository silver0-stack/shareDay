package com.example.shareDay.mypage

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class helpHowtouseHelpyouActivity:AppCompatActivity() {
    lateinit var howtousehelpyou_back:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help_howtouse_helpyou)

        howtousehelpyou_back=findViewById(R.id.howtousehelpyou_back)
        howtousehelpyou_back.setOnClickListener{
            onBackPressed()
        }
    }
}