package com.example.shareDay.chats

// 채팅 Data Set
// 1:1 채팅방에서 메세지를 보내면 이 형태로 데이터베이스에 저장된다

class Chat {
    //Data Transfer Object
    var name: String? = null
    var msg: String? = null
}
