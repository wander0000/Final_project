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
		2024-09-04 서연주
		POPUP > 열기
	*/
	$('.modifyBtn').click(function() {
	    console.log("click");
	    // 버튼의 부모 요소 중 .infoDetail을 찾고, 그 안의 .popUp을 선택하여 보여줌
	    $(this).closest('.infoDetail').find('.popUp').css({"display":"flex"}); 
		// 팝업이 열릴 때 입력 필드 초기화
	    $(this).closest('.infoDetail').find('.replace').val('');
	});

	
	
	/*
		2024-09-04 서연주
		POPUP > 닫기
	*/
	$('.popUp .icon.cancel').click(function(){
		console.log("click");
		$(this).parents('.popUp').css({"display":"none"}); 
	}); 

	$('button.cancel').click(function(){
		console.log("click");
		$(this).parents('.popUp').css({"display":"none"}); 
	}); 


	/*
		2024-09-04 서연주
		회원정보 변경(수정버튼 누르면 변경한 값을 가지고 와서 실행)
	*/
	  function updateInfo(type, url, inputId, csrfTokenId) {
	      event.preventDefault(); // 폼 제출 기본 동작을 막음
	      
	      const newValue = document.getElementById(inputId).value; // 입력값 가져오기
	      const csrfToken = document.getElementById(csrfTokenId).value; // CSRF 토큰 가져오기
	      console.log("csrfToken:" + csrfToken);

	      // 인증 토큰이 필요하면 헤더에 추가하여 fetch 요청
	      fetch(url, {
	          method: "PATCH",
	          headers: {
	              "Content-Type": "application/json",
	              "X-CSRF-TOKEN": csrfToken
	          },
	          body: JSON.stringify({ [type]: newValue }) // JSON 형식으로 전송
	      })
	      .then(response => {
	          if (response.ok) {
	              return response.text();
	          } else {
	              throw new Error(`${type} 변경에 실패했습니다.`);
	          }
	      })
	      .then(data => {
	          alert(data); // 성공 메시지 표시
			  // 해당 infoDetail의 td 태그 내용 업데이트
		     if (type === 'ppass') {
		         // 비밀번호의 경우 비밀번호를 숨김
		         $(`#${inputId}`).closest('.infoDetail').find('.td').text(''); // 비밀번호는 표시안함
		     } else {
		         // 비밀번호가 아닌 경우에는 실제 값을 업데이트
		         $(`#${inputId}`).closest('.infoDetail').find('.td').text(newValue);
		     }
	          // 팝업창 닫기
	          $(`#${inputId}`).closest('.popUp').css({ "display": "none" });
	      })
	      .catch(error => {
	          console.error("에러 발생:", error);
	          alert(error.message);
	      });
	  }

	  // 이메일 변경 버튼 > 이메일은 변경 불가하도록 변경
