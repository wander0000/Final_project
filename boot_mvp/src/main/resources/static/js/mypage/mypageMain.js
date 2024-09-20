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
					 
  						  
                      content += '<div class="contentRow">';
                      content += '<div class="ticketCon">' + dto.movienm + '</div>';
                      content += '<div class="ticketCon">' + formattedStarttime + '</div>';
					  content += '<div>MVP '+dto.theaternm+' '+dto.roomno+'관</div>';
                      content += '<div class="ticketCon">' + dto.tmember + '</div>';
                      content += '<div class="ticketCon">';
                      content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
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
                     $(this).html('<i class="fa-regular fa-circle-check" class="mColor"></i>');  // 체크 아이콘으로 대체
                 }
             });
         },
         error: function(xhr, status, error) {
             console.error('출석 정보를 불러오는 중 오류 발생:', error);
         }
     });
	
	

	
});// document ready 끝