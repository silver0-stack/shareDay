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
import com.example.shareDay.helpme.activity.HelpMeLinerWriteActivity
import com.example.shareDay.helpme.activity.HelpMePadWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTamponWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTotalWriteActivity
import com.example.shareDay.helpme.dto.total
import com.example.shareDay.helpme.adapter.HelpmeListLinerAdapter
import com.example.shareDay.helpme.dto.pad2
import com.example.shareDay.helpyou.activity.HelpYouLinerWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouPadWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTamponWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTotalWriteActivity
import com.example.shareDay.helpyou.adapter.HelpYouPadAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpYouPadFragment: Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<pad2>
    private lateinit var perView : View

    //플로팅버튼 애니메이션을 위한 변수
    lateinit var hmTotalFab: FloatingActionButton
    lateinit var hmPadFab: FloatingActionButton
    lateinit var hmTamponFab: FloatingActionButton
    lateinit var hmLinerFab: FloatingActionButton
    lateinit var fabMain: FloatingActionButton
    private var isFabOpen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //helpme_liner_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpyou_pad_fragment, container, false)
        userRecyclerView = perView.findViewById(R.id.helpyou_pad_recycler)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()

        hmLinerFab = perView.findViewById(R.id.helpyou_liner_fab) //liner
        hmTotalFab =perView.findViewById(R.id.helpyou_total_fab) //total
        hmTamponFab = perView.findViewById(R.id.helpyou_tampon_fab) //tampon
        hmPadFab =perView.findViewById(R.id.helpyou_pad_fab) //pad
        fabMain = perView.findViewById(R.id.tog_btn) //main fab

        return perView
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("helpyou_pad")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(pad2::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = HelpYouPadAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain.setOnClickListener {
            toggleFab()
        }
        //total 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        hmTotalFab.setOnClickListener {
            val intent = Intent(activity, HelpMeTotalWriteActivity::class.java)
            startActivity(intent)
        }
        //pad 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmPadFab.setOnClickListener {
            val intent = Intent(activity, HelpMePadWriteActivity::class.java)
            startActivity(intent)
        }
        //liner 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmLinerFab.setOnClickListener {
            val intent = Intent(activity, HelpMeLinerWriteActivity::class.java)
            startActivity(intent)
        }
        //tampon 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmTamponFab.setOnClickListener {
            val intent = Intent(activity, HelpMeTamponWriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun toggleFab() {
        //플로팅 액션 버튼 닫기/열기
        if (isFabOpen) {
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", 0f).apply { start() }
            fabMain.setImageResource(R.drawable.x_icon)

        } else {
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", -600f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", -800f).apply { start() }
            fabMain.setImageResource(R.drawable.writing_icon)
        }
        isFabOpen = !isFabOpen
    }

}