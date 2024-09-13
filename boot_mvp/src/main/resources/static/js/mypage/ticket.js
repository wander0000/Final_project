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

    // 연도와 월 선택 이벤트 핸들러
    $('#yearSelect, #monthSelect').change(updateFilter);
    
    function updateFilter() {
        var year = $('#yearSelect').val();
        var month = $('#monthSelect').val();
        
        if (year && month) {
            // 선택된 연도와 월로 필터링 로직 구현
            console.log('Selected: ' + year + '년 ' + month + '월');
            // 여기에 필터링 또는 데이터 로드 로직 추가
        }
    }

	
	

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
	                  content = '<div class="ticketCon">조회된 목록이 없습니다.</div>';
	              } else {
	                  $.each(ticketList, function(index, dto) {
	                      var date = new Date(dto.starttime);
	                      var formattedStarttime = date.toLocaleString('ko-KR', {
	                          year: 'numeric', month: '2-digit', day: '2-digit',
	                          hour: '2-digit', minute: '2-digit', second: '2-digit'
	                      }).replace(/\./g, '-').replace(' ', '').slice(0, -3); // 콤마 제거 및 마지막 불필요한 부분 제거;
						 
						  // 20분 빼기(취소가능시간)
						  date.setMinutes(date.getMinutes() - 20);
	                      var formattedCancelTime = date.toLocaleString('ko-KR', {
	                          year: 'numeric', month: '2-digit', day: '2-digit',
	                          hour: '2-digit', minute: '2-digit', second: '2-digit'
	                      }).replace(/\./g, '-').replace(' ', '').slice(0, -3); // 콤마 제거 및 마지막 불필요한 부분 제거;
						  
						  var currentDate = new Date();

					     // 시간 비교
					     var cancelButtonText = '';
					     var startTime = new Date(dto.starttime);
					     var cancelTime = new Date(startTime);
					     cancelTime.setMinutes(cancelTime.getMinutes() - 20);

					     if (currentDate < cancelTime) {
					         cancelButtonText = '취소 가능';
					     } else if (currentDate >= cancelTime && currentDate < startTime) {
					         cancelButtonText = '취소 불가';
					     } else {
					         cancelButtonText = '상영 중';
					     }
						 
	                      content += '<div class="contentRowBook">';
	                      content += '<div class="ticketCon">' + dto.reservenum + '</div>';
	                      content += '<div class="ticketCon">' + dto.movienm + '</div>';
	                      content += '<div class="ticketCon">' + formattedStarttime + '</div>';
	                      content += '<div class="ticketCon">' + dto.tmember + '</div>';
	                      content += '<div class="ticketCon">' + formattedCancelTime + '</div>';
	                      content += '<div class="ticketCon">';
	                      content += '<button class="submitTab" value="cancel_status" type="submit">' + cancelButtonText + '</button>';
	                      content += '</div>';
	                      content += '<div class="ticketCon">';
	                      content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
	                      content += '</div>';
	                  });
	                  createPagination(totalCount, pageSize);  // 페이징 버튼 생성
	              }
	              $('#ticketListContent').html(content);  // 데이터를 화면에 업데이트

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
	          },
	          error: function(xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }

	  // 페이징 버튼 생성 함수
	  function createPagination(totalCount, pageSize) {
	      totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	      var startPage = Math.floor((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
	      var endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
	      
	      var pagination = '';

	      // 이전 페이지 그룹으로 이동하는 chevron (prev)
	      if (startPage > 1) {
	          pagination += '<span class="prev-btn"><i class="fa-solid fa-chevron-left"></i></span>';
	      }
	      
	      // 페이지 버튼들
	      for (var i = startPage; i <= endPage; i++) {
	          pagination += '<button class="pageBtn" data-page="' + i + '">' + i + '</button>';
	      }
	      
	      // 다음 페이지 그룹으로 이동하는 chevron (next)
	      if (endPage < totalPages) {
	          pagination += '<span class="next-btn"><i class="fa-solid fa-chevron-right"></i></span>';
	      }
	      
	      $('#pagination').html(pagination);  // 동적으로 태그 추가
	  }

	  // 이전 그룹으로 이동
	  $(document).on('click', '.prev-btn', function() {
	      var prevPage = Math.max(1, currentPage - pageGroupSize);  // 이전 그룹의 첫 페이지로 이동
	      loadPointHistory('30', prevPage, 5);  // 페이지 데이터 재로드
	  });

	  // 다음 그룹으로 이동
	  $(document).on('click', '.next-btn', function() {
	      var nextPage = Math.min(totalPages, currentPage + pageGroupSize);  // 다음 그룹의 첫 페이지로 이동
	      loadPointHistory('30', nextPage, 5);  // 페이지 데이터 재로드
	  });

	  // 페이지 버튼 클릭 시
	  $(document).on('click', '.pageBtn', function() {
	      var page = $(this).data('page');  // 클릭한 페이지 번호
	      loadPointHistory('30', page, 5);  // 클릭된 페이지 번호로 다시 데이터 로드
	  });
	  
	  
	  
	  // 특정기간조회 : 조회 버튼 클릭 시 AJAX로 데이터를 로드
	      $('.submitBtn').on('click', function() {
	          var year = $('#yearSelect').val();
	          var month = $('#monthSelect').val();

	          if (year && month) {
	              loadMonthlyTicketList(year, month, 1,5);
	          } else {
	              alert("년도와 월을 선택해주세요.");
	          }
	      });

		  function loadMonthlyTicketList(year, month, page, pageSize) {
		      // AJAX 요청
		      $.ajax({
		          url: '/loadMonthlyTicketList',  // 서버의 엔드포인트
		          type: 'GET',
		          data: { year: year, month: month, page: page, pageSize: pageSize },  // 기간 데이터를 전송
				  success: function(response) {
	              var content = '';
	              var ticketList = response.ticketList;  // 목록 데이터
	              var totalCount = response.totalCount;    // 전체 데이터 개수
	              totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	              
	              if (ticketList.length === 0 || ticketList === null) {
	                  content = '<div class="ticketCon">조회된 목록이 없습니다.</div>';
	              } else {
	                  $.each(ticketList, function(index, dto) {
	                      var date = new Date(dto.starttime);
	                      var formattedStarttime = date.toLocaleString('ko-KR', {
	                          year: 'numeric', month: '2-digit', day: '2-digit',
	                          hour: '2-digit', minute: '2-digit', second: '2-digit'
	                      }).replace(/\./g, '-').replace(' ', '').slice(0, -3); // 콤마 제거 및 마지막 불필요한 부분 제거;
	  				 
	  				  // 20분 빼기(취소가능시간)
	  				  date.setMinutes(date.getMinutes() - 20);
	                      var formattedCancelTime = date.toLocaleString('ko-KR', {
	                          year: 'numeric', month: '2-digit', day: '2-digit',
	                          hour: '2-digit', minute: '2-digit', second: '2-digit'
	                      }).replace(/\./g, '-').replace(' ', '').slice(0, -3); // 콤마 제거 및 마지막 불필요한 부분 제거;
	  				  
	  				  var currentDate = new Date();

	  			     // 시간 비교
	  			     var cancelButtonText = '';
	  			     var startTime = new Date(dto.starttime);
	  			     var cancelTime = new Date(startTime);
	  			     cancelTime.setMinutes(cancelTime.getMinutes() - 20);

	  			     if (currentDate < cancelTime) {
	  			         cancelButtonText = '취소 가능';
	  			     } else if (currentDate >= cancelTime && currentDate < startTime) {
	  			         cancelButtonText = '취소 불가';
	  			     } else {
	  			         cancelButtonText = '상영 중';
	  			     }
	  				 
	                      content += '<div class="contentRowBook">';
	                      content += '<div class="ticketCon">' + dto.reservenum + '</div>';
	                      content += '<div class="ticketCon">' + dto.movienm + '</div>';
	                      content += '<div class="ticketCon">' + formattedDate + '</div>';
	                      content += '<div class="ticketCon">' + dto.tmember + '</div>';
	                      content += '<div class="ticketCon">' + formattedCancelTime + '</div>';
	                      content += '<div class="ticketCon">';
	                      content += '<button class="submitTab" value="cancel_status" type="submit">' + cancelButtonText + '</button>';
	                      content += '</div>';
	                      content += '<div class="ticketCon">';
	                      content += '<button class="submitTab" value="ticket_status" type="submit">입장권보내기</button>';
	                      content += '</div>';
	                  });
	                  createPagination(totalCount, pageSize);  // 페이징 버튼 생성
	              }
	              $('#ticketListContent').html(content);  // 데이터를 화면에 업데이트

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
	          },
	          error: function(xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }
	  
	
	
	
});// document ready 끝