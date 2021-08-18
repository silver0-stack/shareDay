package com.example.shareDay.helpme.dto

//프사,이름,위치,내용,타입
//이미지 이름으로 서버에서 받아오기
data class tampon(
    var userImg: String,
    var userName: String,
    var userLocation: String,
    var contents: String,
    var uid: String //var selectedType: CharSequence
) {
    constructor() : this("", "", "", "", "") //empty constructor

}