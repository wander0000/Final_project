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
        2024-09-01 서연주 
         탭 메뉴 활성화 : active
    */
    $('.tabCon').click(function(){
        // 탭 활성화
        $(this).addClass('active');
        $('.tabCon').not(this).removeClass('active');
        
        // 탭 유형 가져오기
        const tabType = $(this).data('tab');
        
        // 콘텐츠 활성화
        $(`.contentCon.${tabType}`).addClass('active');
        $('.contentCon').not(`.contentCon.${tabType}`).removeClass('active');
    });



    /*
        2024-09-01 서연주 
        필터버튼 활성화 : active
    */
    $('button.filter').click(function(){
        $(this).addClass('active');
        $('button.filter').not(this).removeClass('active');
    });


    /*
        2024-09-01 서연주 
        연도/월 선택 
    */
    var $yearSelect = $('#yearSelect');
    var currentYear = new Date().getFullYear();
    
    // 현재 연도 기준으로 2년 전부터 올해까지 옵션 생성
    for (var i = currentYear - 2; i <= currentYear ; i++) {
        $yearSelect.append($('<option>', {
            value: i,
            text: i + '년'
        }));
    }
    
    // 현재 연도 선택
    $yearSelect.val(currentYear);

	/*
	    2024-09-10 서연주 
	    예매내력 테이블 : 최초로딩 및 필터링 ,페이징
	*/

	  // 최초 로딩 시 데이터 로드
	   loadTicketList('30', 1, 5);  // 최초 로딩 시 1페이지 5개 데이터를 불러옴(최근1개월 데이터)

	  // 버튼 클릭 시 AJAX로 데이터를 동적으로 로드(기간별)
	  $('.filter').on('click', function() {
	      var days = $(this).data('days');  // 버튼에 설정된 기간
	      loadTicketList(days, 1, 5);//필터링 기간days로 1페이지 5개 데이터 불러옴
		  
		  // 모든 filtern 버튼에서 active 클래스 제거
		  $('.filter').removeClass('active');
		   
		  // 클릭된 버튼에 active 클래스 추가
		  $(this).addClass('active');
		  
	  });

	  var currentPage = 1;  // 현재 페이지
	  var totalPages = 0;   // 총 페이지 수
	  var pageGroupSize = 5;  // 페이지 버튼 그룹 크기
	  var loadMethod = '';  // 어느 함수로 로드되었는지 저장
	  var loadParams = {};  // 함수의 인자를 저장

	  // 기간별 데이터 조회
  	  function loadTicketList(days, page, pageSize) {
	  		  
      currentPage = page;  // 현재 페이지를 저장
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
					  //통화단위 표시
					  const formattedPriceKR = new Intl.NumberFormat('ko-KR', {
					    style: 'currency',
					    currency: 'KRW',
					  }).format(dto.tprice);
					  console.log(formattedPriceKR);
  						 
                      content += '<div class="contentRowBook">';
                      content += '<div class="ticketCon">' + dto.reservenum + '</div>';
                      content += '<div class="ticketCon">' + dto.movienm + '</div>';
                      content += '<div class="ticketCon">' + formattedStarttime + '</div>';
                      content += '<div class="ticketCon">' + dto.tmember + '</div>';
                      content += '<div class="ticketCon">' + formattedCancelTime + '</div>';
					  content += '<div class="ticketCon">' + formattedPriceKR + '</div>';
                      content += '<div class="ticketCon">';
					  content += '<button class="submitTab" id="cancelBtn" value="cancel_status" data-reservenum="' + dto.reservenum + '">' + cancelButtonText + '</button>';
                      content += '</div>';
//                      content += '<div class="ticketCon">';
//                      content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
//                      content += '</div>';
                      content += '</div>';
                  });
              }
              $('#ticketListContent').html(content);  // 데이터를 화면에 업데이트
				
			  setCancelButtonBehavior();//취소불가 커서변경 function호출
			    
			  //페이징을 위한 인자 저장
    		  loadMethod = 'period';  // loadTicketList 함수로 로드
    		  loadParams = { days, page, pageSize };  // 인자 저장(배열)
    		  console.log(loadMethod + "=>loadMethod 설정됨");
    		  console.log(JSON.stringify(loadParams)+"=>loadParams 설정됨");  // loadParams의 내용을 문자열로 출력
              createPagination(totalCount, pageSize,loadMethod,JSON.stringify(loadParams));  // 페이징 버튼 생성

              // 현재 페이지에 active 클래스 추가
              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
  	          },
  	          error: function(xhr, status, error) {
  	              console.log('Error:', error);
  	          }
  	      });
  	  }

		  
	  // 특정기간조회 : 조회 버튼 클릭 시 AJAX로 데이터를 로드
      $('.submitBtn').on('click', function() {
          var keyword = $('#orderBy').val();
          var year = $('#yearSelect').val();
          var month = $('#monthSelect').val();

          if (keyword && year && month) {
              loadMonthlyTicketList(keyword, year, month, 1,5);
          } else {
              alert("기준(예매일,상영일)과 년도, 월을 선택해주세요.");
          }
      });

	  function loadMonthlyTicketList(keyword, year, month, page, pageSize) {
		  // 모든 filtern 버튼에서 active 클래스 제거
  		  $('.filter').removeClass('active');
	  	  // AJAX 요청
	      $.ajax({
	          url: '/loadMonthlyTicketList',  // 서버의 엔드포인트
	          type: 'GET',
	          data: { keyword: keyword, year: year, month: month, page: page, pageSize: pageSize },  // 기간 데이터를 전송
			  success: function(response) {
              var content = '';
              var ticketList = response.ticketList;  // 목록 데이터
              var totalCount = response.totalCount;    // 전체 데이터 개수
              totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
              
			  console.log('ticketList'+ticketList);
			  console.log('totalCount:'+totalCount);
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
				 //통화단위 표시
 				  const formattedPriceKR = new Intl.NumberFormat('ko-KR', {
 				    style: 'currency', currency: 'KRW', }).format(dto.tprice);
				  content += '<div class="contentRowBook">';
				  content += '<div class="ticketCon">' + dto.reservenum + '</div>';
				  content += '<div class="ticketCon">' + dto.movienm + '</div>';
				  content += '<div class="ticketCon">' + formattedStarttime + '</div>';
				  content += '<div class="ticketCon">' + dto.tmember + '</div>';
				  content += '<div class="ticketCon">' + formattedCancelTime + '</div>';
				  content += '<div class="ticketCon">' + formattedPriceKR + '</div>';
				  content += '<div class="ticketCon">';
				  content += '<button class="submitTab" id="cancelBtn" value="cancel_status"  data-reservenum="' + dto.reservenum + '">' + cancelButtonText + '</button>';
				  content += '</div>';
//				  content += '<div class="ticketCon">';
//				  content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
//				  content += '</div>';  
				  content += '</div>'; 

				  document.getElementById('ticketListContent').innerHTML += content;
				  

                  });
              }
              $('#ticketListContent').html(content);  // 데이터를 화면에 업데이트
			  
			  setCancelButtonBehavior();//취소불가 커서변경 function호출
				  //페이징을 위한 인자 저장
				  loadMethod = 'monthly';  // monthly 함수로 로드
				  loadParams = { keyword, year, month, page, pageSize };  // 인자 저장
				  
                  createPagination(totalCount, pageSize, loadMethod, JSON.stringify(loadParams));  // 페이징 버튼 생성
					console.log('createPagination 으로 가지고 가는 totalCount:'+totalCount);

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
	          },
	          error: function(xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }
		  
		
	  // 버튼 동작을 설정하는 함수
	  function setCancelButtonBehavior() {
	    // 동적으로 생성된 모든 버튼에 대해 처리
	    $('.submitTab').each(function() {
	      // 버튼의 텍스트가 '취소불가'인지 확인
	      if ($(this).text() === '취소불가') {
	        // 커서를 기본 화살표 모양으로 설정
	        $(this).css('cursor', 'default');
	        // 클릭 이벤트 막기
	        $(this).on('click', function(e) {
	          e.preventDefault();  // 클릭을 차단
	        });
	      }
	    });
	  }  
	
	// 페이징 버튼 생성 함수
	function createPagination(totalCount, pageSize, loadMethod, loadParams) {
		console.log('createPagination 호출-loadMethod:'+loadMethod+'loadParams:'+loadParams);
		console.log('createPagination 호출-totalCount:'+totalCount);
		$('#pagination').empty();  // #pagination 요소의 모든 자식 요소와 내용을 초기화

	    totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	    var startPage = Math.floor((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
	    var endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
	    
	    var pagination = '';

	    // 이전 페이지 그룹으로 이동하는 chevron (prev)
	    if (startPage > 1) {
	        pagination += '<span class="prev-btn" data-loadmethod="' + loadMethod + '" data-loadparams=\'' + loadParams + '\'><i class="fa-solid fa-chevron-left"></i></span>';
	    }
	    
	    // 페이지 버튼들
	    for (var i = startPage; i <= endPage; i++) {
	        pagination += '<button class="pageBtn" data-page="' + i + '" data-loadmethod="' + loadMethod + '" data-loadparams=\'' + loadParams + '\'>' + i + '</button>';
	    }
	    
	    // 다음 페이지 그룹으로 이동하는 chevron (next)
	    if (endPage < totalPages) {
	        pagination += '<span class="next-btn" data-loadmethod="' + loadMethod + '" data-loadparams=\'' + loadParams + '\'><i class="fa-solid fa-chevron-right"></i></span>';
	    }
	    
	    $('#pagination').html(pagination);  // 동적으로 태그 추가
	}

	  //페이지 넘버 버튼 클릭
	  $(document).on('click', '.pageBtn', function() {
	      var page = $(this).data('page');  // 클릭한 페이지 번호
	      var loadMethod = $(this).data('loadmethod');  // loadMethod 값 가져오기
	      var loadParams = $(this).attr('data-loadparams');  // 문자열로 저장된 loadParams 가져오기
		  console.log("pageBtn 클릭의 loadParams=>"+loadParams);  // 파라미터확인

	      try {
	          loadParams = JSON.parse(loadParams);  // 객체로 변환
	          loadParams.page = page;  // 페이지 업데이트
	      } catch (e) {
	          console.error("loadParams 파싱 오류: ", e);
	          return;
	      }

	      if (loadMethod === 'monthly') {
	          loadMonthlyTicketList(loadParams.keyword, loadParams.year, loadParams.month, page, loadParams.pageSize);
	      } else if (loadMethod === 'period') {
	          loadTicketList(loadParams.days, page, loadParams.pageSize);
	      }
	  });

	  // 이전 그룹으로 이동
	  $(document).on('click', '.prev-btn', function() {
	      var loadMethod = $(this).data('loadmethod');  // loadMethod 값 가져오기
	      var loadParams = $(this).attr('data-loadparams');  // 문자열로 저장된 loadParams 가져오기
	      console.log("prev-btn 클릭의 loadParams => " + loadParams);  // 파라미터 확인

	      try {
	          loadParams = JSON.parse(loadParams);  // 문자열을 객체로 변환
	      } catch (e) {
	          console.error("loadParams 파싱 오류: ", e);
	          return;
	      }

	      var prevPage = Math.max(1, currentPage - pageGroupSize);  // 이전 페이지 계산
	      loadParams.page = prevPage;  // 페이지 업데이트

	      if (loadMethod === 'monthly') {
	          loadMonthlyTicketList(loadParams.keyword, loadParams.year, loadParams.month, prevPage, loadParams.pageSize);
	      } else if (loadMethod === 'period') {
	          loadTicketList(loadParams.days, prevPage, loadParams.pageSize);
	      }
	  });

	  // 다음 그룹으로 이동
	  $(document).on('click', '.next-btn', function() {
	      var loadMethod = $(this).data('loadmethod');  // loadMethod 값 가져오기
	      var loadParams = $(this).attr('data-loadparams');  // 문자열로 저장된 loadParams 가져오기
	      console.log("next-btn 클릭의 loadParams => " + loadParams);  // 파라미터 확인

	      try {
	          loadParams = JSON.parse(loadParams);  // 문자열을 객체로 변환
	      } catch (e) {
	          console.error("loadParams 파싱 오류: ", e);
	          return;
	      }

	      var nextPage = Math.min(totalPages, currentPage + pageGroupSize);  // 다음 페이지 계산
	      loadParams.page = nextPage;  // 페이지 업데이트

	      if (loadMethod === 'monthly') {
	          loadMonthlyTicketList(loadParams.keyword, loadParams.year, loadParams.month, nextPage, loadParams.pageSize);
	      } else if (loadMethod === 'period') {
	          loadTicketList(loadParams.days, nextPage, loadParams.pageSize);
	      }
	  });
	

	// 예매취소내역 : 예매취소 탭  클릭 시 AJAX로 데이터를 로드
    $('.ticketCancel').on('click', function() {
		
		
	
  	  // AJAX 요청
      $.ajax({
          url: '/loadCanceledTicketList',  // 서버의 엔드포인트
          type: 'GET',
		  success: function(response) {
	          var content = '';
	          var ticketList = response.ticketList;  // 목록 데이터
			  console.log('ticketList'+ticketList);
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
					 
					// 취소일시
					var date = new Date(dto.mdate);
					var formattedDate = date.toLocaleDateString('ko-KR', {
					    year: 'numeric', month: '2-digit', day: '2-digit'
					}).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체
					
					var formattedTime = date.toLocaleTimeString('ko-KR', {
					    hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false  // 24시간 형식 사용
					});
					var formattedCancelTime = formattedDate + ' ' + formattedTime;  // 날짜와 시간을 공백으로 연결
					//통화단위 표시
					const formattedPriceKR = new Intl.NumberFormat('ko-KR', {
					    style: 'currency', currency: 'KRW', }).format(dto.tprice);
					content += '<div class="contentRowCancel">';
					content += '<div class="couponCon">' + formattedCancelTime + '</div>';
					content += '<div class="couponCon">' + dto.movienm + '</div>';
					content += '<div class="couponCon">' + formattedStarttime + '</div>';
					content += '<div class="couponCon">' + formattedPriceKR + '</div>';
					content += '</div>'; 

				  document.getElementById('cancelticketList').innerHTML += content;
	                });
	            }
	            $('#cancelticketList').html(content);  // 데이터를 화면에 업데이트

	        },
	        error: function(xhr, status, error) {
	            console.log('Error:', error);
	        }
	    });
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

	
	

	

	
});// document ready 끝