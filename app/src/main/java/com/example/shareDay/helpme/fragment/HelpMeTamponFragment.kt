package com.example.shareDay.helpme.fragment

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
import com.example.shareDay.helpme.adapter.HelpmeListTamponAdapter
import com.example.shareDay.helpme.dto.tampon
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpMeTamponFragment: Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<tampon>
    private lateinit var perView : View

    //플로팅버튼 애니메이션을 위한 변수
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
        //helpme_tampon_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpme_tampon_fragment, container, false)

        userRecyclerView = perView.findViewById(R.id.helpme_tampon_recycler)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()


        hmLinerFab = perView.findViewById(R.id.helpme_liner_fab) //liner
        hmTamponFab = perView.findViewById(R.id.helpme_tampon_fab) //tampon
        hmPadFab =perView.findViewById(R.id.helpme_pad_fab) //pad
        fabMain = perView.findViewById(R.id.tog_btn) //main fab

        return perView
    }


    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("helpme_tampon")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(tampon::class.java)
                        userArrayList.add(user!!)
                        userArrayList.reverse()
                    }
                    userRecyclerView.adapter = HelpmeListTamponAdapter(userArrayList)
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
            ObjectAnimator.ofFloat(hmPadFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", 0f).apply { start() }
            fabMain.setImageResource(R.drawable.x_icon)

        } else {
            ObjectAnimator.ofFloat(hmPadFab, "translationY", -400f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", -600f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", -800f).apply { start() }
            fabMain.setImageResource(R.drawable.writing_icon)
        }
        isFabOpen = !isFabOpen
    }
}