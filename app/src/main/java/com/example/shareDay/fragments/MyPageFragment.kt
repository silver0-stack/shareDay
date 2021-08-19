package com.example.shareDay.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shareDay.MainActivity
import com.example.shareDay.R
import com.example.shareDay.mypage.*
import com.example.shareDay.user.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.helpyou_write_list.view.*
import kotlinx.android.synthetic.main.mypage.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MyPageFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null
    private var PICK_IMAGE_FROM_ALBUM = 0 //앨범 픽 변수
    private var storage: FirebaseStorage? = null
    var photoUri: Uri? = null
    lateinit var myPageView: View
    lateinit var alarmIcon: ImageView
    lateinit var myPhoto: ImageView
    lateinit var myPhotoChange: ImageView
    lateinit var userNameView : TextView
    lateinit var helpHistoryBtn: Button
    lateinit var useHistoryBtn: Button
    lateinit var userInfoModification: Button
    lateinit var myWriting: Button
    lateinit var settings: Button
    lateinit var help: Button
    lateinit var image1: String //이미지 이름
    lateinit var uid : String //uid
    lateinit var userName: TextView //유저네임

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        myPageView = inflater.inflate(
            R.layout.mypage,
            container,
            false
        )

        userNameView = myPageView.findViewById(R.id.userName)

        //데이터베이스에서 유저 닉네임 받아오기
        val userUID = Firebase.auth.currentUser?.uid
        var userNick = ""
        val db: DatabaseReference = FirebaseDatabase.getInstance().reference
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userNick = snapshot.child("Users").child(userUID.toString()).child("userNickname").value.toString()
                Log.e("nick", userNick)
                userNameView.setText(userNick)
            }

            override fun onCancelled(error: DatabaseError) {}
        })


        //현재 유저 이메일을 마이페이지 이름에 받음
        userName = myPageView.findViewById(R.id.userName)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        if (user != null) {
            userName.text= user.email
        }

        //알람 리스너
        alarmIcon = myPageView.findViewById(R.id.alarmIcon)
        alarmIcon.setOnClickListener { } //여기 알람 내역 액티비티로 인텐트


        //앨범 열기
        myPhoto = myPageView.findViewById(R.id.myPhoto)
        myPhotoChange = myPageView.findViewById(R.id.myPhotoChange)
        myPhotoChange.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, PICK_IMAGE_FROM_ALBUM)
        }

        //도움내역
        helpHistoryBtn = myPageView.findViewById(R.id.helpHistoryBtn)
        helpHistoryBtn.setOnClickListener {
            val intent = Intent(activity, helpHistoryActivity::class.java)
            startActivity(intent)
        }

        //사용내역
        useHistoryBtn = myPageView.findViewById(R.id.useHistoryBtn)
        useHistoryBtn.setOnClickListener {
            val intent = Intent(activity, useHistoryActivity::class.java)
            startActivity(intent)
        }

        //개인정보수정
        userInfoModification = myPageView.findViewById(R.id.userInfoModification)
        userInfoModification.setOnClickListener {
            val intent = Intent(activity, userInfoModificationActivity::class.java)
            startActivity(intent)
        }

        //내가 쓴 글
        myWriting = myPageView.findViewById(R.id.myWriting)
        myWriting.setOnClickListener {
            val intent = Intent(activity, myWritingActivity::class.java)
            startActivity(intent)
        }

        //공지사항
        settings = myPageView.findViewById(R.id.settings)
        settings.setOnClickListener {
            val intent = Intent(activity, settingsActivity::class.java)
            startActivity(intent)
        }

        //도움
        help = myPageView.findViewById(R.id.help)
        help.setOnClickListener {
            val intent = Intent(activity, helpActivity::class.java)
            startActivity(intent)
        }

        return myPageView
    }


    //프로필 사진 선택 & 업로드
    @SuppressLint("SimpleDateFormat")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_FROM_ALBUM) {
            if (resultCode == Activity.RESULT_OK) {
                //선택된 이미지 path
                photoUri = data?.data
                myPhoto.setImageURI(photoUri)

                val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "IMAGE_" + timestamp + "_.png"
                image1 = imageFileName //설정한 이미지 이름을 넣어줌(url 만들 때 필요함)
                val storageRef = storage?.reference?.child("profileImg")?.child(imageFileName)
                //파일업로드
                storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
                    //누구 프사인지 식별하기 위해 회원 정보 갖고 오기
                    mAuth = FirebaseAuth.getInstance()
                    val user = mAuth!!.currentUser

                    Toast.makeText(activity, "프로필 사진 설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "프로필 사진을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
