![쉐어데이_표지](https://user-images.githubusercontent.com/76518850/132691070-f56bf087-275a-459b-bbc2-20603ca5e312.png)

<h1 align="center">ShareDay 🌸</h1>
<p align="center"><b>여성을 위한 위생용품 나눔 매칭 Android 앱</b></p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?logo=android">
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin">
  <img src="https://img.shields.io/badge/Database-Firebase-orange?logo=firebase">
  <img src="https://img.shields.io/badge/Maps-Naver%20API-03C75A">
  <img src="https://img.shields.io/badge/Status-Completed-blue">
</p>

<p align="center">
위생용품이 긴급히 필요한 사용자를, 가까운 제공자와 안전하게 연결해주는 매칭 플랫폼
</p>

---

## ✨ 프로젝트 소
ShareDay는 **HelpMe(요청자)** 와 **HelpYou(제공자)**를 연결하여
어려운 상황의 여성을 돕는 커뮤니티 앱입니다.  
목록·지도·채팅 기반으로 실시간 연결을 지원하며, 거래 완료 시 **포인트로 보상**합니다.

> 🏆 제2회 여대 연합 IT 해커톤 「Light it up」 장려상 수상작

---

## 🧩 핵심 기능

| 기능 | 상세 |
|------|-----|
| HelpMe | 생리대 요청 게시 및 실시간 매칭 |
| HelpYou | 제공 등록, 거래 완료 시 포인트 적립 |
| 지도 기반 탐색 | 네이버 지도 기반 위치 기반 매칭 & 여성안심화장실 안내 |
| 1:1 채팅 | Firebase 실시간 채팅 |
| 포인트 | 거래 신뢰도 기반 보상 |
| 마이페이지 | 정보 수정, 거래 내역, 포인트 조회 |
| 인증 | Firebase 로그인/회원가입 |

---

## 📱 시연 이미지 (예시 공간) 
<img width="365" height="532" alt="image" src="https://github.com/user-attachments/assets/65824d61-ceff-4ff8-9fab-64d8d6c8f019" />

---

## 🛠 기술 스택

| 분야 | 기술 |
|------|------|
| 언어 | Kotlin (주 개발), Java (일부 Activity 유지보수) |
| 데이터 | Firebase Realtime Database · Cloud Storage |
| 인증/Auth | Firebase Authentication |
| 지도 | Naver Maps API |
| UI | RecyclerView · Fragment · DataBinding |
| 아키텍처 | HelpMe / HelpYou 기능 모듈화 |
| SDK | Min 23 / Target 36 |

### 📍 추가 지도 기능
- 현재 위치 기반 게시글 필터링 (기본 반경 5km)
- 여성안심화장실 공공데이터 마커 표시
- 마커 재사용으로 렌더링 성능 최적화

  
---

## 🔧 프로젝트 구조
```bash
(MainActivity)
├─ HelpMe (요청)
│  ├─ Pad
│  ├─ Tampon
│  └─ Liner
├─ HelpYou (제공)
│  ├─ Pad
│  ├─ Tampon
│  └─ Liner
├─ Map
├─ Chat
└─ MyPage
```

---


## Firebase 데이터 구조
```bash
Users/{uid}
 ├ email, name, userType, point
 ├ profileImageUrl
 └ location(lat, lng)

Posts/{postId}
 ├ userId, itemType, quantity
 ├ description, location
 └ status(active/matched/completed)

Chats/{chatRoomId}
 ├ participants
 └ messages/{msgId}
       ├ content, sender, isRead
       └ timestamp

```

## 🚀 설치 및 실행

```bash
git clone https://github.com/silver0-stack/shareDay.git
# Android Studio → Gradle Sync → 실행
# 네이버 지도 API 키 등록 필수
```
---

## 🏆 수상 & 참고자료

| 항목 | 내용 |
|------|------|
| 해커톤 | 2021 제2회 여대 연합 IT 해커톤 |
| 수상 | 장려상 |
| GitHub | [https://github.com/silver0-stack/shareDay](https://github.com/silver0-stack/shareDay) |
| 보도자료 | • [중앙일보](https://www.joongang.co.kr/article/25000985)  <br>• [서울여대](https://www.swu.ac.kr/front/boardview.do?bbsConfigFK=16&pkid=490074)  <br>• [대학저널](https://m.dhnews.co.kr/news/view/179523626813262)  <br>• [한국대학신문](https://news.unn.net/news/articleView.html?idxno=514385) |



---

## 💡 프로젝트 의의
ShareDay는 단순한 나눔 앱이 아니라 
**위생용품 접근성 문제를 해결하는 기술 기반 안정망 구축**이 목표입니다.

---

## 🗂 라이선스
학습·연구 목적 사용 권장.




#### ✅여성안심화장실 공공데이터 출처: https://www.seocho.go.kr/site/seocho/gis/Gis_List.do?type1=safe
