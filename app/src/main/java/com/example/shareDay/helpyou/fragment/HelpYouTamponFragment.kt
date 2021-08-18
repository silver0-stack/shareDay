package com.example.shareDay.helpyou.fragment

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.helpme.dto.total
import com.example.shareDay.helpme.adapter.HelpmeListLinerAdapter
import com.example.shareDay.helpme.dto.tampon2
import com.example.shareDay.helpyou.activity.HelpYouLinerWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouPadWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTamponWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTotalWriteActivity
import com.example.shareDay.helpyou.adapter.HelpYouTamponAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpYouTamponFragment: Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<tampon2>
    private lateinit var perView : View

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var hyTotalFab: FloatingActionButton
    lateinit var hyPadFab: FloatingActionButton
    lateinit var hyTamponFab: FloatingActionButton
    lateinit var hyLinerFab: FloatingActionButton
    lateinit var fabMain: FloatingActionButton
    private var isFabOpen = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //helpme_liner_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpyou_tampon_fragment, container, false)

        hyLinerFab = perView.findViewById(R.id.helpyou_liner_fab) //liner
        hyTotalFab = perView.findViewById(R.id.helpyou_total_fab) //total
        hyTamponFab = perView.findViewById(R.id.helpyou_tampon_fab) //tampon
        hyPadFab = perView.findViewById(R.id.helpyou_pad_fab) //pad
        fabMain = perView.findViewById(R.id.tog_btn) //main fab

        userRecyclerView = perView.findViewById(R.id.helpyou_tampon_recycler)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()

        return perView
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain.setOnClickListener {
            toggleFab()
        }
        //total 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        hyTotalFab.setOnClickListener {
            val intent = Intent(activity, HelpYouTotalWriteActivity::class.java)
            startActivity(intent)
        }
        //pad 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hyPadFab.setOnClickListener {
            val intent = Intent(activity, HelpYouPadWriteActivity::class.java)
            startActivity(intent)
        }
        //liner 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hyLinerFab.setOnClickListener {
            val intent = Intent(activity, HelpYouLinerWriteActivity::class.java)
            startActivity(intent)
        }
        //tampon 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hyTamponFab.setOnClickListener {
            val intent = Intent(activity, HelpYouTamponWriteActivity::class.java)
            startActivity(intent)
        }

    }


    private fun toggleFab() {
        //플로팅 액션 버튼 닫기/열기
        if (isFabOpen) {
            ObjectAnimator.ofFloat(hyTamponFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hyLinerFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hyPadFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hyTotalFab, "translationY", 0f).apply { start() }
            fabMain.setImageResource(R.drawable.x_icon)

        } else {
            ObjectAnimator.ofFloat(hyTamponFab, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(hyLinerFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(hyPadFab, "translationY", -600f).apply { start() }
            ObjectAnimator.ofFloat(hyTotalFab, "translationY", -800f).apply { start() }
            fabMain.setImageResource(R.drawable.writing_icon)
        }
        isFabOpen = !isFabOpen
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("helpyou_tampon")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(tampon2::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = HelpYouTamponAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}