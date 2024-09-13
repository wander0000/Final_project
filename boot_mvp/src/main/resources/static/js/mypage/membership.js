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
        포인트 이력 테이블 : 최초로딩 및 필터링 ,페이징
    */
	
	  // 최초 로딩 시 데이터 로드
	   loadPointHistory('30', 1, 5);  // 최초 로딩 시 1페이지 5개 데이터를 불러옴(최근1개월 데이터)

	  // 버튼 클릭 시 AJAX로 데이터를 동적으로 로드(기간별)
	  $('.filter').on('click', function() {
	      var days = $(this).data('days');  // 버튼에 설정된 기간
	      loadPointHistory(days, 1, 5);//필터링 기간days로 1페이지 5개 데이터 불러옴
		  
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
	  function loadPointHistory(days, page, pageSize) {
		  //페이징을 위한 인자 저장
		  loadMethod = 'period';  // loadTicketList 함수로 로드
		  loadParams = { days, page, pageSize };  // 인자 저장
		  
	      currentPage = page;  // 현재 페이지를 저장
	      $.ajax({
	          url: '/filterPtHis',
	          type: 'GET',
	          data: { days: days, page: page, pageSize: pageSize },
	          success: function(response) {
	              var content = '';
	              var historyList = response.historyList;  // 목록 데이터
	              var totalCount = response.totalCount;    // 전체 데이터 개수
	              totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	              
	              if (historyList.length === 0 || historyList === null) {
	                  content = '<div class="noContent">조회된 목록이 없습니다.</div>';
	              } else {
	                  $.each(historyList, function(index, dto) {
	                      var date = new Date(dto.trndt);
						  var formattedDate = date.toLocaleDateString('ko-KR', {
						      year: 'numeric', month: '2-digit', day: '2-digit'
						  }).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체

						  var formattedTime = date.toLocaleTimeString('ko-KR', {
						      hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false  // 24시간 형식 사용
						  });

						  var finalFormattedDateTime = formattedDate + ' ' + formattedTime;  // 날짜와 시간을 공백으로 연결
	                      content += '<div class="contentRow">';
	                      content += '<div class="contentRowDe">' + finalFormattedDateTime + '</div>';
	                      content += '<div class="contentRowDe">' + dto.kind + '</div>';
	                      content += '<div class="contentRowDe">' + dto.description + ' (' + dto.pthistno + ')</div>';
	                      content += '<div class="contentRowDe">' + (dto.kind === '적립' ? dto.recqt : dto.issqt) + ' P</div>';
	                      content += '</div>';
	                  });
	                  createPagination(totalCount, pageSize);  // 페이징 버튼 생성
	              }
	              $('#pointHistoryContent').html(content);  // 데이터를 화면에 업데이트

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
	  loadParams = { startDate, endDate, page, pageSize };  // 인자 저장
	  // 이전 그룹으로 이동
	    $(document).on('click', '.prev-btn', function() {
	        var prevPage = Math.max(1, loadParams.page - pageGroupSize);  // 이전 페이지 계산
	        loadParams.page = prevPage;  // 페이지 업데이트

	        if (loadMethod === 'monthly') {
	            loadPointHistoryBetween(loadParams.startDate, loadParams.endDate, prevPage, loadParams.pageSize);
	        } else if (loadMethod === 'period') {
	            loadPointHistory(loadParams.days, prevPage, loadParams.pageSize);
	        }
	    });

	    // 다음 그룹으로 이동
	    $(document).on('click', '.next-btn', function() {
	        var nextPage = Math.min(totalPages, loadParams.page + pageGroupSize);  // 다음 페이지 계산
	        loadParams.page = nextPage;  // 페이지 업데이트

	        if (loadMethod === 'monthly') {
	            loadPointHistoryBetween(loadParams.startDate, loadParams.endDate, nextPage, loadParams.pageSize);
	        } else if (loadMethod === 'period') {
	            loadPointHistory(loadParams.days, nextPage, loadParams.pageSize);
	        }
	    });

	    // 페이지 버튼 클릭 시
	    $(document).on('click', '.pageBtn', function() {
	        var page = $(this).data('page');  // 클릭한 페이지 번호
	        loadParams.page = page;  // 페이지 업데이트

	        if (loadMethod === 'monthly') {
	            loadPointHistoryBetween(loadParams.startDate, loadParams.endDate, page, loadParams.pageSize);
	        } else if (loadMethod === 'period') {
	            loadPointHistory(loadParams.days, page, loadParams.pageSize);
	        }
	    });
	  
	  
	  
	  // 특정기간조회 : 조회 버튼 클릭 시 AJAX로 데이터를 로드
	      $('.submitBtn').on('click', function() {
	          var startDate = $('.startDate').val();
	          var endDate = $('.endDate').val();

	          if (startDate && endDate) {
	              loadPointHistoryBetween(startDate, endDate, 1,5);
	          } else {
	              alert("시작일과 종료일을 선택해주세요.");
	          }
	      });

		  function loadPointHistoryBetween(startDate, endDate, page, pageSize) {
			  //페이징을 위한 인자 저장
			  loadMethod = 'monthly';  // monthly 함수로 로드
			  loadParams = { startDate, endDate, page, pageSize };  // 인자 저장
			  // 모든 filtern 버튼에서 active 클래스 제거
			  $('.filter').removeClass('active');
		      // AJAX 요청
		      $.ajax({
		          url: '/filterPtHisBetween',  // 서버의 엔드포인트
		          type: 'GET',
		          data: { startDate: startDate, endDate: endDate, page: page, pageSize: pageSize },  // 기간 데이터를 전송
		          success: function(response) {
		              var content = '';
		              var historyList = response.historyList;  // 목록 데이터
		              var totalCount = response.totalCount;    // 전체 데이터 개수

		              // 응답이 비어 있거나 null인 경우 처리
		              if (historyList.length === 0 || historyList === null) {
		                  content = '<div class="noContent">해당 기간에 조회된 내용이 없습니다.</div>';
		              } else {
		                  // 응답받은 데이터를 테이블에 업데이트
		                  $.each(historyList, function(index, dto) {
		                      var date = new Date(dto.trndt);
							  var formattedDate = date.toLocaleDateString('ko-KR', {
				  			      year: 'numeric', month: '2-digit', day: '2-digit'
				  			  }).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체
	
				  			  var formattedTime = date.toLocaleTimeString('ko-KR', {
				  			      hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false  // 24시간 형식 사용
				  			  });
	
				  			  var finalFormattedDateTime = formattedDate + ' ' + formattedTime;  // 날짜와 시간을 공백으로 연결
		                      content += '<div class="contentRow">';
		                      content += '<div class="contentRowDe">' + finalFormattedDateTime + '</div>';
		                      content += '<div class="contentRowDe">' + dto.kind + '</div>';
		                      content += '<div class="contentRowDe">' + dto.description + ' (' + dto.pthistno + ')</div>';
		                      if (dto.kind === '적립') {
		                          content += '<div class="contentRowDe">' + dto.recqt + ' P</div>';
		                      } else {
		                          content += '<div class="contentRowDe">' + dto.issqt + ' P</div>';
		                      }
		                      content += '</div>';
		                  });
		              	  createPagination(totalCount, pageSize); // 페이징 버튼 생성
		              }

		              // 기존 내용을 지우고 새 데이터를 추가
		              $('#pointHistoryContent').html(content);
					  // 현재 페이지에 active 클래스 추가
	                  $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');

		             
		          },
		          error: function(xhr, status, error) {
		              console.log('Error:', error);
		          }
		      });
		  }
	  

});// document ready 끝