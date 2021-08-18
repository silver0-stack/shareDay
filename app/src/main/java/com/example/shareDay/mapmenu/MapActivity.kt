package com.example.shareDay.mapmenu

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shareDay.markers.*
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.overlay.OverlayImage
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource

//지도 탭에 보이는 마커들 표시하는 코드

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var myMap: NaverMap? = null
    lateinit var helpMeIcon: ImageView
    private lateinit var helpYouIcon: ImageView
    lateinit var findMyLocation: ImageView
    lateinit var toilet: ImageView
    lateinit var myArea: TextView
    private val PERMISSIONS = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private lateinit var fusedLocationProviderClient: FusedLocationSource

    companion object {
        private const val permissionCode = 1000
    }

    // 도와주세요 마커 변수 선언 및 초기화
    private val marker1 = Marker()
    private val marker2 = Marker()
    private val marker3 = Marker()
    private val marker4 = Marker()
    private val marker5 = Marker()

    //도와줄게요 마커 변수 선언 및 초기화
    private val marker6 = Marker()
    private val marker7 = Marker()
    private val marker8 = Marker()
    private val marker9 = Marker()
    private val marker10 = Marker()

    //도와줄게요 마커 변수 선언 및 초기화
    private val marker11 = Marker()
    private val marker12 = Marker()
    private val marker13 = Marker()
    private val marker14 = Marker()
    private val marker15 = Marker()


    private var mInfoWindow: InfoWindow? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        helpMeIcon = findViewById(R.id.helpMeIcon) //도와주세요 버튼
        helpYouIcon = findViewById(R.id.helpYouIcon) //도와줄게요 버튼
        findMyLocation = findViewById(R.id.findLocation)//내 위치 설정 이미지뷰
        myArea = findViewById(R.id.myArea)
        toilet = findViewById(R.id.toilet)
