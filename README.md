![image](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/ebb89e46-5085-4e4a-a66b-7f1685dd2811)

# Leafpage

전자책 대여 서비스

## 프로젝트 설명
Leafpage는 유저가 전자책을 대여, 반납을 통해 독서활동을 할 수 있는 서비스 입니다. <br>
사용자의 usecase를 고려하여 어떻게 하면 이 서비스를 더 편하고 풍부하게 즐길 수 있을지 고려하며 제작하였습니다. <br>
관리자는 도서와 유저를 관리하며 서비스 운영을 이어갑니다. <br>
클라우드 애플리케이션 개발자 양성과정에서의 커리큘럼에 맞춰 프로젝트 개발 기술로 Servlet/JSP를 채택하였습니다.



## 팀원

|                                            팀장                                            |                                           부팀장                                            |                                            팀원                                            |                                            팀원                                             |                                            팀원                                             |
| :----------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------: |
| <img src="https://avatars.githubusercontent.com/u/90694560?v=4" width="150" height="150"/> | <img src="https://avatars.githubusercontent.com/u/104562141?v=4" width="150" height="150"/> | <img src="https://avatars.githubusercontent.com/u/93602351?v=4" width="150" height="150"/> | <img src="https://avatars.githubusercontent.com/u/116622194?v=4" width="150" height="150"/> | <img src="https://avatars.githubusercontent.com/u/113010528?v=4" width="150" height="150"/> |
|                           [김동욱](https://github.com/95Donguk)                            |                         [김경욱](https://github.com/Kyoungwookkim)                          |                          [곽다은](https://github.com/Gwak-daeun)                           |                             [송정희](https://github.com/eeheg)                              |                           [이수민](https://github.com/soomin0019)                           |

## 기능

### 김동욱

- 도서 대여 및 반납 담당
- 개발 총괄

### 김경욱

- 인프라 담당
- 관리자 기능 담당
  - 도서 등록 기능
  - 도서 수정 기능
  - 도서 조회 기능
  - 도서 삭제 기능
  - 유저 조회 기능
  - 유저 상태변경 기능
  - 유저 삭제 기능

### 곽다은

- 도서 뷰어
  - 도서 책갈피
  - 다크모드
- 마이페이지 
  - 기한 지난 도서 자동 반납 
  - 대여 중 및 반납 완료 도서 조회 
- 도서 상세페이지 
  - 도서 기본 정보 조회
  - 리뷰 등록/삭제 
  - 동일 저자 도서 조회
- 검색
  - 키워드
  - 항목별, 카테고리별
  - 인기순, 최신순 정렬
  - 무한스크롤

### 송정희

- 유저 정보 기능 담당
  - 회원가입 기능
  - 이메일 인증 기능
  - 유저 정보 수정 기능
  - 로그인/로그아웃 기능
  - ID/PW 찾기 기능
  - 유효성 검사
- 유스케이스 담당
- 보안 담당

### 이수민

- 상세페이지 기능 담당
  - 좋아요 기능 담당
- 디자인 담당

## 인프라

![infra](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/517af151-58f9-402e-a24b-8472efdba067)

## ERD

![ERD](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/a48a3c4c-a363-442d-a73a-f75de1e0e589)

## API

### 🧑‍💻 USER

|      Description      | Method |    URI     | 담당 |
|:---------------------:|:------:| :--------: | :--: |
|         마이페이지         |  GET   | /mypageInfo.do |      |
|          검색           |  GET   | /search.do |      |
|    메인페이지에서의 검색 결과     |  GET   | /searchResult.do |      |
| 검색 결과 페이지에서의 검색/정렬 결과 |  GET   | /sortBooks.do |      |
|      검색 결과 무한스크롤      |  GET   | /bookScroll.do |      |
|       도서 상세 페이지       |  GET   | /detailPageView.do |      |
|       도서 뷰어 책갈피       | UPDATE | /saveUserBookY.do |      |
|         리뷰 등록         |  POST  | /makeReview.do |      |
|         리뷰 삭제         | DELETE | /removeReview.do |      |




### 🧑‍🔧 ADMIN

| Description | Method |    URI     | 담당 |
| :---------: | :----: | :--------: | :--: |
|             |        | /search.do |      |

### 📖 BOOK

| Description | Method |      URI       |  담당  |
| :---------: | :----: | :------------: | :----: |
| 책 대여하기 |  post  |  /rentBook.do  | 김동욱 |
| 책 반납하기 |  post  | /returnBook.do | 김동욱 |

## 영상

https://clipchamp.com/watch/XMeyWw9ZM48

## 모바일 화면

![search](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/ae85dc63-2511-45b9-96af-5bbd141586ab)
<br>

![detail](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/679c1ce4-7184-42d3-a26f-df5213d4afdd)
<br>

![signup-signin](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/31897c99-c8e5-4139-889b-9c8adafb6605)
<br>

![mypage](https://github.com/Beanstalk-mzc/Leafpage/assets/90694560/f638f3f5-49cf-47e9-851a-8b687bf04858)
<br>

## 트러블슈팅

## 기술 스택

<div> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <br>
  
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white">
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black">
  <img src="https://img.shields.io/badge/servlet&jsp-007396?style=for-the-badge&logo=java&logoColor=white">
  <br>
  
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

## 협업 툴

<div> 
  <img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=white"> 
  <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> 
</div>

## 프로젝트 진행 참고 자료

https://www.notion.so/3-9-14-27-45de0c9d12f449d4bd566cbb97753734

## 회의록

https://www.notion.so/d034fdac023f45ddb5e42a12df867251?v=1588f6e7ec0d4319a17e9ae021878c7f&pvs=4

## 요구사항정의서
