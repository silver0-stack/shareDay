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

//도와줄게요
class MarkerAdapter6(private val mContext: Context, private val mParent: ViewGroup) :
    InfoWindow.DefaultViewAdapter(mContext) {
    @SuppressLint("SetTextI18n", "InflateParams")
    override fun getContentView(infoWindow: InfoWindow): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_point, null)
        val title = view.findViewById<TextView>(R.id.markTitle)
        val img = view.findViewById<ImageView>(R.id.markContentImg)
        val name = view.findViewById<TextView>(R.id.markUserId)
        val content = view.findViewById<TextView>(R.id.markContent)

        title.text = "도와줄게요"
        name.text = "쉐언니"
        content.text = "탐폰 선착순 1명"
        img.setImageResource(R.drawable.tampon1)
        return view
    }
}