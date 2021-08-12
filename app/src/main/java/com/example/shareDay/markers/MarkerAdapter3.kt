package com.example.shareDay.markers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shareDay.R
import com.naver.maps.map.overlay.InfoWindow

//도와주세요
class MarkerAdapter3(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext) {
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_point, null)
        val title = view.findViewById<TextView>(R.id.markTitle)
        val img = view.findViewById<ImageView>(R.id.markContentImg)
        val name = view.findViewById<TextView>(R.id.markUserId)
        val content = view.findViewById<TextView>(R.id.markContent)

        title.text = "도와주세요"
        name.text = "쉐언니"
        content.text = "탐폰 가지고 계신 분 계신가요?"
        img.setImageResource(R.drawable.ic_baseline_mood_bad_24)
        return view
    }
}