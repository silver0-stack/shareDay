![쉐어데이_표지](https://user-images.githubusercontent.com/76518850/132691070-f56bf087-275a-459b-bbc2-20603ca5e312.png)
>***🙌프로젝트 설명에 앞서 본 프로젝트는 개발만 했을 뿐, 앱 출시는 하지 않은 상태입니다.<br>하지만 출시를 했다는 가정 하에 README를 읽어주시면 감사하겠습니다.***
<h1 align="center">ShareDay 🌸</h1>
<p align="center"><b>여성을 위한 생리대 나눔 매칭 Android 앱</b></p>

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?logo=android">
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?logo=kotlin">
  <img src="https://img.shields.io/badge/Database-Firebase-orange?logo=firebase">
  <img src="https://img.shields.io/badge/Maps-Naver%20API-03C75A">
  <img src="https://img.shields.io/badge/Status-Completed-blue">
</p>

<p align="center">
사용자가 생리대가 긴급히 필요할 때, 가까운 도움 제공자와 연결해주는 매칭 플랫폼
</p>

---

## ✨ 프로젝트 개요
ShareDay는 생리대가 긴급히 필요한 사용자(**HelpMe**)와 제공 가능한 사용자(**HelpYou**)를 매칭하는 안드로이드 앱입니다.  
도움을 주고받은 내역은 **포인트 시스템**으로 보상되며 여성안심화장실 정보를 지도 기반으로 확인할 수 있습니다.

> 📍 2021 제2회 여대 연합 IT 해커톤 「Light it up」 장려상 수상작

---

## 🧩 핵심 기능

| 기능 | 설명 |
|------|------|
| HelpMe | 생리대 요청 게시 및 매칭 |
| HelpYou | 생리대 제공 등록 및 포인트 지급 |
| Chat | HelpMe–HelpYou 1:1 채팅 |
| Map | 여성안심화장실 위치를 지도에 마커 표시 |
| MyPage | 포인트·사용이력·설정 관리 |
| Auth | Firebase 기반 이메일/Google 회원가입 & 로그인 |

---

## 📱 시연 이미지 (예시 공간) 
<img width="365" height="532" alt="image" src="https://github.com/user-attachments/assets/65824d61-ceff-4ff8-9fab-64d8d6c8f019" />

---

## 🛠 기술 스택

| 구분 | 사용 기술 |
|------|----------|
| 언어 | Kotlin |
| 백엔드/DB | Firebase Authentication / Realtime Database |
| 지도 | Naver Maps API |
| UI | Fragment · RecyclerView · DataBinding |
| 아키텍처 | Multi-module UX 구조 (HelpMe / HelpYou) |
| 지원 버전 | Target SDK 36 · Min SDK 23 |

---

## 🔧 프로젝트 구조
```bash
(MainActivity)
- HelpMe  
  · HelpMePadFragment  
  · HelpMeTamponFragment  
  · HelpMeLinerFragment  
- HelpYou  
  · HelpYouPadFragment  
  · HelpYouTamponFragment  
  · HelpYouLinerFragment  
- Map  
- Chat  
- MyPage  
```
---

## 🗂 데이터 모델 구조
```bash
HelpMe  → pad, tampon, liner  
HelpYou → pad2, tampon2, liner2  
User    → uid, email, name, point, history  
Chat    → sender, receiver, message, timestamp  
```
---

## 🔄 사용자 흐름
```bash
회원가입 / 로그인  
↓  
게시글 등록 (HelpMe / HelpYou)  
↓  
매칭 → 1:1 채팅  
↓  
거래 완료 → 포인트 적립  
```
---

## 🚀 설치 및 실행

```bash
1) `git clone https://github.com/silver0-stack/shareDay.git`  
2) Android Studio로 열기  
3) Gradle Sync 후 앱 실행  
※ Naver Map API 키 등록 필수
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
ShareDay는 단순한 생리대 나눔 서비스가 아니라 **여성의 생리용품 접근성 문제를 기술로 해결하는 커뮤니티 플랫폼**을 목표로 합니다.

---

## 🗂 라이선스
학습·연구 목적 공개. **상업적 이용 제한**




#### ✅여성안심화장실 공공데이터 출처: https://www.seocho.go.kr/site/seocho/gis/Gis_List.do?type1=safe
