package com.example.shareDay.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.R

//마이페이지- 도움말페이지

class helpActivity:AppCompatActivity() {
    lateinit var mypage_help_back: ImageButton
    lateinit var howtousemap_btn: ImageButton
    lateinit var howtousehelpyou_btn: ImageButton
    lateinit var howtousehelpme_btn: ImageButton
    lateinit var howtousechat_btn: ImageButton
    lateinit var howtousemypage_btn: ImageButton
    lateinit var howtousesharebox_btn: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_help)

       mypage_help_back=findViewById(R.id.mypage_help_back)
        howtousemap_btn=findViewById(R.id.howtousemap_btn)
        howtousehelpyou_btn=findViewById(R.id.howtousehelpyou_btn)
        howtousehelpme_btn=findViewById(R.id.howtousehelpme_btn)
        howtousechat_btn=findViewById(R.id.howtousechat_btn)
        howtousemypage_btn=findViewById(R.id.howtousemypage_btn)
        howtousesharebox_btn=findViewById(R.id.howtousesharebox_btn)

        //뒤로가기 버
        mypage_help_back.setOnClickListener{
            onBackPressed()
        }

        //지도 설명 페이지
        howtousemap_btn.setOnClickListener{
            var intent =Intent(this,helpHowtouseMapActivity::class.java)
            startActivity(intent)
        }

        //도와줄게요 설명 페이지
        howtousehelpyou_btn.setOnClickListener {
            var intent = Intent(this, helpHowtouseHelpyouActivity::class.java)
            startActivity(intent)


        }
        //도와주세요 설명 페에지
        howtousehelpme_btn.setOnClickListener{
            var intent=Intent(this,helpHowtouseHelpmeActivity::class.java)
            startActivity(intent)


        }
        //채팅 설명 페이지
        howtousechat_btn.setOnClickListener{
            var intent=Intent(this,helpHowtouseChatActivity::class.java)
            startActivity(intent)


        }

        //마이페이지 설명 페이지
        howtousemypage_btn.setOnClickListener{
            var intent=Intent(this,helpHowtouseMypageActivity::class.java)
            startActivity(intent)


        }

        //양심생리대 설명 페이지
        howtousesharebox_btn.setOnClickListener{
            var intent=Intent(this,helpHowtouseShareboxActivity::class.java)
            startActivity(intent)


        }
    }

}