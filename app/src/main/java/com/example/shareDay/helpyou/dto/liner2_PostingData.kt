package com.example.shareDay.helpme.dto

import com.google.firebase.database.IgnoreExtraProperties

//도와주세요 글 등록하기 위한 데이터 구조
@IgnoreExtraProperties
data class liner2_PostingData(
    val userLocation: String?,
    val contents: String?,
    val userName: String?,
    val uid: String? = null
) {
    constructor() : this("", "", "", "") //empty constructor
}