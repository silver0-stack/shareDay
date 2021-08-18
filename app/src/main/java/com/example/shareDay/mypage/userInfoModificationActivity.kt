package com.example.shareDay.mypage

//마이페이지- 개인정보 수정 페이지
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

class userInfoModificationActivity:AppCompatActivity(){
    lateinit var mypage_userinfo_back: ImageButton
    lateinit var mypage_userinfo_modification: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_userinfo_modification)

        mypage_userinfo_back=findViewById(R.id.mypage_userinfo_back)
        mypage_userinfo_modification=findViewById(R.id.mypage_userinfo_modification)

        mypage_userinfo_back.setOnClickListener{
            onBackPressed()
        }


        mypage_userinfo_modification.setOnClickListener{
            var intent=Intent(this, userInfoChangeActivity::class.java)
            startActivity(intent)
        }
    }
}