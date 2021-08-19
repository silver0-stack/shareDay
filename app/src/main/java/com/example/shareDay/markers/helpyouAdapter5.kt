package com.example.shareDay.markers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.naver.maps.map.overlay.InfoWindow


//도와주세요
class helpyouAdapter5(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext) {
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(R.layout.helpyou_info, null)
        val markUserImg = view.findViewById<ImageView>(R.id.markUserImg)
        val markUserNickname = view.findViewById<TextView>(R.id. markUserNickname)
        val sharingOrNot = view.findViewById<TextView>(R.id.sharingOrNot)
        val startChat = view.findViewById<ImageView>(R.id.startChat)
        val infoContents=view.findViewById<TextView>(R.id.infoContents)
        val postedTime=view.findViewById<TextView>(R.id.postedTime)
        val postedLocation=view.findViewById<TextView>(R.id.postedLocation)

        //채팅 이미지 누르면 1:1 채팅방으로 이동
        startChat.setOnClickListener {
            context.startActivity(Intent(context, ChatActivity::class.java))
        }
        infoContents.text = "탐폰 있어요! 채팅주세요"
        markUserImg.setImageResource(R.drawable.profile10_image)
        return view
    }
}
