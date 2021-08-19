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
import com.example.shareDay.helpme.dto.liner2
import com.example.shareDay.helpyou.activity.HelpYouLinerWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouPadWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTamponWriteActivity
import com.example.shareDay.helpyou.activity.HelpYouTotalWriteActivity
import com.example.shareDay.helpyou.adapter.HelpYouLinerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpYouLinerFragment: Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<liner2>
    private lateinit var perView : View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //helpme_liner_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpyou_liner_fragment, container, false)
        userRecyclerView = perView.findViewById(R.id.helpyou_liner_recycler)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()

        return perView
    }



    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("helpyou_liner")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(liner2::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = HelpYouLinerAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}