package com.example.shareDay.user

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.MainActivity
import com.example.shareDay.R
import com.google.android.gms.common.SignInButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    lateinit var loginBtn: Button //로그인 버튼
    lateinit var signUpBtn: TextView //회원가입 버튼
    lateinit var auth: FirebaseAuth
    lateinit var makeId: EditText
    lateinit var makePw: TextInputEditText
    //lateinit var btn_googleSignIn: SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        loginBtn = findViewById(R.id.loginBtn)
        signUpBtn = findViewById(R.id.signUpBtn)
        makeId = findViewById(R.id.userId)
        makePw = findViewById(R.id.userPw)
       // btn_googleSignIn=findViewById(R.id.btn_googleSignIn)
        auth = FirebaseAuth.getInstance()

        /*회원가입 버튼으로 회원가입 화면으로 전환*/
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        /*구글 로그인 버튼 클릭 시 이벤트 리스너*/
//        //btn_googleSignIn.setOnClickListener {
//            val intent=Intent(this,GoogleSignInActivity::class.java)
//            startActivity(intent)
//        }
        loginBtn.setOnClickListener {
            val userId = makeId.text.toString().trim()
            val userPw = makePw.text.toString().trim()

            loginUser(userId, userPw)
        }
    }

    private fun loginUser(userId: String, userPw: String) {
        if (userId.isEmpty()) {
            Toast.makeText(this, "아이디를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else if (userPw.isEmpty()) {
            Toast.makeText(this, "비밀번호를 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {
            auth.signInWithEmailAndPassword(userId, userPw)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "userLogin:success")
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w(ContentValues.TAG, "userLogin:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Error Message: " + task.exception?.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}