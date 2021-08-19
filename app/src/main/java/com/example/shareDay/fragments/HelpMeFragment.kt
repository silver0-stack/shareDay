package com.example.shareDay.fragments

import android.animation.ObjectAnimator
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
import com.example.shareDay.helpme.activity.HelpMeLinerWriteActivity
import com.example.shareDay.helpme.activity.HelpMePadWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTamponWriteActivity
import com.example.shareDay.helpme.activity.HelpMeTotalWriteActivity
import com.example.shareDay.helpme.fragment.HelpMeLinerFragment
import com.example.shareDay.helpme.fragment.HelpMePadFragment
import com.example.shareDay.helpme.fragment.HelpMeTamponFragment
import com.example.shareDay.helpme.fragment.HelpMeTotalFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class HelpMeFragment : Fragment(R.layout.helpme_fragment) {

    lateinit var myFragment: View
    lateinit var viewHomePager: ViewPager //게시물이 배치되는 화면
    lateinit var topTabLayout: TabLayout //상단메뉴탭


    //플로팅버튼 애니메이션을 위한 변수
    lateinit var hmTotalFab: FloatingActionButton
    lateinit var hmPadFab: FloatingActionButton
    lateinit var hmTamponFab: FloatingActionButton
    lateinit var hmLinerFab: FloatingActionButton
    lateinit var fabMain: FloatingActionButton
    private var isFabOpen = false

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

        hmLinerFab = myFragment.findViewById(R.id.helpme_liner_fab) //liner
        hmTotalFab =myFragment.findViewById(R.id.helpme_total_fab) //total
        hmTamponFab = myFragment.findViewById(R.id.helpme_tampon_fab) //tampon
        hmPadFab =myFragment.findViewById(R.id.helpme_pad_fab) //pad
        fabMain = myFragment.findViewById(R.id.tog_btn) //main fab
        return myFragment
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        //플로팅 버튼 클릭 시 애니메이션 동작 기능
        fabMain.setOnClickListener {
            toggleFab()
        }
        //total 버튼 클릭 시 개인나눔 글쓰기 화면으로 전환
        hmTotalFab.setOnClickListener {
            val intent = Intent(activity, HelpMeTotalWriteActivity::class.java)
            startActivity(intent)
        }
        //pad 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmPadFab.setOnClickListener {
            val intent = Intent(activity, HelpMePadWriteActivity::class.java)
            startActivity(intent)
        }
        //liner 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmLinerFab.setOnClickListener {
            val intent = Intent(activity, HelpMeLinerWriteActivity::class.java)
            startActivity(intent)
        }
        //tampon 버튼 클릭 시 공구 글쓰기 화면으로 전환
        hmTamponFab.setOnClickListener {
            val intent = Intent(activity, HelpMeTamponWriteActivity::class.java)
            startActivity(intent)
        }
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
    private fun toggleFab() {
        //플로팅 액션 버튼 닫기/열기
        if (isFabOpen) {
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", 0f).apply { start() }
            fabMain.setImageResource(R.drawable.x_icon)

        } else {
            ObjectAnimator.ofFloat(hmTotalFab, "translationY", 200f).apply { start() }
            ObjectAnimator.ofFloat(hmPadFab, "translationY", 400f).apply { start() }
            ObjectAnimator.ofFloat(hmTamponFab, "translationY", 600f).apply { start() }
            ObjectAnimator.ofFloat(hmLinerFab, "translationY", 800f).apply { start() }
            fabMain.setImageResource(R.drawable.writing_icon)
        }
        isFabOpen = !isFabOpen
    }


    companion object {

    }
}
