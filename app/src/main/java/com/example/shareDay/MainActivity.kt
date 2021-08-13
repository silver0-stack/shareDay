package com.example.shareDay

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.example.shareDay.adapters.MainPageAdapter
import com.example.shareDay.chats.UserInfo
import com.example.shareDay.mapmenu.MapActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList


class MainActivity : AppCompatActivity()  {

    lateinit var bottomNav: BottomNavigationView //하단메뉴바
    private lateinit var viewContainer: ViewPager //하단메뉴바로 바뀌는 화면

    lateinit var mapBtn : ImageView //지도 버튼

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1) //사진 접근 권한

        mapBtn = findViewById(R.id.mapBtn)

        bottomNav = findViewById(R.id.bottom_mainNav)
        viewContainer = findViewById(R.id.view_container)

        viewContainer.adapter = MainPageAdapter(supportFragmentManager,5)
        viewContainer.offscreenPageLimit = 5 //뷰 계층 구조에 보관된 페이지, view/fragment 수 제어

        viewContainer.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                // 네비게이션 메뉴 아이템 체크상태
                bottomNav.menu.getItem(position).isChecked = true
            }
        })

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                // itemId에 따라 viewPager 바뀜
                R.id.menu_chat -> viewContainer.currentItem = 0
                R.id.menu_helpMe -> viewContainer.currentItem = 1
                R.id.menu_map -> viewContainer.currentItem = 2
                R.id.menu_helpYou -> viewContainer.currentItem = 3
                R.id.menu_myPage -> viewContainer.currentItem = 4
            }
            true
        }
        mapBtn.setOnClickListener {
            val intent = Intent(this, MapActivity ::class.java)
            startActivity(intent)
        }
    }
}
