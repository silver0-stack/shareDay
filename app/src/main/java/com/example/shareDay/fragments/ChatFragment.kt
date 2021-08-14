package com.example.shareDay.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareDay.R
import com.example.shareDay.chats.UserInfo
import com.example.shareDay.chats.UserListAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

//채팅화면
//리사이클러뷰와 어댑터 연결함
class ChatFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var currentUserID: String
    private lateinit var dbref: DatabaseReference
    private lateinit var userList: RecyclerView
    private lateinit var items: ArrayList<UserInfo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
       // dbref = Firebase.database.reference //firebase
        mAuth = FirebaseAuth.getInstance()
        currentUserID = mAuth.currentUser!!.uid
        dbref = FirebaseDatabase.getInstance().reference.child("message").child(currentUserID)
        val chatView: View = inflater.inflate(R.layout.chat_fragment, container, false)
        val options = FirebaseRecyclerOptions.Builder<UserInfo>().setQuery(dbref, UserInfo::class.java)
            .setLifecycleOwner(this)
            .build()
        items = arrayListOf(UserInfo("person1","profile1","one tampon plz"))
        userList=chatView.findViewById(R.id.list)
        userList.layoutManager = LinearLayoutManager(context)
//        userList.setHasFixedSize(true)
        //xddssssssss
        getUserUpdate()

        return chatView

    }

    private fun getUserUpdate() {
        super.onStart()

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserInfo::class.java)
                        items.add(user!!)
                    }
                   userList.adapter = UserListAdapter(items)
                    userList.itemAnimator = DefaultItemAnimator()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}