//        myAreaSetting.setOnClickListener {
//            val intent = Intent(this, MyAreaSettingActivity::class.java)
//            startActivity(intent)
//        }

        //화장실 버튼 클릭 시 마커 등장
        toilet.setOnClickListener {
            setMarker(marker11,
                37.626067572147385, 127.08145926573985,
                R.drawable.map_restroom_loc_marker,
                0)
            setMarker(marker12,
                37.62317751353327, 127.07584970476171,
                R.drawable.map_restroom_loc_marker,
                10)
            setMarker(marker13,
                37.619295168405294, 127.07797371328742,
                R.drawable.map_restroom_loc_marker,
                10)
            setMarker(marker14,
                37.62076185594893, 127.09060884092744,
                R.drawable.map_restroom_loc_marker,
                10)
            setMarker(marker15,
                37.621969694552966, 127.08559835927709,
                R.drawable.map_restroom_loc_marker,
                10)
        }
        //도와주세요 버튼 클릭 시 마커 등장
        helpMeIcon.setOnClickListener {
            setMarker(marker1,
                37.62120135705168, 127.08070716149409, //길가
                R.drawable.helpme_loc_marker,
                0)
            setMarker(marker2,
                37.62562264167059, 127.08724171156216, //비선아파트
                R.drawable.helpme_loc_marker,
                10)
            setMarker(marker3,
                37.62817890422706, 127.0904957588285, //서울여자대학교
                R.drawable.helpme_loc_marker,
                10)
            setMarker(marker4,
                37.6250568995319, 127.08097171811086, //공릉신선미소아파트
                R.drawable.helpme_loc_marker,
                10)
            setMarker(marker5,
                37.621243266207216, 127.0886438619974, //신내효성아파트
                R.drawable.helpme_loc_marker,
                10)
        }


        //정보창
        val infoWindow = InfoWindow()



        //마커 클릭하면 정보창 생성됨
        //도와주세요 첫번째 마커
        val listener = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker

            val rootView = findViewById<View>(R.id.map_main) as ViewGroup
            val adapter = helpmeAdapter1(this, rootView)

            infoWindow.adapter = adapter

            //인포창의 우선순위
            infoWindow.zIndex = 10
            //투명도 조정
            infoWindow.alpha = 0.9f

            if (marker1.infoWindow == null) {
                // 마커1 정보 창이 열려있지 않을 경우 엶
                infoWindow.open(marker)
            } else {
                // 마커1 정보 창이 열려있을 경우 닫음
                infoWindow.close()
            }

            true
        }

        //도와주세요 두번째 마커
        val listener2 = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker

            val rootView = findViewById<View>(R.id.map_main) as ViewGroup
            val adapter = helpmeAdapter2(this, rootView)

            infoWindow.adapter = adapter
            infoWindow.zIndex = 10
            infoWindow.alpha = 0.9f
            if (marker2.infoWindow == null) {
                infoWindow.open(marker)
            } else {
                infoWindow.close()
            }
            true
        }

        //도와주세요 세번째 마커
        val listener3 = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker

            val rootView = findViewById<View>(R.id.map_main) as ViewGroup
            val adapter = helpmeAdapter3(this, rootView)

            infoWindow.adapter = adapter
            infoWindow.zIndex = 10
            infoWindow.alpha = 0.9f
            if (marker3.infoWindow == null) {
                infoWindow.open(marker)
            } else {
                infoWindow.close()
            }
            true

        }
        //도와주세요 네번째 마커
        val listener4 = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker

            val rootView = findViewById<View>(R.id.map_main) as ViewGroup
            val adapter = helpmeAdapter3(this, rootView)

            infoWindow.adapter = adapter
            infoWindow.zIndex = 10
            infoWindow.alpha = 0.9f
            if (marker4.infoWindow == null) {
                infoWindow.open(marker)
            } else {
                infoWindow.close()
            }
            true

        }
        //도와주세요 다섯번째 마커
        val listener5 = Overlay.OnClickListener { overlay ->
            val marker = overlay as Marker

            val rootView = findViewById<View>(R.id.map_main) as ViewGroup
            val adapter = helpmeAdapter3(this, rootView)

            infoWindow.adapter = adapter
            infoWindow.zIndex = 10
            infoWindow.alpha = 0.9f
            if (marker5.infoWindow == null) {
                infoWindow.open(marker)
            } else {
                infoWindow.close()
            }
            true

        }

        marker1.onClickListener = listener
        marker2.onClickListener = listener2
        marker3.onClickListener = listener3
        marker4.onClickListener = listener4
        marker5.onClickListener = listener5


        //도와줄게요 버튼 클릭 시 마커 등장
        helpYouIcon.setOnClickListener {
            setMarker(marker6,
                37.61916575337751, 127.08821252361916,
                R.drawable.helpyou_loc_marker,
                0)
            setMarker(marker7,
                37.625118610035564, 127.08254850088396,
                R.drawable.helpyou_loc_marker,
                10)
            setMarker(marker8,
                37.62002851577837, 127.08292973330127,
                R.drawable.helpyou_loc_marker,
                10)
            setMarker(marker9,
                37.62598130335246, 127.08325650384369,
                R.drawable.helpyou_loc_marker,
                10)
            setMarker(marker10,
                37.61903633817256, 127.07530508731159,
                R.drawable.helpyou_loc_marker,
                10)
            //정보창
            val infoWindow = InfoWindow()

            //마커 클릭하면 정보창 생성됨
            //도와줄게요 첫번째 마커
            val listener6 = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker
                val rootView = findViewById<View>(R.id.map_main) as ViewGroup
                val adapter = helpyouAdapter1(this, rootView)

                infoWindow.adapter = adapter
                infoWindow.zIndex = 10
                infoWindow.alpha = 0.9f

                if (marker6.infoWindow == null) {
                    infoWindow.open(marker)
                } else {
                    infoWindow.close()
                }
                true
            }


            //도와줄게요 두번째 마커
            val listener7 = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker
                val rootView = findViewById<View>(R.id.map_main) as ViewGroup
                val adapter = helpyouAdapter2(this, rootView)

                infoWindow.adapter = adapter
                infoWindow.zIndex = 10
                infoWindow.alpha = 0.9f

                if (marker7.infoWindow == null) {
                    infoWindow.open(marker)
                } else {
                    infoWindow.close()
                }
                true
            }
            //도와줄게요 세번째 마커
            val listener8 = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker
                val rootView = findViewById<View>(R.id.map_main) as ViewGroup
                val adapter = helpyouAdapter3(this, rootView)

                infoWindow.adapter = adapter
                infoWindow.zIndex = 10
                infoWindow.alpha = 0.9f

                if (marker8.infoWindow == null) {
                    infoWindow.open(marker)
                } else {
                    infoWindow.close()
                }
                true
            }
            //도와줄게요 4번째 마커
            val listener9 = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker
                val rootView = findViewById<View>(R.id.map_main) as ViewGroup
                val adapter = helpyouAdapter3(this, rootView)

                infoWindow.adapter = adapter
                infoWindow.zIndex = 10
                infoWindow.alpha = 0.9f

                if (marker9.infoWindow == null) {
                    infoWindow.open(marker)
                } else {
                    infoWindow.close()
                }
                true
            }
            //도와줄게요 5번째 마커
            val listener10 = Overlay.OnClickListener { overlay ->
                val marker = overlay as Marker
                val rootView = findViewById<View>(R.id.map_main) as ViewGroup
                val adapter = helpyouAdapter3(this, rootView)

                infoWindow.adapter = adapter
                infoWindow.zIndex = 10
                infoWindow.alpha = 0.9f

                if (marker10.infoWindow == null) {
                    infoWindow.open(marker)
                } else {
                    infoWindow.close()
                }
                true
            }
            marker6.onClickListener = listener6
            marker7.onClickListener = listener7
            marker8.onClickListener = listener8
            marker9.onClickListener = listener9
            marker10.onClickListener = listener10

        }
        //네이버 지도
        // 지도 객체 생성
        // 지도 객체 생성
        mapView = findViewById(R.id.map_main)
        if (mapView == null) {
            mapView!!.onCreate(savedInstanceState)
        }
        //getMapAsync를 호출하여 비동기로 onMapReady 콜백 메소드 호출
        //onMapReady에서 NaverMap 객체를 받음
        mapView!!.getMapAsync(this)
        fusedLocationProviderClient = FusedLocationSource(this, permissionCode)
    }


    //마커 만드는 함수
    private fun setMarker(marker: Marker, lat: Double, lng: Double, resourceID: Int, zIndex: Int) {
        //원근감 표시
        marker.isIconPerspectiveEnabled = true
        //아이콘 지정
        marker.icon = OverlayImage.fromResource(resourceID)
        //마커의 투명도
        marker.alpha = 0.8f
        //마커 위치
        marker.position = LatLng(lat, lng)
        //마커 우선순위
        marker.zIndex = zIndex
        //마커 표시
        marker.map = myMap
    }

    //지도 설정
    override fun onMapReady(myMap: NaverMap) {

        this.myMap = myMap
        mInfoWindow = InfoWindow()
        //배경 지도 선택
        myMap.mapType = NaverMap.MapType.Navi

        //건물 표시
        myMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true)
        //위치 및 각도 조정
        val cameraPosition = CameraPosition(
            LatLng(37.6204, 127.0837),  // 위치 지정
            15.0,  // 줌 레벨
            0.0,  // 기울임 각도
            0.0// 방향
        )
        myMap.cameraPosition = cameraPosition

        //지도상에 마커 표시
        val myloc_marker = Marker()
        //도와주세요 버튼 클릭 시 마커 등장
        findMyLocation.setOnClickListener {
            setMarker(myloc_marker,
                37.62055004552846,
                127.08452235412291,
                R.drawable.mylocation_marker,
                0)
            myloc_marker.map = myMap
            //위치 소스 지정
            myMap.locationSource = fusedLocationProviderClient
            //권한 확인 결과는 onRequestPermissionsResult 콜백 메소드 호출
            ActivityCompat.requestPermissions(this, PERMISSIONS, permissionCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray,
    ) {
        if (fusedLocationProviderClient.onRequestPermissionsResult(requestCode, permissions,
                grantResults)
        ) {
            if (!fusedLocationProviderClient.isActivated) { // 권한 거부됨
                myMap?.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView!!.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView!!.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }
}
