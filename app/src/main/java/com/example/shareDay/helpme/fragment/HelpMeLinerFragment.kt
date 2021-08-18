package com.example.shareDay.helpme.fragment

import com.example.shareDay.R
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
import com.example.shareDay.helpme.dto.total
import com.example.shareDay.helpme.activity.HelpMeLinerWriteActivity
import com.example.shareDay.helpme.activity.HelpMePadWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTamponWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTotalWriteActivity
import com.example.shareDay.helpme.adapter.HelpmeListLinerAdapter
import com.example.shareDay.helpme.dto.liner
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpMeLinerFragment : Fragment() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<liner>
    private lateinit var perView: View

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
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        //helpme_liner_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpme_liner_fragment, container, false)

        hmLinerFab = perView.findViewById(R.id.helpme_liner_fab) //liner
        hmTotalFab = perView.findViewById(R.id.helpme_total_fab) //total
        hmTamponFab = perView.findViewById(R.id.helpme_tampon_fab) //tampon
        hmPadFab = perView.findViewById(R.id.helpme_pad_fab) //pad
        fabMain = perView.findViewById(R.id.tog_btn) //main fab

        userRecyclerView = perView.findViewById(R.id.helpme_liner_recycler)
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
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", 0f).apply { start() }
            fabMain.setImageResource(R.drawable.x_icon)

        } else {
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", -200f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", -600f).apply { start() }
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", -800f).apply { start() }
            fabMain.setImageResource(R.drawable.writing_icon)
        }
        isFabOpen = !isFabOpen
    }


    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("helpme_liner")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(liner::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = HelpmeListLinerAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