//	  $('#emailForm .updateBtn').click(function () {
//	      updateInfo('email', '/api/user/email', 'email', 'token');
//	  });

	  // 휴대폰번호 변경 버튼
	  $('#phoneForm .updateBtn').click(function () {
		event.preventDefault(); // 폼 제출 기본 동작을 막음
		console.log("휴대폰 수정버튼 클릭")
					
		//휴대폰 유효성 검사에 통과했으면 valid
		if (valid && $.trim($('#phone').val()) !== "") {
			updateInfo('phone', '/api/user/phone', 'phone', 'token');
			
		} else {
			alert("생년월일 값을 형식에 맞춰 입력해주세요.");
			$('#phone').focus();
			return; // 함수 종료
		}
	  });
	
	  // 비밀번호 변경 버튼
	  $('#ppassForm .updateBtn').click(function () {
			event.preventDefault(); // 폼 제출 기본 동작을 막음

		   const password = $('#ppass').val();
		   const passwordCheck = $('#ppass_check').val();

		   // 비밀번호와 비밀번호 확인 필드가 같은지 확인
		   if (password !== passwordCheck) {
		       alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요.");
		   } else {
		       // 비밀번호가 일치하면 updateInfo 호출
		       updateInfo('ppass', '/api/user/password', 'ppass', 'token');
		   }
	  });
	  
	  
		var valid = false; // 유효성 검사 통과 여부 저장하는 변수
		// 생년월일 유효성 검사
		$('#birth').on('input', function (e) {//입력필드에 입력할 때 마다
			
			// 생년월일 입력 필드에 하이픈 자동 추가
	  	    let input = e.target.value;
	  	    
	  	    // 숫자만 남기기
	  	    input = input.replace(/\D/g, '');

	  	    // 하이픈 추가
	  	    if (input.length > 4 && input.length <= 6) {
	  	        input = input.replace(/(\d{4})(\d+)/, '$1-$2');
	  	    } else if (input.length > 6) {
	  	        input = input.replace(/(\d{4})(\d{2})(\d+)/, '$1-$2-$3');
	  	    }
				  	    
	  	    e.target.value = input;//자동으로 하이픈 넣은 값
			
			var dateStr = input;//현재 입력되고 있는 값
		
			// 정규식을 이용하여 YYYY-MM-DD 형식과 두 자리 월/일 검사
			var dateFormat = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
		
			// 날짜가 올바른 형식인지 확인
			if (dateFormat.test(dateStr)) {
				var year = Number(dateStr.substr(0, 4)); // 입력한 값의 0~4자리까지 (연)
				var month = Number(dateStr.substr(5, 2)); // 입력한 값의 5번째 자리부터 2자리 숫자 (월)
				var day = Number(dateStr.substr(8, 2)); // 입력한 값 9번째 자리부터 2자리 숫자 (일)
				var today = new Date(); // 날짜 변수 선언
				var yearNow = today.getFullYear(); // 올해 연도 가져옴
		
				// 연도의 경우 1900보다 작거나 yearNow보다 크다면 false를 반환합니다.
				if (1900 > year || year > yearNow) {
					$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
					$('#birth_check').css({ "display": "flex" });
					valid = false;
				} else if (month < 1 || month > 12) {
					$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
//					$('#birth_check').css({ "display": "flex", "color":"#9b4e4e", "padding":"5px 20px" });
					$('#birth_check').css({ "display": "flex" });
					valid = false;
				} else if (day < 1 || day > 31) {
					$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
					$('#birth_check').css({ "display": "flex" });
					$('#user_birthdate').focus;
					valid = false;
				} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
					$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
					$('#birth_check').css({ "display": "flex" });
					valid = false;
				} else if (month == 2) { // 윤달 확인
					var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
					if (day > 29 || (day == 29 && !isleap)) {
						$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
						$('#birth_check').css({ "display": "flex" });
						valid = false;
					} else {
						valid = true;
						$('#birth_check').css('display', 'none');
					}
				} else {
					// $('#birth_check').text('');
					valid = true;
					$('#birth_check').css('display', 'none');
				}
			} else {
				// 형식이 맞지 않거나 두 자리 수가 아닐 때
				$('#birth_check').text('생년월일을 1900-01-01형식으로 입력하세요');
				$('#birth_check').css({ "display": "flex" });
				valid = false;
			}
		
		}); //생년월일 유효성 검사 끝	

	
	  // 생년월일 변경 버튼
	  $('#birthForm .updateBtn').click(function () {
			event.preventDefault(); // 폼 제출 기본 동작을 막음
			console.log("생년월일수정버튼 클릭")
			
			//생년월일 유효성 검사에 통과했으면 valid
			if (valid && $.trim($('#birth').val()) !== "") {
	     		updateInfo('birth', '/api/user/birth', 'birth', 'token');
				
			} else {
				alert("생년월일 값을 형식에 맞춰 입력해주세요.");
				$('#birth').focus();
				return; // 함수 종료
			}
		
	  });
	  
	  // 환불계좌 변경 버튼
	  $('#accountForm .updateBtn').click(function () {
	      updateInfo('account', '/api/user/account', 'account', 'token');
	  });
	  
	  
	  
	  
	  // 휴대폰 번호 입력 필드에 하이픈 자동 추가, 휴대폰번호 유효성검사
	     document.getElementById('phone').addEventListener('input', function(e) {
	         let input = e.target.value;
	         
	         // 숫자만 남기기
	         input = input.replace(/\D/g, '');
	         
	         // 하이픈 추가
	         if (input.length > 6) {
	             input = input.replace(/(\d{3})(\d{4})(\d+)/, '$1-$2-$3');
	         } else if (input.length > 3) {
	             input = input.replace(/(\d{3})(\d+)/, '$1-$2');
	         }
	         
	         e.target.value = input;
			 // 휴대전화 유효성 검사 
			var dateStr = input;
			console.log(dateStr);

			// 정규식을 이용하여 YYYY-MM-DD 형식과 두 자리 월/일 검사
			var dateFormat = /^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/;

			if (dateFormat.test(dateStr)) {   //올바른 형식인지 확인
				valid = true;
				$('#tel_check').css('display', 'none'); // 유효하면 메시지를 숨김
			} else {
				$('#tel_check').text('휴대폰번호를 010-1111-2222 형식으로 입력해주세요.');
				$('#tel_check').css({ "display": "flex"});
				valid = false;
			}
					
	     });
		 
		 
	 
		// 선택장르 수정 버튼
		$('#genreForm .updateBtn').click(function () {
			event.preventDefault(); // 폼 제출 기본 동작을 막음
			console.log("선택장르 수정버튼 클릭")
				
			var selectedGenres = $('input[name="genres"]:checked').length;
			
			// 3개 미만일 경우 경고 메시지를 띄우고 폼 제출을 막음
			if (selectedGenres < 3) {
				alert('최소 3개의 장르를 선택해 주세요.');
				event.preventDefault(); // 폼 제출 방지
			} else {
				updateSelectedGenres();//input창 값업데이트 하기
				$(this).parents('.popUp').css({"display":"none"}); 
//				updateInfo('genre', '/api/user/genre', 'genre', 'token');//fetch 실행
			}
		          
		});
			
		
		//선택된 장르 글자수 표시 제한
		$('#selectedGenresText').each(function() {
	        var length = 25; //표시할 글자 수 정하기
	    
	        $(this).each(function()
	        {
	            if($(this).text().length >= length)
	            {
	                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
	            }
	        });
	    });
		 
		 // 선택된 장르 업데이트
	    function updateSelectedGenres() {
	        // 선택된 체크박스들
	        let selectedGenres = [];
	        let selectedGenreTexts = [];

	        $('.genrebut:checked').each(function() {
	            selectedGenres.push($(this).val()); // value 값 가져오기
	            selectedGenreTexts.push($(`label[for="${$(this).attr('id')}"]`).text()); // label 텍스트 가져오기
	        });

	        // 콤마로 연결된 문자열로 변환
	        let genreTextString = selectedGenreTexts.join(', ');//장르이름배열(화면에 표시)
	        let genreValueString = selectedGenres.join(',');//장르번호배열(서버단에 넘기는 값)

	        // div에 텍스트 실시간 업데이트(글자수 제한:나머지 ...표시)
			
	        $('#selectedGenresText').text(genreTextString);
		    $('#selectedGenresText').each(function() {
		        var length = 25; //표시할 글자 수 정하기
		    
		        $(this).each(function()
		        {
		            if($(this).text().length >= length)
		            {
		                $(this).text($(this).text().substr(0, length) + '...');	//지정한 글자수 이후 표시할 텍스트 '...'
		            }
		        });
		    });
			
			// AJAX 요청 (fetch 사용)
			fetch('/api/user/genre', {
			          method: "POST",
			          headers: {
			              "Content-Type": "application/json",
			              "X-CSRF-TOKEN": $('#token').val()
			          },
			          body: JSON.stringify({ genre: genreValueString }) // JSON 형식으로 전송
			      })
			      .then(response => {
			          if (response.ok) {
			              return response.text();
			          } else {
			              throw new Error(`선호장르 변경에 실패했습니다.`);
			          }
			      })
			      .then(data => {
			          alert(data); // 성공 메시지 표시
			          // 팝업창 닫기
			         $(this).parents('.popUp').css({"display":"none"}); 
			      })
			      .catch(error => {
			          console.error("에러 발생:", error);
			          alert(error.message);
			      });
			
			
	    }
		 
	     
	     

});// document ready 끝