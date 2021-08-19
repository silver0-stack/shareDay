package com.example.shareDay.mypage

//마이페이지- 개인정보 수정 페이지
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.MainActivity
import com.example.shareDay.R
import com.example.shareDay.user.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.mypage.*
import kotlin.system.exitProcess

class userInfoModificationActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    lateinit var mypage_userinfo_back: ImageButton
    lateinit var mypage_userinfo_modification: Button
    lateinit var removeUser: TextView
    lateinit var logOut: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mypage_userinfo_modification)

        mypage_userinfo_back = findViewById(R.id.mypage_userinfo_back)
        mypage_userinfo_modification = findViewById(R.id.mypage_userinfo_modification)
        removeUser = findViewById(R.id.removeUser)
        logOut = findViewById(R.id.logOut)
        mAuth = FirebaseAuth.getInstance()


        //회원탈퇴 누르면 로그아웃 되면서 로그인 화면으로 이동
        //유저 정보 삭제로 인해 해당 아이디로 재접속 불가능
        removeUser.setOnClickListener {
            mAuth.currentUser!!.delete()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        //로그아웃
        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        //뒤로 가기 버튼
        mypage_userinfo_back.setOnClickListener {
            onBackPressed()
        }

        //회원정보 수정
        mypage_userinfo_modification.setOnClickListener {
            val intent = Intent(this, userInfoChangeActivity::class.java)
            startActivity(intent)
        }
    }
}
