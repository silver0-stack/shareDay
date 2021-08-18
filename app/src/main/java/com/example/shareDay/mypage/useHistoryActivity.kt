package com.example.shareDay.mypage


//마이페이지- 사용내역 페이지
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class useHistoryActivity:AppCompatActivity() {
    lateinit var mypage_usehistory_back: ImageButton
    lateinit var mypage_oliveyoung_button:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.mypage_usehistory)

        mypage_usehistory_back=findViewById(R.id.mypage_usehistroy_back)
        mypage_usehistory_back.setOnClickListener{
            onBackPressed()
        }

        mypage_oliveyoung_button=findViewById(R.id.mypage_oliveyoung_button)
        mypage_oliveyoung_button.setOnClickListener{
           var intent=Intent(this,useHistory_oliveyoung::class.java)
            startActivity(intent)
        }
    }
}