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
import com.example.shareDay.helpme.dto.total
import com.example.shareDay.helpme.activity.HelpMeLinerWriteActivity
import com.example.shareDay.helpme.activity.HelpMePadWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTamponWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTotalWriteActivity
import com.example.shareDay.helpme.adapter.HelpmeListLinerAdapter
import com.example.shareDay.helpme.adapter.HelpmeListTotalAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class HelpMeTotalFragment: Fragment() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList : ArrayList<total>
    private lateinit var perView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //helpme_total_fragment의 리사이클러뷰 쭉 있는 xml
        perView = inflater.inflate(R.layout.helpme_total_fragment, container, false)

        userRecyclerView = perView.findViewById(R.id.helpme_total_recycler)
        userRecyclerView.layoutManager = LinearLayoutManager(activity);
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getUserData()

        return perView
    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference("helpme_total")

        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(total::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = HelpmeListTotalAdapter(userArrayList)
                }
                userRecyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}