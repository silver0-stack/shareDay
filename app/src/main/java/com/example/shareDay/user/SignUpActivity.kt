package com.example.shareDay.user

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.MainActivity
import com.example.shareDay.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var btnSignOk: Button
    private lateinit var makeId: EditText
    private lateinit var makePw: EditText
    private lateinit var confirmPw: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var refusers: DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        btnSignOk = findViewById(R.id.btnSignOk) //회원가입 ok 버튼
        makeId = findViewById(R.id.makeId)
        makePw = findViewById(R.id.makePw)
        confirmPw = findViewById(R.id.confirmPw)


        btnSignOk.setOnClickListener {
            val userId = makeId.text.toString().trim()
            val userPw = makePw.text.toString().trim()
            val userConfirmPw = confirmPw.text.toString()

            registerUser(userId, userPw, userConfirmPw)

        }
    }

    private fun registerUser(
        userId: String,
        userPw: String,
        userConfirmPw: String,
    ) {

        when {
            userId.isEmpty() -> {
                Toast.makeText(this, "아이디를 작성해주세요.", Toast.LENGTH_LONG).show()
            }
            userPw.isEmpty() -> {
                Toast.makeText(this, "비밀번호를 작성해주세요.", Toast.LENGTH_LONG).show()
            }
            userConfirmPw.isEmpty() -> {
                Toast.makeText(this, "비밀번호 확인란을 작성해주세요.", Toast.LENGTH_LONG).show()
            }
            userConfirmPw != userPw -> {
                Toast.makeText(this, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show()
            }
            else -> {
                auth.createUserWithEmailAndPassword(userId, userPw)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "createUserWithIdAndPw:success")
                            val user: FirebaseUser? = auth.currentUser //현재 로그인한 사용자
                            if (user != null) {
                                firebaseUserID = user.uid
                            }
                            refusers = FirebaseDatabase.getInstance().reference.child("Users")
                                .child(firebaseUserID)

                            val userHashMap = HashMap<String, Any>()
                            userHashMap["uid"] = this.firebaseUserID
                            userHashMap["userId"] = userId
                            userHashMap["userPw"] = userPw

                            refusers.updateChildren(userHashMap)
                                .addOnCompleteListener {
                                    if (task.isSuccessful) {
                                        val intent =
                                            Intent(this, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        finish()
                                    }
                                }

                        } else {
                            Log.w(ContentValues.TAG, "createUserWithIdAndPw:failure", task.exception)
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
}