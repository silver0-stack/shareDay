package com.example.shareDay.chats

import java.time.LocalDate
import java.time.LocalDateTime

// 채팅 Data Set
// 1:1 채팅방에서 메세지를 보내면 이 형태로 데이터베이스에 저장된다

class Chat {
    //Data Transfer Object
    var name: String? = null
    var msg: String? = null
    //var cTime = null

    constructor()

    constructor(name: String?, msg: String?) {
        this.name = name
        this.msg = msg
    }

    //constructor(name: String?, msg: String?, cTime:String){}
}
