function getCsrfToken() {
    return $("#csrf").val();
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

function seatFrom(areano, theaterno, movieno, viewday, roomno, starttime) {
    if (confirm("해당 시간으로 하시겠습니까?")) {
        $.ajax({
            type: 'POST', // 요청 방식
            url: '/ticketing/saveSessionParams', // 요청을 보낼 URL
            data: { areano: areano, theaterno: theaterno, movieno: movieno, viewday: viewday, roomno: roomno, starttime: starttime }, // 전송할 데이터
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
	
}

function up(id) {
	var button = $("#"+id);
		
	var countValue = button.data('count');
	var newcount = countValue + 1;
	
	button.data('count', newcount);
	button.text($button.data('count'));
}