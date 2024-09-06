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
	$.ajax({
		type: 'post',
		data: {areano: areano, theaterno: theaterno, movieno: movieno, viewday: 'no'},
		url: '/ticketing/dateshow',
		headers: {
			'X-CSRF-TOKEN': getCsrfToken() // CSRF 토큰 추가
		},
		success: function(result) {
			console.log(result);
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

function seatFrom(areano, theaterno, movieno, viewday, roomno, starttime, pricetype) {
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
				window.location.href = "/ticketing/seatselect";
            },
            error: function(xhr, status, error) {
                // 오류가 발생했을 때 처리할 내용
                console.error("AJAX 요청 실패:", status, error);
            }
        });
    }
}

function down(id) {
    var button = document.getElementById(id);
    
	if(button.textContent == 0) {
		return false;
	}
	
	minus(); //총 인원 -= 1
	setCnt(getTotal()); //선택 가능 좌석 업데이트
	resetSit();
	$("#calc").text("총 합계 0원");
	
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
	setCnt(getTotal()); //선택 가능 좌석 업데이트
	resetSit();
	$("#calc").text("총 합계 0원");
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
let arr = [];
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
function resetSit() {
	//좌석 초기화//
	var seat = $(".sit");
	seat.removeClass('select'); // css 클래스 삭제
	seat.removeClass('unselect');
	arr = []; //배열 초기화
	$("#next").val('N');
	//좌석 초기화//
}

function updateSeatSelection() {
    var seats = $(".sit");
    seats.each(function() {
        var $this = $(this);
        if (!$this.hasClass('select')) {
            $this.addClass('unselect');
        }
    });
}

function updateSeat() {
	var seats = $(".sit");
	seats.each(function() {
        var $this = $(this);
        if ($this.hasClass('unselect')) {
            $this.removeClass('unselect');
        }
    });
}

function select_seat(id) {
	if(persons == 0) {
		alert('인원을 먼저 선택하세요.');
		return false;
	}
	
	var alink = $("#"+id);
	
	// 이미 선택된 좌석인지 확인
    if (arr.includes(id)) {
        // 선택 해제 (좌석을 이미 선택했을 경우)
        alink.removeClass('select');
        arr = arr.filter(seatId => seatId !== id); // 배열에서 좌석 제거
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
	
	arr.push(id);
}

function calc() {
	var price = $("#price").val()
	var money = persons * price;
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
		/* 각 인원 숫자 */
		var adult = $("#adult").data('count');
		var youth = $("#youth").data('count');
		var old = $("#old").data('count');
		var disable = $("#disable").data('count');
		/* 각 인원 숫자 */
		
		$.ajax({
            type: 'POST', // 요청 방식
            url: '/ticketing/saveSessionParamsMore', // 요청을 보낼 URL
            data: { calc: calc, adult: adult, youth: youth, old: old, disable: disable }, // 전송할 데이터
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