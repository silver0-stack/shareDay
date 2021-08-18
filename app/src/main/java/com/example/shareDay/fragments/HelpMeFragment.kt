package com.example.shareDay.fragments

import com.example.shareDay.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.shareDay.adapters.HelpMeAdapter
import com.example.shareDay.helpme.activity.HelpMeTotalWriteActivity
import com.example.shareDay.helpme.fragment.HelpMeLinerFragment
import com.example.shareDay.helpme.fragment.HelpMePadFragment
import com.example.shareDay.helpme.fragment.HelpMeTamponFragment
import com.example.shareDay.helpme.fragment.HelpMeTotalFragment
import com.google.android.material.tabs.TabLayout

class HelpMeFragment : Fragment(R.layout.helpme_fragment) {

    lateinit var myFragment: View
    lateinit var viewHomePager: ViewPager //게시물이 배치되는 화면
    lateinit var topTabLayout: TabLayout //상단메뉴탭
    lateinit var helpMeWriteBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.helpme_fragment, container, false)
        viewHomePager = myFragment.findViewById(R.id.hm_view_home)
        topTabLayout = myFragment.findViewById(R.id.hm_tab_layout)
        helpMeWriteBtn = myFragment.findViewById(R.id.helpMeWriteBtn)

        helpMeWriteBtn.setOnClickListener {
            val intent = Intent(context, HelpMeTotalWriteActivity::class.java)
            startActivity(intent)
        }
        return myFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()
        topTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpViewPager() {

        val adapter = HelpMeAdapter(childFragmentManager)
        adapter.addFragment(HelpMeTotalFragment(), "전체")
        adapter.addFragment(HelpMePadFragment(), "생리대")
        adapter.addFragment(HelpMeTamponFragment(), "탐폰")
        adapter.addFragment(HelpMeLinerFragment(), "팬티라이너")

        viewHomePager.adapter = adapter
        topTabLayout.setupWithViewPager(viewHomePager)
    }

    companion object {

    }
}
