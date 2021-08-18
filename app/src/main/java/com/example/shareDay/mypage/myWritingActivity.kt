package com.example.shareDay.mypage

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.shareDay.R
import com.google.android.material.tabs.TabLayout
import kotlin.reflect.KMutableProperty1

class myWritingActivity :AppCompatActivity(){


    /*
    lateinit var mywriting_tab_helpme : myWritingHelpmeFragment
    lateinit var mywriting_tab_helpyou : myWritingHelpyouFragment
    lateinit var mywriting_tabs : TabLayout
    lateinit var mypage_mywriting_back: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.mypage_mywriting)

        mywriting_tab_helpme= myWritingHelpmeFragment()
        mywriting_tab_helpyou=myWritingHelpyouFragment()
        mywriting_tabs=findViewById(R.id.mywriting_tabs)
        supportFragmentManager.beginTransaction().add(R.id.mywriting_frame,mywriting_tab_helpme).commit()

        mypage_mywriting_back=findViewById(R.id.mypage_mywriting_back)
             mypage_mywriting_back.setOnClickListener{
                finish()
            }

        mywriting_tabs.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->{
                        replaceView(mywriting_tab_helpme)
                    }
                    1->{
                        replaceView(mywriting_tab_helpyou)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }


        })

    }
    private fun replaceView(tab: Fragment){
        var selectedFragment: Fragment?=null
        selectedFragment=tab
        selectedFragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.mywriting_frame,it).commit()
        }
    }

     */


}