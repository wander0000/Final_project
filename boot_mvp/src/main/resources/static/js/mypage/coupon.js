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

	
	var currentPage = 1;  // 현재 페이지
	var totalPages = 0;   // 총 페이지 수
	var pageGroupSize = 5;  // 페이지 버튼 그룹 크기
	var c_type = '';  // 쿠폰 or 할인권 저장 변수
	var c_acrec = '';  // 사용가능 or 사용완료 저장 변수
	var pageSize = 5;  // 목록의 크기 5로 초기화

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
		
		if(tabType == "coupon"){
			loadCouponList('C','A', 1, pageSize);  // 최초 로딩 시 1페이지 5개 데이터를 불러옴(사용가능한 쿠폰)
		}else{
			loadCouponList('D','A', 1, pageSize);  // 최초 로딩 시 1페이지 5개 데이터를 불러옴(사용가능한 할인권)
		}
    });

	

    /*
        2024-09-151 서연주 
        쿠폰등록
    */
//	$(".couponForm").on("submit", function(event) {
//	       event.preventDefault(); // 폼의 기본 제출 동작을 막음
//
//	       // 입력값과 CSRF 토큰 가져오기
//	       const couponno = $("input[name='couponno']").val();
//	       const csrfToken = $("input[name='_csrf']").val();
//
//	       // AJAX 요청
//	       $.ajax({
//	           url: '/generateCoupon', // 서버의 매핑된 URL
//	           type: 'POST', // POST 메서드
//			   headers: {
//   	              "Content-Type": "application/json",
////   	              "X-CSRF-TOKEN": csrfToken
//   	           },
//	           data: JSON.stringify({ couponno: couponno, _csrf: csrfToken }), // 서버로 보낼 데이터 (쿠폰 번호)
//	           success: function(response) {
//	               alert("쿠폰이 성공적으로 등록되었습니다.");
//	               loadCouponList('C','A', 1, pageSize);
//	           },
//	           error: function(xhr, status, error) {
//				// HTTP 상태 코드가 200이 아닌 경우에만 오류 처리
//			     if (xhr.status !== 200) {
//			         console.error("에러 발생:", error);
//			         alert("쿠폰 등록에 실패했습니다.");
//			     }
//	           }
//	       });
//	   });

	$('form').submit(function(e) {
	    e.preventDefault();
	    var formData = $(this).serialize();
		// 입력값 가져오기
	    const couponno = $(this).find('.inputReg').val(); // 입력된 쿠폰 번호
	    const coupon_type = $(this).data('type'); // 폼의 data-type 값 (쿠폰의 타입 C 또는 D)
	
		console.log("couponno:"+couponno.charAt(0)+"coupon_type:"+coupon_type);
	    // 쿠폰 타입과 입력된 쿠폰 번호의 첫 번째 글자 비교
	    if (couponno.charAt(0) !== coupon_type) {
	        // 쿠폰 타입과 일치하지 않으면 경고 메시지 표시 및 입력 필드로 커서 이동
	        alert("쿠폰 타입을 확인하세요.(쿠폰은 C, 할인권은 D입니다.)");
	        $("input[name='couponno']").focus(); // 입력 필드에 커서 이동
	        return; // 폼 제출 중단
	    }	
	    
	    $.ajax({
	        url: '/generateCoupon',
	        type: 'POST',
	        data: formData,
	        success: function(response) {
				// couponno의 첫 번째 문자를 확인하여 로직 처리
		        if (couponno.charAt(0) === 'C') {
		            alert('쿠폰이 성공적으로 등록되었습니다.');
		            loadCouponList('C', 'A', 1, pageSize);  // 쿠폰 목록
		        } else if (couponno.charAt(0) === 'D') {
		            alert('할인권이 성공적으로 등록되었습니다.');
		            loadCouponList('D', 'A', 1, pageSize);  // 할인권 목록
		        } else {
		            console.error("알 수 없는 쿠폰 형식입니다.");
		        }
				$('.inputReg').val('');  // 입력 필드 초기화
	        },
	        error: function(xhr, status, error) {
	            alert(xhr.responseText);
	        }
	    });
	});
	

    /*
        2024-09-01 서연주 
        필터버튼 활성화 : active
    */
     $('button.filter').click(function(){
		c_type = $(this).data('type');  // 버튼에 설정된 타입(쿠폰,할인권)
		c_acrec = $(this).data('acrec');  // 버튼에 설정된 타입(사용완료/사용가능)
		console.log("c_type:"+c_type)
		loadCouponList(c_type, c_acrec, 1, pageSize);  //데이터를 불러옴
        // 버튼 활성화
        $(this).addClass('active');
        $('button.filter').not(this).removeClass('active');
    });

	// 최초 로딩 시 데이터 로드
    loadCouponList('C','A', currentPage, pageSize);  // 최초 로딩 시 1페이지 5개 데이터를 불러옴(사용가능한 쿠폰)

	// 기간별 데이터 조회
	function loadCouponList(type, acrec, page, pageSize) {
  		  
    currentPage = page;  // 현재 페이지를 저장
    $.ajax({
        url: '/getCouponList',
        type: 'GET',
        data: { type: type, acrec: acrec, page: page, pageSize: pageSize },
        success: function(response) {
            var content = '';
            var couponList = response.couponList;  // 목록 데이터
            var totalCount = response.totalCount;    // 전체 데이터 개수
            totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
		    console.log(response.couponList); // couponList를 확인

            if (couponList.length === 0 || couponList === null) {
                content = '<div class="noContent">조회된 목록이 없습니다.</div>';
            } else {
                $.each(couponList, function(index, dto) {
                    var date = new Date(dto.endDate);
					var formattedDate = date.toLocaleDateString('ko-KR', {
					year: 'numeric', month: '2-digit', day: '2-digit'
		  			}).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체
					
					var statusText = '';
				    if (dto.acrec == 'A') {
			       		statusText = '사용가능';
				    } else {
				    	statusText = '사용불가';
				    }
					  
                    content += '<div class="contentRow">';
                    content += '<div class="couponCon">' + dto.reason+'('+dto.name+')</div>';
                    content += '<div class="couponCon">' + dto.benefit + '</div>';
                    content += '<div class="couponCon">~' + formattedDate + '</div>';
                    content += '<div class="couponCon">' + statusText + '</div>';
                    content += '</div>';
                });
            }
			if (type === 'C'){
	            $('#couponListContent').html(content);  // 데이터를 화면에 업데이트
			}else{
	            $('#discntListContent').html(content);  // 데이터를 화면에 업데이트
			}
			//페이징을 위한 인자 저장
	   		c_type = type;  //쿠폰 or 할인권 저장
	   		c_acrec = acrec;  //사용가능 or 사용완료 저장
            createPagination(totalCount, pageSize,c_type,c_acrec);
		  
            // 현재 페이지에 active 클래스 추가
            $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
			},
			error: function(xhr, status, error) {
              console.log('Error:', error);
          }
      });
	}

  	// 페이징 버튼 생성 함수
  	function createPagination(totalCount, pageSize,type,acrec) {
  		console.log('createPagination 호출-type:'+type+'acrec:'+acrec);
  		console.log('createPagination 호출-totalCount:'+totalCount);
  		$('#pagination').empty();  // #pagination 요소의 모든 자식 요소와 내용을 초기화

  	    totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
  	    var startPage = Math.floor((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
  	    var endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
  	    
  	    var pagination = '';

  	    // 이전 페이지 그룹으로 이동하는 chevron (prev)
  	    if (startPage > 1) {
  	        pagination += '<span class="prev-btn" data-type="' + type + '" data-acrec=\'' + acrec + '\'><i class="fa-solid fa-chevron-left"></i></span>';
  	    }
  	    
  	    // 페이지 버튼들
  	    for (var i = startPage; i <= endPage; i++) {
  	        pagination += '<button class="pageBtn" data-page="' + i + '" data-type="' + type + '" data-acrec=\'' + acrec + '\'>' + i + '</button>';
  	    }
  	    
  	    // 다음 페이지 그룹으로 이동하는 chevron (next)
  	    if (endPage < totalPages) {
  	        pagination += '<span class="next-btn" data-type="' + type + '" data-acrec=\'' + acrec + '\'><i class="fa-solid fa-chevron-right"></i></span>';
  	    }
  	    
  	    $('#pagination').html(pagination);  // 동적으로 태그 추가
  	}

  	  //페이지 넘버 버튼 클릭
  	  $(document).on('click', '.pageBtn', function() {
  	      var page = $(this).data('page');  // 클릭한 페이지 번호
  	      var type = $(this).data('type');  // type 값 가져오기
  	      var acrec = $(this).data('acrec');  // acrec 값 가져오기

          loadCouponList(type, acrec, page, 5);
  	  });

  	// 이전 그룹으로 이동
  	$(document).on('click', '.prev-btn', function() {
		var type = $(this).data('type');  // type 값 가져오기
		var acrec = $(this).data('acrec');  // acrec 값 가져오기
  	    
  	    var prevPage = Math.max(1, currentPage - pageGroupSize);  // 이전 페이지 계산
  	    page = prevPage;  // 페이지 업데이트
  	    
		loadCouponList(type, acrec, page, 5);
  	});

  	// 다음 그룹으로 이동
  	$(document).on('click', '.next-btn', function() {
		var type = $(this).data('type');  // type 값 가져오기
		var acrec = $(this).data('acrec');  // acrec 값 가져오기
			  	    
  	    var nextPage = Math.min(totalPages, currentPage + pageGroupSize);  // 다음 페이지 계산
  	    page = nextPage;  // 페이지 업데이트
  	    
  	    loadCouponList(type, acrec, page, 5);
  	});
	  			
		
});// document ready 끝