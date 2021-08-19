package com.example.shareDay.helpyou.activity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.shareDay.R
import com.example.shareDay.helpme.dto.pad2_PostingData
import com.example.shareDay.helpme.dto.total_PostingData
import com.example.shareDay.helpme.fragment.HelpMeTamponFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class HelpYouPadWriteActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    //firebase
    lateinit var storage: FirebaseStorage
    var photoUri: Uri? = null
    private lateinit var database: DatabaseReference
    private var mAuth: FirebaseAuth? = null

    lateinit var profileImg: ImageView //프사
    lateinit var name: TextView //이름
    lateinit var findMyLoc: ImageView //내 위치 설정 버튼
    lateinit var myLoc: TextView //내 위치 결과
    lateinit var contents: EditText //내용
    lateinit var boardType: RadioGroup //게시판 타입
    lateinit var tampon: RadioButton //탐폰
    lateinit var liner: RadioButton //라이너
    lateinit var pad: RadioButton //패드
    lateinit var total: RadioButton //전체
    lateinit var uploadBtn: ImageButton
    lateinit var uid: String
    lateinit var userName: String
//    private lateinit var totalFragment: HelpMeTotalFragment
//    private lateinit var padFragment: HelpMePadFragment
//    private lateinit var linerFragment: HelpMeLinerFragment
//    private lateinit var tamponFragment: HelpMeTamponFragment


    //뒤로가기 버튼
    lateinit var backBtn: AppCompatImageView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.helpme_write_activity)

        //firebase
        database = Firebase.database.reference
        uploadBtn = findViewById(R.id.HmUploadBtn)
        profileImg = findViewById(R.id.HmItemImg)
        name = findViewById(R.id.HmItemName)
        findMyLoc = findViewById(R.id.HmFindLoc)
        myLoc = findViewById(R.id.HmItemLoc)
        contents = findViewById(R.id.HmItemContents)
        boardType = findViewById(R.id.HmItemType)
        tampon = findViewById(R.id.hm_item_tampon)
        liner = findViewById(R.id.hm_item_pantyliner)
        pad = findViewById(R.id.hm_item_pad)
        total = findViewById(R.id.hm_item_total)
        backBtn = findViewById(R.id.backBtn)

        //저장소 가져오기
        storage = FirebaseStorage.getInstance()

        //뒤로 가기 버튼 리스너
        backBtn.setOnClickListener {
            onBackPressed()
        }

        //내 위치 설정하기 버튼 이벤트 리스터
        findMyLoc.setOnClickListener {
            myLoc.text = "카페 H CUBE"
        }

        //완료 버튼 이벤트
        uploadBtn.setOnClickListener {
            posting()
            //soldPosting()
            onBackPressed()
        }
    }

    private fun posting() {
        val myLocation = myLoc.text.toString().trim()
        val contents = contents.text.toString().trim()
        boardType.setOnCheckedChangeListener(this);

        //누가 올렸는지 식별하기 위해 글 쓴 회원정보 갖고오기
        val userUID = Firebase.auth.currentUser?.uid
        var userNick :String = "익명"
        var db: DatabaseReference
        db = FirebaseDatabase.getInstance().reference
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userNick = snapshot.child("Users").child(userUID.toString()).child("userNickname").value.toString()
                Log.e("nick", userNick)

                //글 업로드
                writeNewPost(
                    myLocation,
                    contents,
                    userNick,
                    userUID!!,
                )
            }
            override fun onCancelled(error: DatabaseError) {}
        })

    }

    private fun writeNewPost(
        myLocation: String,
        contents: String,
        userName:String,
        uid:String
    ) {
        if (myLocation.isEmpty()) {
            Toast.makeText(this, "나의 위치를 설정해주세요", Toast.LENGTH_LONG).show()
        } else if (contents.isEmpty()) {
            Toast.makeText(this, "글의 내용을 작성해주세요.", Toast.LENGTH_LONG).show()
        } else {
            val key = database.child("helpyou_pad").push().key
            if (key == null) {
                Log.w(ContentValues.TAG, "Couldn't get push key for posts")
                return
            }
            val newPost = pad2_PostingData(
                myLocation,
                contents,
                userName,
                uid
            )
            database.child("helpyou_pad").child(key).setValue(newPost).addOnSuccessListener {
                Toast.makeText(this, "⭕업로드 성공⭕", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

        val boardTypeId = boardType.checkedRadioButtonId //숫자로 나타남
        val boardType = resources.getResourceEntryName(boardTypeId) //선택한 라디오 버튼 아이디

        val ft = supportFragmentManager.beginTransaction()

        when (boardTypeId) {
            R.id.hm_item_tampon -> ft.add(R.id.helpme_tampon_recycler, HelpMeTamponFragment()).commit()
            R.id.hm_item_pad -> ft.add(R.id.helpme_tampon_recycler, HelpMeTamponFragment()).commit()
            R.id.hm_item_pantyliner -> ft.add(R.id.helpme_tampon_recycler, HelpMeTamponFragment()).commit()
            R.id.hm_item_total -> ft.add(R.id.helpme_tampon_recycler, HelpMeTamponFragment()).commit()
        }
    }
}
