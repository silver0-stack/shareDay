package com.example.shareDay.markers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shareDay.R
import com.example.shareDay.UserListAdapter
import com.example.shareDay.mapmenu.MapActivity
import com.naver.maps.map.overlay.InfoWindow

//도와주세요
class helpmeAdapter1(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext){
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(R.layout.helpme_info, null)
        val markUserImg = view.findViewById<ImageView>(R.id.markUserImg)
        val markUserNickname = view.findViewById<TextView>(R.id. markUserNickname)
        val resolveOrNot = view.findViewById<TextView>(R.id.resolveOrNot)
        val startChat = view.findViewById<ImageView>(R.id.startChat)
        val infoContents=view.findViewById<TextView>(R.id.infoContents)
        val postedTime=view.findViewById<TextView>(R.id.postedTime)
        val postedLocation=view.findViewById<TextView>(R.id.postedLocation)


        //채팅 이미지 누르면 1:1 채팅방으로 이동
        startChat.setOnClickListener {
            fun onClick(v:View){
                //context.startActivity(Intent(context,ChatActivity::class.java))

                Toast.makeText(context, "버튼 눌림", Toast.LENGTH_SHORT).show()
                Log.d("tag", "Button Click")
            }
        }

        infoContents.text = "test : 탐폰 하나 필요합니다!"
        Log.d("tag", "Ready")
        markUserImg.setImageResource(R.drawable.profile1_image)
        return view
    }


}