package com.example.shareDay.contents

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.shareDay.R
import com.example.shareDay.mapmenu.MapActivity
import com.pedro.library.AutoPermissionsListener
import com.pedro.library.AutoPermissions
import com.pedro.library.AutoPermissions.Companion.parsePermissions

//지도 탭에서 내 위치 설정하는 코드

//위치 기반 서비스와 센서 이용
open class MyAreaSettingActivity : AppCompatActivity(), AutoPermissionsListener {
     lateinit var yourArea: EditText
    lateinit var yourDetailArea: EditText
    private lateinit var AreaSettingButton: Button
    lateinit var backBtn: ImageButton
//    lateinit var myArea: TextView

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_area)



        yourDetailArea = findViewById(R.id.yourDetailArea)
        AreaSettingButton = findViewById(R.id.AreaSettingButton)
        backBtn = findViewById(R.id.backBtn)
//        myArea = findViewById(R.id.myArea)

        AutoPermissions.Companion.loadAllPermissions(this, 101)

        if (this::yourArea.isInitialized){
            yourArea = findViewById(R.id.yourArea)
            yourArea.setOnClickListener {
                startLocationService()
            }
        }

        AreaSettingButton.setOnClickListener {
            startActivity(Intent(applicationContext, MapActivity::class.java))
        }
        //뒤로 가기 버튼 이벤트
        backBtn.setOnClickListener {
            onBackPressed()
        }

    }

    //위치 리스너 구현하기
    class GPSListener : MyAreaSettingActivity(), LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude = location.latitude
            val longitude = location.longitude
            val message = "최근 위치 -> Latitude : $latitude\nLongitude : $longitude"
            yourArea.setText(message)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    //위치 정보 업데이트 요청하기
    //최소 시간은 10초, 최소 거리는 0으로 하여 10초마다 위치 정보를 전달받게 됩니다.
    private fun startLocationService() {

        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (Build.VERSION.SDK_INT >= 23 &&
            ContextCompat.checkSelfPermission(
                applicationContext, ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val location: Location? = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val latitude = location?.latitude
        val longitude = location?.longitude
        val message = "최근 위치 -> Latitude : $latitude\nLongitude : $longitude"
        yourArea.setText(message)



        val gpsListener = GPSListener()
        val minTime: Long = 10000
        val minDistance = 0f

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            minTime,
            minDistance,
            gpsListener)
        Toast.makeText(applicationContext, "내 위치확인 요청함", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        parsePermissions(this, requestCode, permissions, this)
    }

    override fun onDenied(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(applicationContext,
            "permissions denied : $permissions",
            Toast.LENGTH_SHORT).show()
    }

    override fun onGranted(requestCode: Int, permissions: Array<String>) {
        Toast.makeText(applicationContext,
            "permissions granted : $permissions",
            Toast.LENGTH_SHORT).show()
    }

}
