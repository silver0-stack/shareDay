package com.example.shareDay.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.shareDay.R
import com.example.shareDay.adapters.HelpYouAdapter
import com.example.shareDay.board.HelpMeWriteActivity
import com.example.shareDay.board.HelpYouWriteActivity
import com.example.shareDay.helpme.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.helpyou_fragment.*

class HelpYouFragment : Fragment() {

    lateinit var myFragment: View
    lateinit var viewHomePager: ViewPager //게시물이 배치되는 화면
    lateinit var topTabLayout: TabLayout //상단메뉴탭
    lateinit var helpYouWriteBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.helpyou_fragment, container, false)
        viewHomePager = myFragment.findViewById(R.id.hy_view_home)
        topTabLayout = myFragment.findViewById(R.id.hy_tab_layout)
        helpYouWriteBtn=myFragment.findViewById(R.id.helpYouWriteBtn)

        helpYouWriteBtn.setOnClickListener{
            val intent= Intent(context, HelpYouWriteActivity::class.java)
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

        val adapter = HelpYouAdapter(childFragmentManager)
        adapter.addFragment(HelpYouTotalFragment(), "전체")
        adapter.addFragment(HelpYouPadFragment(), "생리대")
        adapter.addFragment(HelpYouTamponFragment(), "탐폰")
        adapter.addFragment(HelpYouLinerFragment(), "팬티라이너")

        viewHomePager.adapter = adapter
        topTabLayout.setupWithViewPager(viewHomePager)
    }

    companion object {

    }
}
