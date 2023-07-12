# Spring Final Project - CarrotMarket

## 주제 : 당근마켓 웹 클론코딩(Back-End) 팀 프로젝트 - 중고차 직거래 파트 구현
<details>
  <summary>프로젝트 소개</summary>

  - 국비 교육원 시절 파이널 프로젝트를 진행하며, 팀원들과 협의하여 무엇을 만들어 볼까?<br>
  하고 고민하다가 모두가 FE 보다는 BE쪽에 더 많은 흥미를 느끼고 있어서,<br>
  만들어 보고 싶은 기능들을 토론하여 정리하고 추려 보니<br>
  중고거래 플랫폼으로 유명한 "**당근마켓**" 웹 사이트가 적합 하겠다는<br>
  결론이 나오게 되어 진행하게 되었습니다.<br>

  - 해당 프로젝트는 프론트엔트 부분은 원본 사이트와 유사하게 개발 되었고,<br>
    백엔드 부분을 더 중점적으로 개발하게 된 첫 클론 코딩 팀 프로젝트 입니다.

  - **본 ReadMe 파일은 전체 프로젝트의 일부만 있으며,<br>
    필자 본인이 작업한 파트에 대한 내용만 게시되어 있음을 알려드립니다.**
  
  - **개발기간 : 23.05.15 ~ 23.6.13**

</details>

## Development Environment & Libraly
<details>
  <summary>프로젝트에 적용한 기술 목록</summary>
  
- **BE : Java(jdk15.0.2 version), SpringFramework(5.3.26 version) - Spring Legacy Project, Mybatis, Maven**
- **FE : HTML5, CSS3, JSP(Servlet), J-Query(A-Jax), JavaScript, BootStrap4**
- **DB : Oracle (DB & SQL)**
- **Server(WAS) : Apache Tomcat 9.0**
- **IDE(TOOL) : STS(3.9.18 version), SQLDEVELOPER(Oracle11g)**

</details>

## 설계의 주안점
<details>
  <summary>접기 & 상세보기</summary><br>

  - **당근마켓 웹 사이트에는 로그인/회원가입 기능이 QR코드로 인증받아서 앱과 연동되도록<br>
      처리 되어 있어서 별도로 로그인/회원가입 기능을 추가하였습니다.**
  - 프론트단 디자인은 벤치마킹한 원본 사이트와 최대한 비슷하게 구현 하였습니다.
  - 백엔드 부분을 중점적으로 개발 하였습니다.
</details>

## 중고차 직거래 파트 구현 기능
<details>
  <summary>접기 & 상세보기</summary><br>
  
- **로그인/회원가입**
- **게시판 (C, R, U, D) 기능 구현 (쇼핑몰 이미지 리스트 형식)**
- **파일 업로드 & 다운로드 기능 구현, 업로드 이미지 썸네일 형식으로 출력 (구현)**
- **페이징 처리**
- **카카오맵 api 연동**

</details>

## CarrotMarket - 중고차 직거래 테이블 구조
<details>
<summary>테이블명 : Carrot_Car 구조</summary><br><br>
  
![carrot_car - DB 테이블 구조](https://github.com/itrecipe/carrotmarket_project/assets/40875025/5174a4fc-688b-47e5-b287-f3bcd23016b3)
</details>

## CarrotMarket - 중고차 직거래 실행 화면
<details>
  <summary>접기 & 상세보기</summary><br><br>
  
  <summary>메인</summary><br>

  - CarrotMarket 메인 화면 입니다.
  
  ![carrotmarket_메인](https://github.com/itrecipe/carrotmarket_project/assets/40875025/0459cb81-e3bb-44f4-81bc-2209f6e12ddc)

  <summary>로그인</summary><br>

  ![carrotmarket_로그인](https://github.com/itrecipe/carrotmarket_project/assets/40875025/8915a1f1-ae69-45a8-8c2a-f48b08a2c27e)

  <summary>회원가입</summary><br>
  
  ![carrotmarket_회원가입](https://github.com/itrecipe/carrotmarket_project/assets/40875025/6409ab06-9c6e-4c82-a8ef-abff16012c26)

  <summary>중고차 직거래_메인</summary><br>
  - 메인 화면 상단은 BS4의 케러셀을 이용하였으며, 상품이 노출될 아래 페이지에는 이미지 리스트 형식으로 구현하였습니다.<br>
  - 케러셀 기능 하단에 "게시글 등록" 창으로 이동하여 매물 등록을 할 수 있도록 구현 하였습니다.<br><br>
  
  ![carrotmarket_중고차직거래_메인](https://github.com/itrecipe/carrotmarket_project/assets/40875025/b3a0177b-b698-461f-abd3-e2a2cfae7114)

  <summary>중고차 직거래_게시글 등록 & 파일 업로드</summary><br>
  - 게시글 등록을 할 수 있는 페이지이며, 파일(사진) 업로드와 함께 수행 할 수 있도록 구현 되었습니다.<br>

  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/90b9a6be-1d6f-4858-bc18-3eb2a192e69d)

  <summary>중고차 직거래_상세보기(1)</summary><br>
  - 게시글 상세보기 페이지 입니다.<br>
  - 차량 등록에 필요한 정보들을 모두 입력하도록 구현 되었습니다.<br>
  - 아래쪽에 현재 매물의 위치를 알 수 있도록 카카오맵 api를 사용하여 구현 하였습니다.<br>
  - 댓글 기능이 구현 되어 있으나, 현재 미완성 입니다. (현재 작업 중)<br><br>
  
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/67d84119-9246-4d6f-b327-abf305f63231)
  
  <summary>중고차 직거래_상세보기(2)</summary><br>
  
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/6da0c9ce-45bb-416a-a7a5-6b5446fc0e05)
  
  <summary>중고차 직거래_상세보기(3)</summary><br>
  
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/ed88f0c5-8859-4eff-bf58-74b2d3f9b5e0)

  
  <summary>중고차 직거래_수정</summary><br>
  - 수정 버튼을 누르면 alert창이 뜨며 확인을 누를시 수정 페이지로 이동하게 되고,<br>
    취소를 누르면 종료가 되도록 구현하였습니다.<br><br>

  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/be801a1b-b0f6-4c2e-abc7-3e8ea0d5e484)
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/0ace3eda-8c50-453b-92fb-db0e30200cf5)
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/b30eb2ac-ec6f-45eb-904d-f5d3042f9904)
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/f2d234e4-ab08-47a0-8a3a-081d4b9c139c)

  <summary>중고차 직거래_삭제</summary><br>
  - 삭제 버튼도 수정 버튼과 동일하게 누르면 alert창이 뜨면서 삭제 처리가 수행되고,<br>
    취소를 누르면 해당 작업이 종료 되도록 구현 되어 있습니다.
  
  
  ![image](https://github.com/itrecipe/carrotmarket_project/assets/40875025/d6e6eb03-5d32-49ab-aaf2-4758b28f7870)

</details>
