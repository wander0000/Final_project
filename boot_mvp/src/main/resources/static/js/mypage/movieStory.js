$(document).ready(function()
{
 


    /*
        2024-090--02 서연주 
        글자수 제한 + 넘는건 ...처리: 영화제목/관람평
    */
    $('.commentTitle').each(function() {
        var length = 13; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });

    $('.commentBody').each(function()  {
        var length = 80; //표시할 글자 수 정하기
    
        $(this).each(function()
        {
            if($(this).text().length >= length)
            {
                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
            }
        });
    });
    
    //본영화에서 영화제목
    // $('.movieTitle').each(function()  {
    //     var length = 20; //표시할 글자 수 정하기
    
    //     $(this).each(function()
    //     {
    //         if($(this).text().length >= length)
    //         {
    //             $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
    //         }
    //     });
    // });

    //파트너스에서 영화제목/게시글제목
//    $('.movieName > .title').each(function()  {
//        var length = 12; //표시할 글자 수 정하기
//    
//        $(this).each(function()
//        {
//            if($(this).text().length >= length)
//            {
//                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
//            }
//        });
//    });
//    $('.partnerTitle').each(function()  {
//        var length = 24; //표시할 글자 수 정하기
//    
//        $(this).each(function()
//        {
//            if($(this).text().length >= length)
//            {
//                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
//            }
//        });
//    });


	var currentPage = 1;  // 현재 페이지
	var totalPages = 0;   // 총 페이지 수
	var pageGroupSize = 5;  // 페이지 버튼 그룹 크기
	var pageSize = 4;  // 목록의 크기 4로 초기화


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
	     
	     // 탭 유형에 따라 다른 함수 호출
	     if(tabType === "watched"){
	         loadtWatchedMovies(1, 4);
	     } else if(tabType === "comment") {
	         loadUserReviews(1, 7);
	     } else {
	         loadlikedMovies(1, 4);
	     }
	 });


	 // 최초 로딩 시 데이터 로드
     loadtWatchedMovies(currentPage, pageSize);  // 최초 로딩



	function loadtWatchedMovies(page, pageSize) {
		  // 모든 filtern 버튼에서 active 클래스 제거
			//  $('.filter').removeClass('active');
	  	  // AJAX 요청
	      $.ajax({
	          url: '/loadtWatchedMovies',  // 서버의 엔드포인트
	          type: 'GET',
	          data: { page: page, pageSize: pageSize },  // 데이터를 전송
			  success: function(response) {
	            var content = '';
	            var movieList = response.movieList;  // 목록 데이터
	            var totalCount = response.totalCount;    // 전체 데이터 개수
	            totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	            
			  console.log('movieList'+movieList);
			  console.log('totalCount:'+totalCount);
	            if (movieList.length === 0 || movieList === null) {
	                content = '<div class="noContent">관람한 영화가 없습니다.</div>';
	            } else {
	                $.each(movieList, function(index, dto) {
						var date = new Date(dto.viewday);
						var formattedDate = date.toLocaleDateString('ko-KR', {
						year: 'numeric', month: '2-digit', day: '2-digit'
			  			}).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체
						
						content += '<div class="detailCon">';
						content += '<div class="movieDetail">';
						content += '<img src="'+dto.moviepostimg+'" alt="'+dto.movienm+'">';
						content += '<input type="hidden" id="movieno" value="'+dto.movieno+'}">';
						content += '<div class="infoCon">';
						content += '<div class="movieConM">';
						content += '<div class="movieTitle">' + dto.movienm + '</div>';
						content += '<div class="detailInfo">';
						content += '<div>'+ formattedDate+'</div>';
						content += '<div>MVP '+dto.theaternm+' '+dto.roomno+' 관</div>';
						content += '<div>'+dto. tmember+' 명</div>';
						content += '</div>';
						content += '</div>';
						content += '</div>';
						content += '</div>';
						content += '<button class="commentBtn" type="submit">관람평남기기</button>';
						content += '</div>';
						
						document.getElementById('watchedmovieList').innerHTML += content;
	                });
	            }
	            $('#watchedmovieList').html(content);  // 데이터를 화면에 업데이트
				createPagination("watched",totalCount, pageSize);  // 페이징 버튼 생성

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
	          },
	          error: function(xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }
	  
	  
	  
	

	  function loadUserReviews(page, pageSize) {
	      // AJAX 요청
	      $.ajax({
	          url: '/loadUserReviews',  // 서버의 엔드포인트
	          type: 'GET',
	          data: { page: page, pageSize: pageSize },  // 데이터를 전송
	          success: function(response) {
	              var content = '';
	              var commentList = response.commentList;  // 목록 데이터
	              var totalCount = response.totalCount;    // 전체 데이터 개수
	              totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산

	              console.log('commentList' + commentList);
	              console.log('totalCount:' + totalCount);
	              if (commentList.length === 0 || commentList === null) {
	                  content = '<div class="noContent">등록된 관람평이 없습니다.</div>';
	              } else {
	                  $.each(commentList, function (index, dto) {
	                      var date = new Date(dto.viewday);
	                      var formattedDate = date.toLocaleDateString('ko-KR', {
	                          year: 'numeric', month: '2-digit', day: '2-digit'
	                      }).replace(/\./g, '-').replace(/-\s/g, '-').slice(0, -1);  // 날짜 부분에서 점을 하이픈으로 대체

	                      content += '<div class="commentDetail">';
	                      content += '<div class="commentTitle">' + dto.movienm + '</div>';
	                      content += '<div class="commentTime">' + formattedDate + '</div>';
	                      content += '<div class="commentBody">' + dto.review + '</div>';
	                      content += '<div class="commentStar">';
	                      content += '<span class="material-symbols-outlined">stars</span>';
	                      content += '<div class="commentStarRate">' + dto.star + '</div>';
	                      content += '<span class="material-symbols-outlined optionReviewB" style="cursor:pointer">more_vert</span>';
	                      content += '<ul class="optionReview">';
	                      content += '<li class="modify-pop" data-movienm="'+dto.movienm+'" data-review="'+dto.review+'" data-star="'+dto.star+'">수정하기</li>';
	                      content += '<li class="delete-review" data-movieno="'+dto.movieno+'">삭제하기</li>';
	                      content += '</ul>';
	                      content += '</div>';

	                      // 팝업 태그 추가 (수정 팝업)
	                      content += '<div class="pop write" style="display:none;">';
	                      content += '<div class="popInner">';
	                      content += '<div class="popH">';
	                      content += '<h5 class="popTitle">관람평 수정하기</h5>';
	                      content += '<span class="material-symbols-outlined close-pop" style="cursor:pointer">close</span>';
	                      content += '</div>';
	                      content += '<div class="popB">';
	                      content += '<div class="popMovienm">' + dto.movienm + '<br>';
	                      content += '<span style="font-size: 18px; color: #111;">영화 어떠셨나요?</span></div>';

	                      // 별점 부분 추가
	                      content += '<div class="popRating">';
	                      content += '<div class="rating">';
	                      for (var i = 1; i <= 10; i++) {
	                          var isHalf = (i % 2 === 1); // 홀수는 반쪽 별
	                          var labelClass = isHalf ? 'rating__label--half' : 'rating__label--full';
	                          var inputValue = i;
	                          var starId = isHalf ? 'starhalf' + inputValue : 'star' + inputValue; // 반쪽 별과 꽉 찬 별 id 구분
	                          var checked = (dto.star == i) ? 'checked' : ''; // 현재 별점과 동일한 별점에 체크

	                          content += '<label class="rating__label ' + labelClass + '" for="' + starId + '">';
	                          content += '<input type="radio" id="' + starId + '" class="rating__input" name="rating2" value="' + inputValue + '" ' + checked + '>';
	                          content += '<span class="star-icon one"></span>';
	                          content += '</label>';
	                      }
	                      content += '</div>';
	                      content += '<div class="rating-result"><span id="selected-rating">' + dto.star + '</span>점</div>';
	                      content += '</div>';

	                      content += '<div class="textAreaWrap">';
	                      content += '<textarea id="textArea" placeholder="실관람평을 남겨주세요.">'+dto.review+'</textarea>';
	                      content += '</div>';
	                      content += '</div>';
	                      content += '<div class="popF">';
	                      content += '<div class="buttonWrap">';
	                      content += '<button class="button cancel close-pop" type="cancel">취소</button>';
	                      content += '<button class="button modify" type="submit" data-movieno="'+dto.movieno+'">수정</button>';
	                      content += '</div>';
	                      content += '</div>';
	                      content += '</div>';
	                      content += '</div>';  // 수정 팝업 종료

	                      // 팝업 태그 추가 (삭제 팝업)
	                      content += '<div class="pop delete" style="display:none;">';
	                      content += '<div class="popInner">';
	                      content += '<div class="popH">';
	                      content += '<h5 class="popTitle">관람평 삭제하기</h5>';
	                      content += '<span class="material-symbols-outlined close-pop" style="cursor:pointer">close</span>';
	                      content += '</div>';
	                      content += '<div class="popB">';
	                      content += '<div class="popMovienm">정말로 삭제하시겠습니까?</div>';
	                      content += '</div>';
	                      content += '<div class="popF">';
	                      content += '<div class="buttonWrap">';
	                      content += '<button class="button cancel close-pop" type="cancel">취소</button>';
	                      content += '<button class="button delete" type="submit" data-movieno="'+dto.movieno+'">>삭제</button>';
	                      content += '</div>';
	                      content += '</div>';
	                      content += '</div>';
	                      content += '</div>';  // 삭제 팝업 종료

	                      content += '</div>';  // commentDetail 종료
	                  });
	              }
	              $('#commentList').html(content);  // 데이터를 화면에 업데이트
	              createPagination("comment", totalCount, pageSize);  // 페이징 버튼 생성

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');

	              // 별점 기능 초기화
	              initializeStarRating();
	          },
	          error: function (xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }
	  
	  function initializeStarRating() {
	      $('.pop.write').each(function() {
	          var $popup = $(this);
	          var stars = $popup.find('.star-icon.one');
	          var result = $popup.find('#selected-rating');
	          var selectedValue = parseInt(result.text());  // 초기 선택된 값

	          // 초기 별점 설정
	          updateStars(selectedValue);

	          stars.on('mouseover', function() {
	              var index = $(this).closest('.rating__label').index();
	              updateStars(index + 1, true);
	          });

	          stars.on('mouseout', function() {
	              updateStars(selectedValue);
	          });

	          stars.on('click', function() {
	              selectedValue = $(this).closest('.rating__label').index() + 1;
	              updateStars(selectedValue);
	              result.text(selectedValue);
	          });

	          function updateStars(value, isHover = false) {
	              stars.each(function(index) {
	                  if (index < value) {
	                      $(this).addClass('filled');
	                      $(this).css('opacity', isHover ? 0.5 : 1);
	                  } else {
	                      $(this).removeClass('filled');
	                      $(this).css('opacity', 1);
	                  }
	              });
	          }
	      });
	  }
	  
	// :누르면 수정하기, 삭제하기 토글 나오게
	$(document).on('click', '.optionReviewB', function() {
	    var $optionReview = $(this).next('.optionReview');  // 바로 다음에 있는 .optionReview 찾기
	    if ($optionReview.length > 0) {
	        $optionReview.toggle();  // .optionReview의 display 속성을 토글
	    }
	});
	    
	    
	// 팝업을 열고 데이터를 채워주는 함수
	$(document).on('click', '.modify-pop', function() {
	    var movienm = $(this).data('movienm');
	    var review = $(this).data('review');
	    var star = $(this).data('star');

	    // 팝업 데이터 채우기
	    $('.pop.write .popMovienm').text(movienm);
	    $('#textArea').val(review);
	    $('#selected-rating').text(star);

	    // 별점 선택 부분 업데이트 (해당 라디오 버튼을 체크)
	    $('.pop.write .rating__input').each(function() {
	        if ($(this).val() == star) {
	            $(this).prop('checked', true);
	        } else {
	            $(this).prop('checked', false);
	        }
	    });

	    // 팝업 표시
	    $(this).closest('.commentDetail').find('.pop.write').css({"display":"flex"});
	});
    	
	
	
	// 수정 버튼에 대한 이벤트 핸들러 설정
	  $(document).on('click', '.button.modify', function() {
	      // data-movieno 속성에서 영화 번호를 가져옴
	      var movieno = $(this).data('movieno');
	      
	      // modifyReview 함수 호출
	      modifyReview(movieno);
	  });	
   	
	  
	  function modifyReview(movieno) {
	    const csrfToken = document.getElementById('token').value;
	    var star = $("input[name='rating']:checked").val();  // 선택된 별점 값
	    var movieno = movieno;  // 영화 번호 가져오기
	    var textArea = $("#textArea").val();  // 수정할 리뷰 내용
	    alert("관람평을 수정하셨습니다.");  // 알림 메시지

	    $.ajax({
	        url: "/movie/modifyReview",  // 서버로 보낼 URL
	        type: "POST",  // POST 방식으로 데이터 전송
	        data: { movieno: movieno, star: star, review: textArea },  // 전송할 데이터 (영화 번호, 별점, 리뷰)
	        headers: {
	            "X-CSRF-TOKEN": csrfToken  // CSRF 토큰 전송 (보안)
	        },
	        success: function(result) {
	            console.log("성공");
//	            closePop();  // 팝업 닫기
				$('.pop.write').css("display", "none");  // 팝업 닫기
	            // 리뷰 목록 새로고침 (현재 페이지에서)
	            loadUserReviews(currentPage, 4);  // 'currentPage'와 페이지 크기 4로 리뷰 목록 새로 불러오기
	        },
	        error: function() {
	            console.log("ajax 오류");  // 에러 메시지
	        }
	    });
	}
	
	
  	// 삭제 팝업을 열기 위한 클릭 이벤트
  	$(document).on('click', '.delete-review', function() {
//  	    var movieno = $(this).data('movieno');
  	    // 팝업을 열기
  	    $(this).closest('.commentDetail').find('.pop.delete').css({"display":"flex"});
  	    
  	    // 삭제 버튼 클릭 시 movieno를 삭제 함수에 넘겨서 처리
//  	    $('.pop.delete .button.delete').attr('onclick', 'deleteReview(' + movieno + ');');
  	});
   		
	$(document).on('click', '.close-pop', function() {
		closePop();
	});		

	
	// 삭제 버튼에 대한 이벤트 핸들러 설정
	  $(document).on('click', '.button.delete', function() {
	      // data-movieno 속성에서 영화 번호를 가져옴
	      var movieno = $(this).data('movieno');
	      
	      // modifyReview 함수 호출
	      deleteReview(movieno);
	  });	
	   	

	  	
	  	

	

	  	// 리뷰 삭제 함수
	  	function deleteReview(movieno) {
	  	    const csrfToken = document.getElementById('token').value;
	  	    alert("관람평이 삭제되었습니다.");
	  	    
	  	    $.ajax({
	  	        url: "/movie/deleteReview",
	  	        type: "POST",
	  	        data: {movieno: movieno},
	  	        headers: {
	  	            "X-CSRF-TOKEN": csrfToken
	  	        },
	  	        success:function(result) {
	  	            console.log("성공");
	  	            $('.pop.delete').css("display", "none");  // 팝업 닫기
	  	            loadUserReviews(currentPage, 4);  // 리뷰 목록 새로 불러오기
	  	        },
	  	        error:function() {
	  	            console.log("ajax 오류");
	  	        }
	  	    });
	  	}	
	  		
	  	// 팝업 닫기
	  	function closePop() 
	  	{
	  		var star = $("input[name='rating']:checked").val();
	  		var textArea = $("#textArea").val();
	  		
	  		$('.pop').css({"display":"none"});
	  		$("#textArea").val("");
	  		$("input[name='rating']:checked").prop('checked', false);	
	  			
	  		let stars = document.querySelectorAll('.star-icon.one.filled');
	  		let result = document.getElementById('selected-rating');
	  		
	  		stars.forEach((s, i) =>
	  		{
	          	s.classList.remove('filled');  // 선택되지 않은 별의 filled 클래스 제거
	  		});
	  				
	  		result.textContent = "";	
	  	}	
	  
	  
		
	  
	  
	  
	  
	  
	  
	  

	function loadlikedMovies(page, pageSize) {
	  	  // AJAX 요청
	      $.ajax({
	          url: '/loadlikedMovies',  // 서버의 엔드포인트
	          type: 'GET',
	          data: { page: page, pageSize: pageSize },  // 데이터를 전송
			  success: function(response) {
	            var content = '';
	            var movieList = response.movieList;  // 목록 데이터
	            var totalCount = response.totalCount;    // 전체 데이터 개수
	            totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
	            
			  console.log('movieList'+movieList);
			  console.log('totalCount:'+totalCount);
	            if (movieList.length === 0 || movieList === null) {
	                content = '<div class="noContent">조회된 목록이 없습니다.</div>';
	            } else {
	                $.each(movieList, function(index, dto) {
						content += '<div class="detailCon">';
						content += '<div class="movieDetail">';
						content += '<img src="'+dto.moviepostimg+'" alt="'+dto.movienm+'">';
						content += '<input type="hidden" id="movieno" value="'+dto.movieno+'}">';
						content += '<div class="infoCon">';
						content += '<div class="movieConM">';
						content += '<div class="movieTitle">' + dto.movienm + '</div>';
						content += '</div>';
						content += '<div class="movieConB">';
						content += '<div class="scrapCon">';
						content += '<span class="iconHeart">';
						content += '<i class="fa-regular fa-heart"></i>';
						content += '</span>';
						content += '<div class="scrapRate">'+dto.scrapRate+'</div>';
						content += '</div>';
						content += '<button class="bookingBtn" type="submit">예매하기</button>';
						content += '</div>';
						content += '</div>';
						content += '</div>';
						content += '</div>';
						
						document.getElementById('likedmovieList').innerHTML += content;
	                });
	            }
	            $('#likedmovieList').html(content);  // 데이터를 화면에 업데이트
				createPagination("wanted", totalCount, pageSize);  // 페이징 버튼 생성

	              // 현재 페이지에 active 클래스 추가
	              $('.pageBtn[data-page="' + currentPage + '"]').addClass('active');
	          },
	          error: function(xhr, status, error) {
	              console.log('Error:', error);
	          }
	      });
	  }

	  
	  
	  

	  // 페이지 로드 함수 (공통 함수)
	  function loadPageData(tabType, page, pageSize) {
	      if (tabType === "watched") {
	          loadtWatchedMovies(page, pageSize);
	      } else if (tabType === "comment") {
	          loadUserReviews(page, pageSize);
	      } else {
	          loadlikedMovies(page, pageSize);
	      }
	  }

	  // 페이징 버튼 생성 함수
	  function createPagination(tabType, totalCount, pageSize) {
          console.log('createPagination 접근 tabType:', tabType);
          console.log('createPagination 접근 totalCount:', totalCount);
          console.log('createPagination 접근 pageSize:', pageSize);
		
	      totalPages = Math.ceil(totalCount / pageSize);  // 총 페이지 수 계산
          console.log('createPagination 접근 totalPages:', totalPages);
	      var startPage = Math.floor((currentPage - 1) / pageGroupSize) * pageGroupSize + 1;
	      var endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
	      
	      var pagination = '';

	      // 이전 페이지 그룹으로 이동하는 chevron (prev)
	      if (startPage > 1) {
	          pagination += '<span class="prev-btn" data-tab="'+tabType+'"><i class="fa-solid fa-chevron-left"></i></span>';
	      }
	      
	      // 페이지 버튼들
	      for (var i = startPage; i <= endPage; i++) {
	          pagination += '<button class="pageBtn" data-page="' + i + '" data-tab="'+tabType+'">' + i + '</button>';
	      }
	      
	      // 다음 페이지 그룹으로 이동하는 chevron (next)
	      if (endPage < totalPages) {
	          pagination += '<span class="next-btn" data-tab="'+tabType+'"><i class="fa-solid fa-chevron-right"></i></span>';
	      }
	      
	      $('#pagination').html(pagination);  // 동적으로 태그 추가
	  }

	  // 페이지 넘버 버튼 클릭
	  $(document).on('click', '.pageBtn', function() {
	      var page = $(this).data('page');  // 클릭한 페이지 번호
	      currentPage = page;  // 현재 페이지 업데이트
	      var tabType = $(this).data('tab');  // 클릭한 페이지의 tabType 
	      
	      loadPageData(tabType, currentPage, pageSize);  // 공통 함수 호출
	  });

	  // 이전 그룹으로 이동
	  $(document).on('click', '.prev-btn', function() {
	      const tabType = $(this).parents('.tabCon').data('tab');
	      var prevPage = Math.max(1, currentPage - pageGroupSize);  // 이전 페이지 계산
	      currentPage = prevPage;  // 현재 페이지 업데이트
	      
	      loadPageData(tabType, currentPage, pageSize);  // 공통 함수 호출
	  });

	  // 다음 그룹으로 이동
	  $(document).on('click', '.next-btn', function() {
	      const tabType = $(this).parents('.tabCon').data('tab');
	      var nextPage = Math.min(totalPages, currentPage + pageGroupSize);  // 다음 페이지 계산
	      currentPage = nextPage;  // 현재 페이지 업데이트
	      
	      loadPageData(tabType, currentPage, pageSize);  // 공통 함수 호출
	  });
	  
	  
	  
	 

});// document ready 끝