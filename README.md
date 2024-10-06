# MVP(영화중개 웹사이트): 4인 프로젝트

Movie booking intermediary service built with Spring Boot, using Spring Security, Batch, and Scheduler to collect and process movie data from public APIs, provide movie information, and enable ticket reservations


## Developed by Team
- **UI/UX Design**: Design of pages and layouts.
<hr/>  
Contributor : 🐳yeonjuseo712@gmail.com

Developed Features:
- **Member Information**: Read, Update, Delete (RUD)
- **Membership Information**: Create, Read, Update, Delete (CRUD)
- **Booking Information**: Read, Delete (RD)
<hr/>
Contributor : 🎮jwlme@naver.com

Developed Features:
- **Public API Data Collection**: Set up database and collect movie data from public API.  
- **Spring Batch Scheduler**: Implement scheduling for batch processing.  
- **Recommended Movies**: Implement movie recommendation functionality.  
- **Movie Details and Likes**: Implement detailed movie information and "like" feature.  
- **Movie Viewing History (Rating + Review)**: Implement functionality for movie viewing history, including ratings and reviews.

<hr/>
Contributor : 🥑aovhff@naver.com

Developed Features:
- **Form-based Login and Registration**: Implement login and registration via forms.  
- **OAuth2 Login and Registration**: Implement OAuth2-based login and registration.  
- **Email Verification and Password Recovery**: Implement email verification, user ID recovery, and password reset features.  

<hr/>
Contributor : ☕whrhkdfo12@naver.com

Developed Features:
- **Movie Reservation**: Implement movie reservation functionality.  
- **Payment API**: Implement payment gateway integration.  
- **SMS Notifications**: Implement SMS notification functionality.  

<hr/>
💻프로젝트 개요
💬 SpringBoot Flamework를 사용하여 작성하였으며, MVC 패턴을 따르고 있습니다.Spring Security, Batch, Scheduler를 이용하며 공공API로 데이터를 수집, 가공하여 영화DB를 구성하고 영화정보를 제공하고 예매가 가능하도록 하는 영화 예매 중개 서비스입니다.

