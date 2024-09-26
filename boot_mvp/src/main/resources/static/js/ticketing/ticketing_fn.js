function getCsrfToken() {
    return $("#csrf").val();
}

//천 단위 콤마 찍기
function comma(num) {
   var len, point, str;

   num = num + "";
   point = num.length % 3 ;
   len = num.length;

   str = num.substring(0, point);
   while (point < len) {
      if (str != "" && str != "-") str += ",";
      str += num.substring(point, point + 3);
      point += 3;
   }

   return str;
}

function area_event(areano) {
	// 클릭된 a 태그의 부모 div 요소 찾기
	var parentDiv = event.target.closest('.areascroll .Div_tab');
	// 모든 .Div_tab 요소에서 active 클래스 제거
	var allDivs = document.querySelectorAll('.areascroll .Div_tab');
	var alink = document.getElementById('showtitle');
	$.ajax({
		type: 'post',
		data: {areano: areano},
		url: '/ticketing/theatershow',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			document.getElementById('theaterscroll').scrollTop = 0;
            
            allDivs.forEach(function(div) {
                div.classList.remove('active');
            });

            // 클릭된 a 태그의 부모 div에 active 클래스 추가
            if (parentDiv) {
                parentDiv.classList.add('active');
            }
			alink.textContent = "영화 선택";
			$("#theaterscroll").html(result);
		}, error: function(status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function theaterevent(theaterno, theaternm) {
	// 클릭된 a 태그의 부모 div 요소 찾기
	var parentDiv = event.target.closest('.theaterscroll .Div_tab');
	// 모든 .Div_tab 요소에서 active 클래스 제거
	var allDivs = document.querySelectorAll('.theaterscroll .Div_tab');
	var alink = document.getElementById('showtheater');
	var alink2 = document.getElementById('showtitle');
	var areano = $("#areano_t").val();
	$.ajax({
		type: 'post',
		data: {areano: areano, theaterno: theaterno},
		url: '/ticketing/movieshow',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			document.getElementById('theaterscroll').scrollTop = 0;
            
            allDivs.forEach(function(div) {
                div.classList.remove('active');
            });

            // 클릭된 a 태그의 부모 div에 active 클래스 추가
            if (parentDiv) {
                parentDiv.classList.add('active');
            }
			alink.textContent = theaternm;
			alink2.textContent = "영화 선택";
			
			$("#moviebox").html(result);
			
			$.ajax({
			});
		}, error: function(status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function movieevent(movieno, movienm) {
	// 클릭된 a 태그의 부모 div 요소 찾기
	var parentDiv = event.target.closest('.moviebox .boxtit');
	// 모든 .Div_tab 요소에서 active 클래스 제거
	var allDivs = document.querySelectorAll('.moviebox .boxtit');
	var alink = document.getElementById('showtitle');
	var alink2 = document.getElementById('showdate');
	var areano = $("#areano_m").val();
	var theaterno = $("#theaterno_m").val();
	var viewday = $("#viewday_m").val() == '' ? 'no':$("#viewday_m").val();
	$.ajax({
		type: 'post',
		data: {areano: areano, theaterno: theaterno, movieno: movieno, viewday: viewday},
		url: '/ticketing/dateshow',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			allDivs.forEach(function(div) {
		        div.classList.remove('active');
		    });
		
		    // 클릭된 a 태그의 부모 div에 active 클래스 추가
		    if (parentDiv) {
		        parentDiv.classList.add('active');
		    }
			alink.textContent = movienm;
			$("#areano_date").val(areano);
			$("#theaterno_date").val(theaterno);
			$("#movieno_date").val(movieno);
			
			$.ajax({
				type:'post',
				url: '/ticketing/datetxt',
				headers: {
					'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
				},
				data: { viewday: viewday },
				success: function(res) {
					alink2.textContent = res.date;
				}, error: function(status, error) {
					console.error("Ajax 요청 실패:", status, error);
				}
			});
			
			$("#groupbox").html(result);
		}, error: function(status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function date_event(viewday) {
	var areano = $("#areano_date").val();
	var theaterno = $("#theaterno_date").val();
	var movieno = $("#movieno_date").val();
	var alink = document.getElementById('showdate');
	$.ajax({
		type: 'post',
		data: {areano: areano, theaterno: theaterno, movieno: movieno, viewday: viewday},
		url: '/ticketing/dateshow',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			$.ajax({
				type:'post',
				url: '/ticketing/datetxtparam',
				data: {viewday: viewday},
				headers: {
					'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
				},
				success: function(res) {
					alink.textContent = res.date;
					$.ajax({
						type: 'post',
						url: '/ticketing/movieshow',
						data: {areano: areano, theaterno: theaterno, movieno: movieno, viewday: viewday},
						headers: {
							'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
						}, success: function(result) {
							/*
							allDivs.forEach(function(div) {
						        div.classList.remove('active');
						    });
							
						    // 클릭된 a 태그의 부모 div에 active 클래스 추가
						    
							if (parentDiv) {
						        parentDiv.classList.add('active');
						    }
							*/
							//alink.textContent = movienm;
							$("#areano_date").val(areano);
							$("#theaterno_date").val(theaterno);
							$("#movieno_date").val(movieno);
							
							$("#moviebox").html(result);
							$("#selected_" + movieno).parent().addClass('active');
							//$("#selected_"+movieno).addClass('active');
						}, error: function(status, error) {
							console.error("Ajax 요청 실패:", status, error);
						}
					});
				}, error: function(status, error) {
					console.error("Ajax 요청 실패:", status, error);
				}
			});
			$("#groupbox").html(result);
		}, error: function(status, error) {
			console.error("Ajax 요청 실패:", status, error);
		}
	});
}

function checkLoginStatus(callback) {
    $.ajax({
        type: 'get',
        url: '/ticketing/logincheck',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		dataType: 'json', // 응답이 JSON 형식일 것으로 기대		
        success: function(isLoggedIn) {
            callback(isLoggedIn);
        },
        error: function(xhr, status, error) {
            console.error("로그인 상태 확인 실패:", status, error);
            callback(false);
        }
    });
}

function seatFrom(areano, theaterno, movieno, viewday, roomno, starttime, pricetype) {
	var dateString = starttime;
	// Date 객체로 변환
	var targetDate = new Date(dateString);

	// 현재 날짜와 시간 가져오기
	var currentDate = new Date();
	
	if(targetDate > currentDate) {
	    if (confirm("해당 시간으로 하시겠습니까?")) {
	        $.ajax({
	            type: 'POST', // 요청 방식
	            url: '/ticketing/saveSessionParams', // 요청을 보낼 URL
	            data: { areano: areano, theaterno: theaterno, movieno: movieno, viewday: viewday, roomno: roomno, starttime: starttime, pricetype: pricetype }, // 전송할 데이터
				headers: {
					'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
				},
	            success: function(response) {
	                console.log('서버 응답:', response);
					// 세션에 먼저 저장 후 로그인 여부 확인
					checkLoginStatus(function(isLoggedIn) {
						if(isLoggedIn) {
							window.location.href = "/ticketing/seatselect";
						} else {
							alert("로그인이 필요합니다.\n로그인 화면으로 이동합니다.");
							var currentUrl = "/ticketing/seatselect";
			                window.location.href = "/login?redirect=" + encodeURIComponent(currentUrl);
						}
					});
	            },
	            error: function(xhr, status, error) {
	                // 오류가 발생했을 때 처리할 내용
	                console.error("AJAX 요청 실패:", status, error);
	            }
	        });
	    }
	} else {
		alert('선택한 상영 시간의 영화는 현재 선택이 불가능합니다.\n다른 상영시간을 선택하세요.');
		
		var childDiv = document.getElementById('selected_' + movieno);
		if (childDiv) {
	         var parentDiv = childDiv.closest('.boxtit'); // 가장 가까운 div 요소 찾기
			 if (parentDiv) {
 		        parentDiv.classList.add('active');
 		    }
	    }
	}
}

function down(id) {
    var button = document.getElementById(id);
    
	if(button.textContent == 0) {
		return false;
	}
	
	minus(); //총 인원 -= 1
	settype(id, 'down'); //타입별 인원 -=1;
	setCnt(getTotal()); //선택 가능 좌석 업데이트
	resetSit();
	
	if(selecttype[3] == 0)
		make_disabled(); //장애인석 표시(빨간 영역) 클래스 추가
	else if(selecttype[3] > 0)
		show_disabled(); ////장애인석 표시(초록색 영역) 클래스 추가
	
	default_calc(); //계산한 합계 초기화
	
    // data-count 값을 가져옵니다.
    var countValue = parseInt(button.getAttribute('data-count'), 10);
    var newCountValue = countValue - 1;
    
    // data-count 값을 업데이트합니다.
    button.setAttribute('data-count', newCountValue);
    
    // 버튼의 텍스트를 업데이트합니다.
    button.textContent = newCountValue;
}

function up(id) {
    var button = document.getElementById(id);
	if(getTotal() >= 8) {
		alert('인원은 최대 8명까지 선택 가능합니다.');
		return false;
	}
    plus(); //전체 인원 += 1
	settype(id, 'up'); //타입별 인원 +=1;
	setCnt(getTotal()); //선택 가능 좌석 업데이트
	resetSit(); //좌석 상태 초기화

	if(selecttype[3] == 0) {
		make_disabled(); //장애인석 표시(빨간 영역) 클래스 추가
	} else if(selecttype[3] > 0) {
		remove_disabled(); //장애인 전용(빨간색) 좌석 클래스 제거 
		show_disabled(); //장애인석 표시(초록색 영역) 클래스 추가
	}
	
	default_calc(); //계산한 합계 초기화
	
    // data-count 값을 가져옵니다.
    var countValue = parseInt(button.getAttribute('data-count'), 10);
    var newCountValue = countValue + 1;
    
    // data-count 값을 업데이트합니다.
    button.setAttribute('data-count', newCountValue);
    
    // 버튼의 텍스트를 업데이트합니다.
    button.textContent = newCountValue;
}

// 좌석 선택에 사용할 전역 변수
let persons = 0;
let seatcnt = 0;
let selectseat = [];
let disabled = ['A1','A2','A3','A4'];
let selecttype = [0,0,0,0]; //타입별 좌석 인원 //성인, 청소년, 경로 장애인
// 좌석 선택에 사용할 전역 변수
function plus() {
	persons += 1;
}
function minus() {
	persons -= 1;
}
function getTotal() {
	return persons;
}
function select() {
    seatcnt -= 1;
    if (seatcnt < 0) seatcnt = 0;
}
function unselect() {
	seatcnt += 1;
	if(seatcnt > persons) seatcnt = persons;
}
function setCnt(value) {
	seatcnt = value;
}
function getCnt() {
	return seatcnt;
}
function settype(id, gubun) {
	var num = 0;
	switch(id) {
		case 'adult':
			num = 0;
			break;
		case 'youth':
			num = 1;
			break;
		case 'old':
			num = 2;
			break;
		case 'disable':
			num = 3;
			break;
	}
	
	if(gubun == 'up') {
		selecttype[num] += 1;
	} else {
		selecttype[num] -= 1;
	}
}

/* 장애인 석 관련 클래스 처리 로직 */
function make_disabled() { //장애인 전용(빨간색) 좌석 클래스 추가
	for(var i = 0; i < disabled.length; i++) {
		$("#"+disabled[i]).addClass("disabled");
		$("#"+disabled[i]).removeClass("showdisabled");
	}
}
function remove_disabled() { //장애인 전용(빨간색) 좌석 클래스 제거
	for(var i = 0; i < disabled.length; i++) {
		$("#"+disabled[i]).removeClass("disabled");
	}
}
function show_disabled() { //장애인 전용(초록색) 좌석 클래스 추가 
	for(var i = 0; i < disabled.length; i++) {
		$("#"+disabled[i]).addClass("showdisabled");
	}
}
function show_disabled_remove() { //장애인 전용(초록색) 좌석 클래스 제거
	for(var i = 0; i < disabled.length; i++) {
		$("#"+disabled[i]).removeClass("showdisabled");
	}
}
/* 장애인 석 관련 클래스 처리 로직 */
function resetSit() {
	//좌석 초기화//
	var seat = $(".sit");
	seat.removeClass('select'); // css 클래스 삭제
	seat.removeClass("diagonal-background"); //seat.removeClass('unselect');
	
	
	if(select_seat[3] == 0)
		make_disabled(); // 장애인석 표시(빨간 영역) 클래스 추가
	
	selectseat = []; //배열 초기화
	$("#next").val('N');
	//좌석 초기화//
}

function updateSeatSelection() { //모든 좌석 선택 시 다른 좌석 선택 막음
    var seats = $(".sit");
	remove_disabled(); //장애인석 표시(빨간 영역) 클래스 제거
	show_disabled_remove(); //활성화된 장애인석 표시 제거
    seats.each(function() {
        var $this = $(this);
        if (!$this.hasClass('select')) {
            $this.addClass('diagonal-background'); //$this.addClass('unselect');
        }
    });
}

function updateSeat() { // 선택 좌석 취소 시, 다시 활성화
	var seats = $(".sit");
	
	seats.each(function() {
        var $this = $(this);
		/*
        if ($this.hasClass('unselect')) {
            $this.removeClass('unselect');
        }
		*/
		if ($this.hasClass('diagonal-background')) {
            $this.removeClass('diagonal-background');
        }
    });

	if(selecttype[3] == 0)
		make_disabled(); //장애인석 표시(빨간 영역) 클래스 추가
	else if(selecttype[3] > 0)
		show_disabled(); ////장애인석 표시(초록색 영역) 클래스 추가
}

function select_seat(id) {
	if(persons == 0) {
		alert('인원을 먼저 선택하세요.');
		return false;
	}
	
	var alink = $("#"+id);
	
	// 이미 선택된 좌석인지 확인
    if (selectseat.includes(id)) {
        // 선택 해제 (좌석을 이미 선택했을 경우)
        alink.removeClass('select');
        selectseat = selectseat.filter(seatId => seatId !== id); // 배열에서 좌석 제거
        unselect(); // 선택 가능 좌석 수 증가
        updateSeat(); // 선택 가능 좌석 상태 업데이트
        return;
    }
	
	alink.addClass('select');
		
	select(); //선택 가능 좌석 -= 1
	
	if(getCnt() == 0) {
		updateSeatSelection(); //남은 좌석 선택 불가로 변경
		calc(); //금액 계산
	}
	
	selectseat.push(id);
}

function default_calc() {
	$("#calcshow").text("총 합계 0원");
}

function calc() {
	var price0 = $("#price0").val();
	var price1 = $("#price1").val();
	var price2 = $("#price2").val();
	var price3 = $("#price3").val();
	
	// 변경된 갑을 읽어야 하기에 .data('count')가 아닌 attr('data-count')로 직접 접근하여 값을 가져옴
	var persons0 = $("#adult").attr('data-count'); //$("#adult").data('count');
	var persons1= $("#youth").attr('data-count'); //$("#youth").data('count');
	var persons2 = $("#old").attr('data-count'); //$("#old").data('count');
	var persons3 = $("#disable").attr('data-count'); //$("#disable").data('count');
	
	// 가격과 인원 수 배열
	var prices = [price0, price1, price2, price3];
	var persons = [persons0, persons1, persons2, persons3];
	
	var money = 0;
	for(var i = 0; i < 4; i++) {
		money += prices[i] * persons[i];
	}
	
	$("#calc").val(money);
	$("#calcshow").text("총 합계 "+comma(money)+"원");
	$("#next").val('Y');
}

function payment() {
	var next = $("#next").val();
	if(next != 'Y') {
		alert('인원 및 좌석을 선택해주세요.');
		return false;
	} else {
		var calc = $("#calc").val();
		var pricetype = $("#pricetype").val();
		var seats = '';
		
		//선택한 좌석 정렬
		selectseat.sort(function(a, b) { 
		    var aNum = parseInt(a.replace(/[^\d]/g, '')); //숫자 추출
		    var bNum = parseInt(b.replace(/[^\d]/g, '')); //숫자 추출
		    var aChar = a.replace(/\d/g, ''); //문자 추출
		    var bChar = b.replace(/\d/g, ''); //문자 추출
		    
		    if (aChar === bChar) { //문자 비교
		        return aNum - bNum;
		    } else { // 숫자 비교
		        return aChar.localeCompare(bChar);
		    }
		}); 
		
		for(var i = 0; i < selectseat.length; i++) {
			seats += selectseat[i] + ',';
		}
		seats = seats.slice(0, -1);

		$.ajax({
            type: 'POST', // 요청 방식
            url: '/ticketing/saveSessionParamsMore', // 요청을 보낼 URL
            data: { calc: calc, pricetype: pricetype, adult: selecttype[0], youth: selecttype[1], old: selecttype[2], disable: selecttype[3], seats: seats }, // 전송할 데이터
			headers: {
				'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
			},
            success: function(response) {
                console.log('서버 응답:', response);
				window.location.href = "/ticketing/payment";
            },
            error: function(xhr, status, error) {
                // 오류가 발생했을 때 처리할 내용
                console.error("AJAX 요청 실패:", status, error);
            }
        });
    }
}

function move_form(actions) {
	document.paycomform.action = actions;
	paycomform.submit();
}