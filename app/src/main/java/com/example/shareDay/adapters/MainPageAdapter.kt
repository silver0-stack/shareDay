package com.example.shareDay.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.shareDay.fragments.*


//메인페이지에서 하단네비게이션바를 눌렀을 때, 뷰페이저에 나타나는 fragment를 연결하기 위한 Adapter

class MainPageAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment{
     return when (position) {
            0 -> MapFragment()
            1 -> MapFragment()
            2 -> MapFragment()
            3 -> MapFragment()
            else -> MapFragment()
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}
