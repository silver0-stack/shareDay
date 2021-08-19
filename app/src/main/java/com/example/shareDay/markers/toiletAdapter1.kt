package com.example.shareDay.markers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.naver.maps.map.overlay.InfoWindow

//도와주세요
class toiletAdapter1(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext){
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(R.layout.toilet_info, null)
        val markUserNickname = view.findViewById<TextView>(R.id. markUserNickname)
        val resolveOrNot = view.findViewById<TextView>(R.id.resolveOrNot)
        val postedTime=view.findViewById<TextView>(R.id.postedTime)
        val postedLocation=view.findViewById<TextView>(R.id.postedLocation)

        return view
    }
}