- [x] [💿서비스 시연 영상](#서비스-시연-영상)
- [x] [🎯서비스 핵심기능](#서비스-핵심기능)
- [x] [🛠기술 스택](#기술-스택)
- [x] [✨기술적 의사결정](#기술적-의사결정)
- [x] [🚧시스템 아키텍처](#시스템-아키텍처)
- [x] [📖ERD](#erd)

<hr/>

### 💿서비스 시연 영상
[![YouTube](https://github.com/wander0000/Final_project/raw/develop/assets/mvp_main.jpg)](https://youtu.be/P8uMiN_WF68)

### 🎯서비스 핵심기능
```
👨‍👨‍👧 회원 : 로그인 | 회원가입 | 소셜 로그인(네이버) | 아이디 찾기 | 비밀번호 찾기 | 임시 비밀번호 발송
🏡 마이페이지 : 예매조회/예매취소 | 맴버십안내/포인트이용내역 조회 | 관람영화조회/관람평CRUD/스크랩영화조회 | 쿠폰/할인권 등록,조회 | 회원정보조회 수정 | 회원정보수정 | 회원탈퇴
📽️ 영화 : 박스오피스 조회 | 장르별 영화 조회 | 영화 상세정보 조회(줄거리, 트레일러,스틸컷, 평점 등) | 찜하기 등록,삭제 | 섹션/브랜드/가격범위/태그/필터별 조회, 장바구니, 바로주문
🎫 예매 : 영화예매 | 예매시 쿠폰/할인권/포인트 조회 및 사용, 적립 | 예매완료 시 문자전송 | 결제(카카오페이, 토스페이, 이니시스)
```

<details>
<summary>핵심기능 #1. 출석이벤트 및 만근시 할인권 발행/문자전송</summary>

![fuction001](https://github.com/wander0000/Final_project/raw/develop/assets/scheduler_att.png)
- [x] 스프링에서 제공되는 `Scheduler` 이용, @Scheduled와 @EnableScheduling 어노테이션에 cron 표기법을 이용해서 원하는 시간대에 원하는 기능이 실현될 수 있도록 구현하였습니다.
- [x] `AuthenticationSuccessHandler`를 오버라이딩한 `CustomLoginSuccessHandler`에서 로그인한 유저의 오늘의 출석상태를 확인하여 첫 출석이면 포인트를 지급하고 쿠키를 생성하며, 프론트에서는 쿠키값을 확인하여 출석이벤트에 참여하였음을 알리는 Popup이 뜨도록 하고, '오늘은 더이상 보지 않기'를 check하면 쿠키값을 false로 설정하여 Popup이 뜨지 않도록 하였습니다.
- [x] 매달 1일, 전월의 만근자를 조회하여 할인권을 발행하고, 할인권번호를 문자 API를 이용하여 문자로 발송하도록 했습니다.
</details>
<details>
<summary>핵심기능 #2. 스케쥴러 : 생일자 쿠폰발행</summary>

![fuction002](https://github.com/wander0000/Final_project/raw/develop/assets/scheduler_birth.png)
- [x] 스프링에서 제공되는 `Scheduler` 이용, @Scheduled와 @EnableScheduling 어노테이션에 cron 표기법을 이용해서 원하는 시간대에 원하는 기능이 실현될 수 있도록 구현하였습니다.
- [x] 생일쿠폰은 발행할 때 사용가능 상태로 insert해서 예매시 바로 사용할 수 있도록 하였습니다.
```xml
    <!-- 쿠폰등록  -->
    <insert id="insertCoupon" parameterType="com.boot.DTO.CouponDTO">
		INSERT INTO coupontb (couponno, endDate, reason, refno, uuid, acrec)
		SELECT CONCAT(
	           #{type}, 
	           DATE_FORMAT(NOW(), '%y%m%d'), 
	           '-',
	           LPAD(
	               IFNULL(
	                   (SELECT COUNT(*) + 1 
	                    FROM coupontb
	                    WHERE DATE(adate) = CURDATE()), 1
	               ), 4, '0') 
		       ),
		       DATE_SUB(CURDATE(), INTERVAL #{period} DAY), 
		       #{reason},       
		       #{refno},          
		       #{uuid},
		       #{acrec}
    </insert>
```
</details>
<details>
<summary>핵심기능 #3. 맴버십 및 포인트 적립/사용</summary>

![fuction003](https://github.com/wander0000/Final_project/raw/develop/assets/pthist.gif)
- [x] 일반 로그인/소셜 로그인 구분 없이 모든 신규 회원은 `가입과 동시에` 등급:Welcome으로, 1,000원의 적립금을 적립 받습니다.
- [x] `등급별`으로 예매시 적립금 혜택이 주어집니다. 쿠폰/할인권/포인트를 사용한 금액을 제외한 실결제금액에 대해 3~10%의 포인트가 적립됩니다.
- [x] 회원은 `마이페이지`에서 자신의 적립금 적립/사용 상세 내역을 확인할 수 있습니다.

</details>
<details>
<summary>핵심기능 #4. 예매취소</summary>

![fuction004](https://github.com/wander0000/Final_project/raw/develop/assets/cancelprocedure.png)
- [x] 예매 취소시 11가지 쿼리가 실행되어야 해서 `MySQL`의 프로시져 기능을 활용하였습니다.
- [x] 예매정보테이블의 내용을 '예매'에서 '취소'로 변경하고, 결제 API를 이용하여 실결제금액이 반환되도록 하고, 결제 시 사용한 포인트와 점유한 좌석을 원복하고, 적립된 포인트 또한 반환되도록 하였습니다.
- [x] 하나의 트랜젝션 안에서 작업이 수행되고 하나라도 오류가 나면 전체가 롤백되도록 작성했습니다.
</details>
<details>
<summary>핵심기능 #5. ajax 이용한 동적 구현 및 페이징</summary>

![fuction005](https://github.com/wander0000/Final_project/raw/develop/assets/pagingparam.png)
- [x] 목록은 ajax를 이용하여 동적으로 구현하고, 세그먼트 기법 중 페이징을 제대로 작동하게 하기위하여 페이지 버튼에도 필터링과 페이징을 위한 인자를 배열값으로 저장하여 화면단이 구성되도록 function을 호출할 때 인자를 다시 Json으로 보낼 수 있는 형태로 변환하여 서버에 데이터를 요청하도록 하였습니다.
- [x] `페이징`은 SQL쿼리에서 LIMIT와 OFFSET을 이용하여 구현하였습니다.
```xml
    <!-- 포인트 이력 테이블 조회(기간별)-->
    <select id="getUserPtHis" resultType="com.boot.DTO.PthistDTO">
        SELECT * FROM pthisttb where uuid=#{uuid}
        <include refid="days"></include>
        <include refid="pagenation"></include>
    </select>
    
     <!-- 포인트 이력 목록의 갯수(기간별)-->
    <select id="getTotalCountFiltered">
        SELECT COUNT(*) 
        FROM pthisttb where uuid=#{uuid}
        <include refid="days"></include>
    </select>

    <!--  정렬 로직: 기간별 15일,1개월,2개월, 3개월 -->
	  <sql id="days">
      <if test="(days == '15')">
        AND trndt BETWEEN DATE_SUB(CURDATE(), INTERVAL 15 DAY) AND now()
        ORDER BY trndt DESC
      </if>
      <if test="(days == '30') or days == null or days == ''">
         AND trndt BETWEEN DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND now()
         ORDER BY trndt DESC
      </if>
      <if test='(days == "60")'>
         AND trndt BETWEEN DATE_SUB(CURDATE(), INTERVAL 60 DAY) AND now()
         ORDER BY trndt DESC
      </if>
      <if test='(days == "90")'>
         AND trndt BETWEEN DATE_SUB(CURDATE(), INTERVAL 90 DAY) AND now()
         ORDER BY trndt DESC
      </if>
     </sql>
   
     <sql id="pagenation">
   	  	LIMIT #{pageSize} OFFSET #{offset}
     </sql>
```
</details>
<details>
<summary>핵심기능 #6. REST Api 회원정보</summary>

![fuction006](https://github.com/wander0000/Final_project/raw/develop/assets/restapi.png)

- [x] 시큐리티가 적용된 프로젝트여서 사용자타입이 2가지로 인가된 사용자의 정보를 꺼내쓰는 객체가 UserDetails와 OAuth2User로 나누어 져서 UserDetails, UserDetailsService도 커스텀해서 사용했습니다.
- [x] 사용자의 정보 조회, 수정, 삭제는 REST 방식으로 구현하였습니다.
- [x] OAuth2유저의 경우 일부 정보만 수정할 수 있도록, 화면단에서도 유저타입별로 수정버튼이 노출될 수 있도록 분기처리하였습니다.
</details>
<details>


### 사용자 시나리오(시연영상)

|<small>회원가입(일반)</small>|<small>회원가입(네이버)<small>|<small>비밀번호찾기/변경</small>|
|:-:|:-:|:-:|
|![003](https://github.com/wander0000/Final_project/raw/develop/assets/generate1.gif)|![004](https://github.com/wander0000/Final_project/raw/develop/assets/generate2.gif)|![005](https://github.com/wander0000/Final_project/raw/develop/assets/findpw.gif)|
|<small><b>아이디찾기</b></small>|<small><b>영화조회</b></small>|<small><b>영화스크랩</b></small>|
|![006](https://github.com/wander0000/Final_project/raw/develop/assets/findid.gif)|![007](https://github.com/wander0000/Final_project/raw/develop/assets/getmovie.gif)|![008](https://github.com/wander0000/Final_project/raw/develop/assets/likemovie.gif)|
|<small><b>영화예매(카카오페이)</b></small>|<small><b>영화예매(토스페이)</b></small>|<small><b>영화예매(이니시스)</b></small>|
|![009](https://github.com/wander0000/Final_project/raw/develop/assets/bookmovie_kakao.gif)|![010](https://github.com/wander0000/Final_project/raw/develop/assets/bookmovie_toss.gif)|![011](https://github.com/wander0000/Final_project/raw/develop/assets/bookmovie_ini.gif)|
|<small><b>예매내역조회</b></small>|<small><b>예매취소</b></small>|<small><b>포인트이력조회</b></small>|
|![012](https://github.com/wander0000/Final_project/raw/develop/assets/booklist.gif)|![013](https://github.com/wander0000/Final_project/raw/develop/assets/cancelmovie.gif)|![014](https://github.com/wander0000/Final_project/raw/develop/assets/pthist.gif)|
|<small><b>무비스토리</b></small>|<small><b>출석이벤트 문자API/쿠폰등록</b></small>|<small><b>탈퇴</b></small>|
|![012](https://github.com/wander0000/Final_project/raw/develop/assets/cancelmovie.gif)|![013](https://github.com/wander0000/Final_project/raw/develop/assets/attendence.gif)|![014](https://github.com/wander0000/Final_project/raw/develop/assets/unsubscribe.gif)|


### 🛠기술 스택
OS | Windows 10
--- | --- |
Language | ![Java](https://img.shields.io/badge/JAVA-000?style=for-the-badge&logo=java&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-000?style=for-the-badge&logo=spring&logoColor=white) ![HTML5](https://img.shields.io/badge/html5-000?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-000?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-000?style=for-the-badge&logo=javascript&logoColor=white)
IDE | ![STS4](https://img.shields.io/badge/STS4-000?style=for-the-badge&logo=spring&logoColor=white) ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-000?style=for-the-badge&logo=visualstudiocode&logoColor=white) ![MySQL Workbench](https://img.shields.io/badge/MySQL%20Workbench-000?style=for-the-badge&logo=oracle&logoColor=white)
Framework | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![MyBatis](https://img.shields.io/badge/Mybatis-d40000?style=for-the-badge)
Build Tool | ![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
Database |![MySQL](https://img.shields.io/badge/Mysql-4479A1?style=flat-square&logo=Mysql&logoColor=white)
Frontend | ![HTML5](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) ![jQuery](https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
Library | ![Spring Security](https://img.shields.io/badge/spring%20security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
API | ![Iamport Payment](https://img.shields.io/badge/Iamport%20Payment-c1272d?style=for-the-badge) ![coolSMS](https://img.shields.io/badge/cool%20SMS-f7943a?style=for-the-badge)
Server |![Apache Tomcat 9.0](https://img.shields.io/badge/Apache%20Tomcat%20-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
Version Control | ![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)

### 🚧시스템 아키텍처
![architecture](https://github.com/wander0000/Final_project/raw/develop/assets/mvc.png)]
![개발환경](https://github.com/wander0000/Final_project/raw/develop/assets/idle.png)]

### 📖ERD
![erd](https://github.com/wander0000/Final_project/raw/develop/assets/erd.png)]
