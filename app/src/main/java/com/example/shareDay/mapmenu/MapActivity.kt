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
import android.widget.ToggleButton
import androidx.core.app.ActivityCompat
import com.example.shareDay.ChatActivity
import com.example.shareDay.R
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource

//지도 탭에 보이는 마커들 표시하는 코드

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var myMap: NaverMap? = null
    lateinit var helpMeIcon: ToggleButton
    private lateinit var helpYouIcon: ToggleButton
    lateinit var findMyLocation: ImageView
    lateinit var toilet: ToggleButton
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
    private val marker201 = Marker()
    private val marker202 = Marker()
    private val marker203 = Marker()
    private val marker204 = Marker()
    private val marker205 = Marker()

    //도와줄게요 마커 변수 선언 및 초기화
    private val marker301 = Marker()
    private val marker302 = Marker()
    private val marker303 = Marker()
    private val marker304 = Marker()
    private val marker305 = Marker()

    //화장실 마커 변수 선언 및 초기화
    private val marker101 = Marker()
    private val marker102 = Marker()
    private val marker103 = Marker()
    private val marker104 = Marker()
    private val marker105 = Marker()
    private val marker106 = Marker()
    private val marker107 = Marker()
    private val marker108 = Marker()
    private val marker109 = Marker()
    private val marker110 = Marker()
    private val marker111 = Marker()
    private val marker112 = Marker()
    private val marker113 = Marker()
    private val marker114 = Marker()
    private val marker115 = Marker()
    private val marker116 = Marker()
    private val marker117 = Marker()
    private val marker118 = Marker()
    private val marker119 = Marker()
    private val marker120 = Marker()



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
            if(toilet.isChecked==true){

                //화장실 마커 세팅
                setMarker(marker101, //서울특별시 서초구 방배천로 5-4 안심화장실145
                    37.477291446255194, 126.98217320405335 ,
                    R.drawable.map_restroom_loc_marker,
                    0)
                setMarker(marker102, //서울특별시 서초구 방배천로 17 안심화장실146
                    37.47806393170941, 126.98229438527598 ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker103, //서울특별시 서초구 청두곶길 1 안심화장실177
                    37.477618097520924, 126.98501594662648 ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker104,  //서울특별시 서초구 신반포로 15 안심화장실150
                    37.502538268739535, 126.98616338986513  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker105, //서울특별시 서초구 방배로23길 8 안심화장실 93
                    37.48756457136718, 126.99374220003516 ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker106, //서울특별시 서초구 효령로31길 6 안심화장실141
                    37.48259083303174, 126.99707103071415 ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker107, //서울특별시 서초구 서초중앙로 42 안심화장실92
                    37.486477751073075, 127.01609556935394  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker108, //서울특별시 서초구 반포대로 22 안심화장실70
                    37.48910732742018, 127.01004512852757  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker109, //서울특별시 서초구 서초대로52길 25 안심화장실34
                    37.49271737851727, 127.01476635401619  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker110, // 서울특별시 서초구 서초중앙로24길 27 안심화장실78
                    37.495413776978786, 127.0164456846983  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker111, //서울특별시 서초구 나루터로 64 안심화장실152
                    37.51493615633114, 127.01699052334422 ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker112, // 서울특별시 서초구 강남대로 565 안심화장실153
                    37.51231654270945, 127.02003467732165   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker113, //서울특별시 서초구 서운로 226 안심화장실43
                    37.50298818564983, 127.02167120004185   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker114, //서울특별시 서초구 서초대로77길 3 안심화장실22
                    37.49793353312094, 127.02628239265644   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker115, //서울특별시 서초구 강남대로 220 안심화장실18
                    37.48369068597798, 127.03545138469354   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker116, //서울특별시 서초구 사임당로 178 안심화장실61
                    37.492330579223776, 127.02891372333504   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker117, //서울특별시 서초구 서운로11길 35 안심화장실57
                    37.4872429185319, 127.02668878469503    ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker118, //서울특별시 서초구 강남대로 243 안심화장실38
                    37.484434531147066, 127.0331915386727   ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker119, //서울특별시 서초구 남부순환로356길 32 안심화장실170
                    37.48346673159111, 127.03779800799103  ,
                    R.drawable.map_restroom_loc_marker,
                    10)
                setMarker(marker120, //
                    37.48369068597798, 127.03545138469354   ,
                    R.drawable.map_restroom_loc_marker,
                    10)

                marker101.isVisible=true
                marker102.isVisible=true
                marker103.isVisible=true
                marker104.isVisible=true
                marker105.isVisible=true
                marker106.isVisible=true
                marker107.isVisible=true
                marker108.isVisible=true
                marker109.isVisible=true
                marker110.isVisible=true
                marker111.isVisible=true
                marker112.isVisible=true
                marker113.isVisible=true
                marker114.isVisible=true
                marker115.isVisible=true
                marker116.isVisible=true
                marker117.isVisible=true
                marker118.isVisible=true
                marker119.isVisible=true
                marker120.isVisible=true

            }
            else{
                marker101.isVisible=false
                marker102.isVisible=false
                marker103.isVisible=false
                marker104.isVisible=false
                marker105.isVisible=false
                marker106.isVisible=false
                marker107.isVisible=false
                marker108.isVisible=false
                marker109.isVisible=false
                marker110.isVisible=false
                marker111.isVisible=false
                marker112.isVisible=false
                marker113.isVisible=false
                marker114.isVisible=false
                marker115.isVisible=false
                marker116.isVisible=false
                marker117.isVisible=false
                marker118.isVisible=false
                marker119.isVisible=false
                marker120.isVisible=false
            }


        }
        //도와주세요 버튼 클릭 시 마커 등장
        helpMeIcon.setOnClickListener {
            if (helpMeIcon.isChecked==true){
                setMarker(marker201,
                    37.62120135705168, 127.08070716149409, //길가
                    R.drawable.helpme_loc_marker,
                    0)
                setMarker(marker202,
                    37.62562264167059, 127.08724171156216, //비선아파트
                    R.drawable.helpme_loc_marker,
                    10)
                setMarker(marker203,
                    37.62817890422706, 127.0904957588285, //서울여자대학교
                    R.drawable.helpme_loc_marker,
                    10)
                setMarker(marker204,
                    37.6250568995319, 127.08097171811086, //공릉신선미소아파트
                    R.drawable.helpme_loc_marker,
                    10)
                setMarker(marker205,
                    37.621243266207216, 127.0886438619974, //신내효성아파트
                    R.drawable.helpme_loc_marker,
                    10)
                marker201.isVisible=true
                marker202.isVisible=true
                marker203.isVisible=true
                marker204.isVisible=true
                marker205.isVisible=true

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

                    if (marker201.infoWindow == null) {
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
                    if (marker202.infoWindow == null) {
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
                    if (marker203.infoWindow == null) {
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
                    if (marker204.infoWindow == null) {
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
                    if (marker205.infoWindow == null) {
                        infoWindow.open(marker)
                    } else {
                        infoWindow.close()
                    }
                    true

                }
                marker201.onClickListener = listener
                marker202.onClickListener = listener2
                marker203.onClickListener = listener3
                marker204.onClickListener = listener4
                marker205.onClickListener = listener5

            }
            else{
                marker201.isVisible=false
                marker202.isVisible=false
                marker203.isVisible=false
                marker204.isVisible=false
                marker205.isVisible=false
            }

        }







        //도와줄게요 버튼 클릭 시 마커 등장
        helpYouIcon.setOnClickListener {
            if (helpMeIcon.isChecked == true) {
                setMarker(
                    marker301,
                    37.61916575337751, 127.08821252361916,
                    R.drawable.helpyou_loc_marker,
                    0
                )
                setMarker(
                    marker302,
                    37.625118610035564, 127.08254850088396,
                    R.drawable.helpyou_loc_marker,
                    10
                )
                setMarker(
                    marker303,
                    37.62002851577837, 127.08292973330127,
                    R.drawable.helpyou_loc_marker,
                    10
                )
                setMarker(
                    marker304,
                    37.62598130335246, 127.08325650384369,
                    R.drawable.helpyou_loc_marker,
                    10
                )
                setMarker(
                    marker305,
                    37.61903633817256, 127.07530508731159,
                    R.drawable.helpyou_loc_marker,
                    10
                )
                marker301.isVisible=true
                marker302.isVisible=true
                marker303.isVisible=true
                marker304.isVisible=true
                marker305.isVisible=true



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

                    if (marker301.infoWindow == null) {
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

                    if (marker302.infoWindow == null) {
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

                    if (marker303.infoWindow == null) {
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

                    if (marker304.infoWindow == null) {
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

                    if (marker305.infoWindow == null) {
                        infoWindow.open(marker)
                    } else {
                        infoWindow.close()
                    }
                    true
                }
                marker301.onClickListener = listener6
                marker302.onClickListener = listener7
                marker303.onClickListener = listener8
                marker304.onClickListener = listener9
                marker305.onClickListener = listener10
            }
            else {

                marker301.isVisible=false
                marker302.isVisible=false
                marker303.isVisible=false
                marker304.isVisible=false
                marker305.isVisible=false

            }
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
            LatLng(37.4935288523304, 127.02440601960771),  // 위치 지정
            14.0,  // 줌 레벨
            0.0,  // 기울임 각도
            0.0// 방향
        )
        myMap.cameraPosition = cameraPosition

        //지도상에 마커 표시
        val myloc_marker = Marker()
        //도와주세요 버튼 클릭 시 마커 등장
        findMyLocation.setOnClickListener {
            setMarker(myloc_marker,
                37.4935288523304, 127.02440601960771,
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
