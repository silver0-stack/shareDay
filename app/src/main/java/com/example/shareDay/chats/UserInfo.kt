package com.example.shareDay.chats

import com.google.firebase.database.DataSnapshot

// 유저 정보 Data Set
// 홈 채팅 탭에서 뜨는 채팅방 목록에 띄울 유저 정보
data class UserInfo(var name: String, var profile: String, var about: String) {

    /*var name: String? = null
    var profile: Drawable? = null
    var about: String? = null*/
}