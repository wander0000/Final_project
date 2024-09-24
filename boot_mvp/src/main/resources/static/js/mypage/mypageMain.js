$(document).ready(function()
{
    /*
        2024-09-01 서연주 
        서브메뉴 눌렀을 때 메뉴 활성화 : active
    */
    $('.subHeader ul li').click(function(){
        $(this).addClass('active');
        $('.navMenu ul li').not(this).removeClass('active');
    });



	/*
	    2024-09-10 서연주 
	    예매내력 테이블 : 최초로딩 및 필터링 ,페이징
	*/

	  // 최초 로딩 시 데이터 로드
	   loadTicketList('30', 1, 4);  //로딩 시 1페이지 4개 데이터를 불러옴(최근1개월 데이터)

	  // 기간별 데이터 조회
  	  function loadTicketList(days, page, pageSize) {
	  		  
      $.ajax({
          url: '/loadTicketList',
          type: 'GET',
          data: { days: days, page: page, pageSize: pageSize },
          success: function(response) {
              var content = '';
              var ticketList = response.ticketList;  // 목록 데이터
              var totalCount = response.totalCount;    // 전체 데이터 개수
              totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
			  console.log(response.ticketList); // ticketList를 확인

              if (ticketList.length === 0 || ticketList === null) {
                  content = '<div class="noContent">조회된 목록이 없습니다.</div>';
              } else {
                  $.each(ticketList, function(index, dto) {
                      var date = new Date(dto.starttime);
					  var formattedDate = date.toLocaleDateString('ko-KR', {
		  			      year: 'numeric', month: '2-digit', day: '2-digit'
		  			  }).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체

		  			  var formattedTime = date.toLocaleTimeString('ko-KR', {
		  			      hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false  // 24시간 형식 사용
		  			  }).slice(0, -3);//초단위 버리기;
					  var formattedStarttime = formattedDate + ' ' + formattedTime;  // 날짜와 시간을 공백으로 연결
					 
					  // 20분 빼기(취소가능시간)
	  				  date.setMinutes(date.getMinutes() - 20);
	  				  var formattedDate = date.toLocaleDateString('ko-KR', {
   		  			      year: 'numeric', month: '2-digit', day: '2-digit'
   		  			  }).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체

   		  			  var formattedTime = date.toLocaleTimeString('ko-KR', {
   		  			      hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false  // 24시간 형식 사용
   		  			  }).slice(0, -3);//초단위 버리기
   					  var formattedCancelTime = formattedDate + ' ' + formattedTime;  // 날짜와 시간을 공백으로 연결
  						  
					  // 시간 비교
	  				  var currentDate = new Date();
	  			      var cancelButtonText = '';
	  			      var startTime = new Date(dto.starttime);
	  			      var cancelTime = new Date(startTime);
	  			      cancelTime.setMinutes(cancelTime.getMinutes() - 20);
	   
	  			      if (currentDate < cancelTime) {
	  			          cancelButtonText = '취소가능';
	  			      } else {
	  			          cancelButtonText = '취소불가';
	  			      } 
					  
                      content += '<div class="contentRow">';
                      content += '<div class="ticketCon">' + dto.movienm + '</div>';
                      content += '<div class="ticketCon">' + formattedStarttime + '</div>';
					  content += '<div>MVP '+dto.theaternm+' '+dto.roomno+'관</div>';
                      content += '<div class="ticketCon">' + dto.tmember + '</div>';
                      content += '<div class="ticketCon">';
					  content += '<button class="submitTab" id="cancelBtn" value="cancel_status" data-reservenum="' + dto.reservenum + '">' + cancelButtonText + '</button>';
//                      content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
                      content += '</div>';
                      content += '</div>';
                  });
              }
              $('#ticketListContent').html(content);  // 데이터를 화면에 업데이트
				  
  	          },
  	          error: function(xhr, status, error) {
  	              console.log('Error:', error);
  	          }
  	      });
  	  }

	  
	  $.ajax({
         url: 'getAttendanceDays',  // 출석일자를 반환하는 서버의 엔드포인트
         type: 'GET',
         dataType: 'json',  // 서버에서 JSON 형식으로 데이터를 받을 것임
         success: function(response) {
             // 가져온 출석일 리스트와 달력 UI 비교
             var attendanceDays = response.attList;  // 서버에서 가져온 List<Integer>

             // 달력의 day 요소들에 대해 반복문 실행
             $('.day').each(function() {
                 var dayValue = parseInt($(this).attr('data-day'));  // 각 day 요소의 data-day 값
                 
                 // attendanceDays에 dayValue가 있으면 아이콘으로 대체
                 if (attendanceDays.includes(dayValue)) {
                     $(this).html('<span class="mColor"><i class="fa-regular fa-circle-check" ></i></span>');  // 체크 아이콘으로 대체
                 }
             });
         },
         error: function(xhr, status, error) {
             console.error('출석 정보를 불러오는 중 오류 발생:', error);
         }
     });
	
 	//예매 취소하기
 	$(document).on('click', '#cancelBtn', function(e) {
 	    e.preventDefault();  // 기본 폼 제출 동작을 막습니다.
 		
 	    var buttonText = $(this).text();  // 버튼의 텍스트 값을 가져옴
 	    var reservenum = $(this).data('reservenum');  // 버튼에 저장된 예약번호 가져옴
 //		const csrfToken = document.getElementById(csrfTokenId).value; // CSRF 토큰 가져오기
 	    const csrfToken = document.getElementById('token').value;  // CSRF 토큰 가져오기
 		console.log("csrfToken:" + csrfToken);

 	    if (buttonText === '취소가능') {  // 버튼의 텍스트가 "취소가능"일 때만 실행
 	        if (confirm("정말로 이 예매를 취소하시겠습니까?")) {
 	            $.ajax({
 	                url: '/cancelTicket',  // 예매 취소 API 엔드포인트
 	                type: 'POST',
 	                headers: {
 //	                    "Content-Type": "application/json",
 	                    "X-CSRF-TOKEN": csrfToken  // CSRF 토큰을 헤더에 추가
 	                },
 	                data: {  // 예약번호를 JSON 형식으로 전달
 	                    reservenum: reservenum
 	                },
 	                success: function(response) {
 	                    alert(response);  // 서버 응답 메시지 출력
 	                    loadTicketList('30', 1, 5);  // 성공 시 목록 갱신
 	                },
 	                error: function(xhr, status, error) {
 	                    alert('취소에 실패했습니다. 다시 시도해 주세요.');
 	                    console.log(error);  // 에러 로그 출력
 	                }
 	            });
 	        }
 	    }
 	});

	
	
	
	

	//	멤버십 네비게이터 라인길이 동적으로 변화
	function updateLinePosition() {
	    const circles = document.querySelectorAll('.circle');
	    const line = document.querySelector('.line');
	    
	    if (circles.length > 1) {
	        const firstCircle = circles[0].getBoundingClientRect();
	        const lastCircle = circles[circles.length - 1].getBoundingClientRect();

	        // 첫 번째 circle과 마지막 circle 사이의 거리를 계산
	        const distance = lastCircle.left - firstCircle.left;

			 // line의 길이를 설정
			        line.style.width = `${distance -firstCircle.width / 4 }px`;
			//        line.style.left = `${firstCircle.width / 2}px`;
	    }
	}

	// 초기 로딩 시 위치를 설정
	updateLinePosition();

	// 스크롤이나 화면 크기 변경 시에도 위치를 동적으로 업데이트
	window.addEventListener('resize', updateLinePosition);
	window.addEventListener('scroll', updateLinePosition);


	
	
	
});// document ready 끝