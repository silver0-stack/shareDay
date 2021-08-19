package com.example.shareDay.helpme.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.example.shareDay.UserListAdapter

import com.example.shareDay.helpme.dto.liner

import com.example.shareDay.mapmenu.MapActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.helpyou_write_list.view.*
import java.lang.NullPointerException

class HelpmeListLinerAdapter(@NonNull private val userList: ArrayList<liner>) :
    RecyclerView.Adapter<HelpmeListLinerAdapter.MyViewHolder>() {

    lateinit var chatIcon: ImageButton

    val userUID = Firebase.auth.currentUser?.uid
    var userNick :String = "익명"
    lateinit var db: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.hm_liner_list,
            parent,
            false
        )

        chatIcon = itemView.findViewById(R.id.HmLinerStartChat)

        //닉네임 받아오기
        db = FirebaseDatabase.getInstance().reference
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userNick = snapshot.child("Users").child(userUID.toString()).child("userNickname").value.toString()
                Log.e("nick", userNick)
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        return MyViewHolder(itemView).apply {
            //채팅아이콘 클릭 이벤트
            chatIcon.setOnClickListener {
                val intent = Intent(parent.context, ChatActivity::class.java)
                intent.putExtra("chatName", "익명") //여기 바꿔주세요!!
                intent.putExtra("userName", userNick)
                ContextCompat.startActivity(parent.context, intent, null)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        val imageName: String = currentItem.userImg //저장된 이미지 이름 받아오기
        //val imgUrl: String = "https://firebasestorage.googleapis.com/" +
       //         "v0/b/nami-market.appspot.com/o/images%2F" + imageName +
        //        "?alt=media&token=8770eebd-9052-4fe7-9e1a-a70273921fbf" //이미지 url
        try {
        //    Glide.with(holder.itemView).load(imgUrl).into(holder.userImg) //이미지 배치할 곳에 url 로드
            holder.userName.text = currentItem.userName
            holder.userLocation.text = currentItem.userLocation
            holder.contents.text = currentItem.contents
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImg = itemView.findViewById<ImageView>(R.id.HmLinerImg)
        val userName = itemView.findViewById<TextView>(R.id.HmLinerName)
        val userLocation = itemView.findViewById<TextView>(R.id.HmLinerLoc)
        val contents = itemView.findViewById<TextView>(R.id.HmLinerContents)
    }

}
