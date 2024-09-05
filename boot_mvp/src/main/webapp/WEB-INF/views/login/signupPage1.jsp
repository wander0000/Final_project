<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 1단계</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signupPage1.css">
    <!-- import font-awesome, line-awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css">
    <!-- import pretendard font -->
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.8/dist/web/variable/pretendardvariable.css"/>
    <!-- import js -->
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/signupPage1.js"></script>
  
</head>

<body>
    <div class="logimg">
        <div class="container"></div>
        <section>
            <div class="wrap">
                <div class="loginWrap">
                    <div class="logbox">
                        <div class="background-blur"></div>
                        <div class="content">
                            <div class="log1">
                                <h5 class="hh">회원가입 1단계</h5>
                            </div>
                            <form action="${pageContext.request.contextPath}/signup/step1" method="post">
                                <!-- CSRF 토큰 추가 -->
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
								<div class="log2">
								    <h5 class="nobo">*</h5>
								    <input type="text" name="pname" placeholder="이름" class="idpwbox3" required id="pname">
								</div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="email" name="email" placeholder="이메일 주소" class="idpwbox3" required id="emailInput">
                                    <a href="#" class="emaillink" id="checkEmail">중복확인</a>
                                </div>
								<div class="log3">
								    <h5 class="nobo">*</h5>
								    <input type="text" name="userid" placeholder="아이디" class="idpwbox3" required id="userIdInput">
								    <a href="#" class="emaillink" id="checkId">중복확인</a>
								</div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="password" id="ppass" name="ppass" placeholder="비밀번호" class="idpwbox3" required>
                                    <a href="#" class="pwllink" id="togglePassword">비밀번호보기</a>
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="password" id="ppassConfirm" name="ppassConfirm" placeholder="비밀번호 확인" class="idpwbox3" required>
                                    <a href="#" class="pwwllink" id="checkPasswordMatch">중복확인</a>
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="tel" name="phone" placeholder="휴대폰 번호 (010-1234-5678)" class="idpwbox3" required id="phoneInput" maxlength="13">
                                </div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" name="birth" placeholder="생년월일 (YYYY-MM-DD)" class="idpwbox3" required id="birthInput" maxlength="10">
                                </div>
                                <div class="gdbox1">
                                    <h5 class="gdgd11">*</h5>
                                    <h5 class="gdgd22">성별</h5>
                                    <div class="gdbox2">
                                        <button type="button" class="gender-button male-button" id="male" onclick="setGender('1')">남</button>
                                    </div>
                                    <div class="gdbox3">
                                        <button type="button" class="gender-button female-button" id="female" onclick="setGender('2')">여</button>
                                    </div>
                                    <input type="hidden" id="gender" name="gender" />
                                </div>
								<div class="agreebox">
								    <div class="agr11">
								        <input type="checkbox" name="chk1" id="check1" class="chk">
								        <label for="check1"></label>
								        <h5 class="agrh">전체약관에 동의합니다</h5>
								    </div>
								    <div class="agr1">
								        <input type="checkbox" name="chk2" id="check2" class="chk" required>
								        <label for="check2"></label>
								        <h5 class="agrh">(필수) 만 14세 이상입니다</h5>
								    </div>
								    <div class="agr1">
								        <input type="checkbox" name="chk3" id="check3" class="chk" required>
								        <label for="check3"></label>
								        <h5 class="agrh">(필수) 서비스 이용 약관</h5>
								        <a href="##" class="agrhh">보기</a>
								    </div>
								    <div class="agr1">
								        <input type="checkbox" name="chk4" id="check4" class="chk" required>
								        <label for="check4"></label>
								        <h5 class="agrh">(필수) 개인정보 수집 및 이용 동의</h5>
								        <a href="##" class="agrhh">보기</a>
								    </div>
								</div>
                                <div class="log4">
                                    <input type="submit" value="다음으로" class="logbut">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>


</html>
<script>
	
	function setGender(genderValue) {
	    document.getElementById('gender').value = genderValue;
	}
	
	
	
	
	function checkUserId(callback) {
	    var userId = $('#userIdInput').val(); // 아이디 입력 필드의 ID가 맞는지 확인

	    // 아이디 형식 검사 (옵션)
	    if (userId.length === 0) {
	        alert("아이디를 입력해 주세요.");
	        return false;
	    }

	    // CSRF 토큰 가져오기
	    var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

	    $.ajax({
	        url: "/checkUserId", // 서버에서 정의한 URL
	        type: "GET", // GET 요청으로 변경
	        data: { userid: userId }, // 파라미터 포함
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken); // CSRF 토큰을 헤더에 추가
	        },
	        success: function(response) {
	            console.log('서버 응답:', response); // 서버 응답 로그

	            if (response.userExists) {
	                alert("이미 사용 중인 아이디입니다.");
	                callback(false);
	            } else {
	                var confirmUsage = confirm("사용 가능한 아이디입니다. 사용하시겠습니까?");
	                if (confirmUsage) {
	                    $('#userIdInput').addClass('readonly'); // 클래스 추가
	                    $('#checkId').hide(); // 중복 확인 버튼 숨기기
	                    $('#userIdInput').prop('readonly', true); // 읽기 전용으로 설정
	                }
	                callback(true);
	            }
	        },
	        error: function() {
	            alert("서버 오류. 다시 시도해주세요.");
	            callback(false);
	        }
	    });
	}

	function checkEmail(callback) {
	    var email = $('#emailInput').val();

	    // 이메일 형식 검사
	    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	    if (!emailPattern.test(email)) {
	        alert("이메일 형식이 틀렸습니다.");
	        return false;
	    }

	    // CSRF 토큰 가져오기
	    var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

	    $.ajax({
	        url: "/email/check-email", // 서버에서 정의한 URL
	        type: "GET", // GET 요청으로 변경
	        data: { email: email }, // 파라미터 포함
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken); // CSRF 토큰을 헤더에 추가
	        },
	        success: function(response) {
	            if (response.emailExists) {
	                alert("이미 등록된 이메일입니다.");
	                callback(false);
	            } else {
	                var confirmUsage = confirm("사용 가능한 이메일입니다. 사용하시겠습니까?");
	                if (confirmUsage) {
	                    $('#emailInput').addClass('readonly'); // 클래스 추가
	                    $('#checkEmail').hide(); // 중복 확인 버튼 숨기기
	                    $('#emailInput').prop('readonly', true); // 읽기 전용으로 설정
	                }
	                callback(true);
	            }
	        },
	        error: function() {
	            alert("서버 오류. 다시 시도해주세요.");
	            callback(false);
	        }
	    });
	}





    // 비밀번호 보기/숨기기 로직
    $('#togglePassword').on('click', function(event) {
        event.preventDefault(); // 기본 링크 동작 방지
        
        var passwordField = $('#ppass');
        var isPasswordVisible = passwordField.attr('type') === 'text'; // 현재 비밀번호가 보이는지 확인

        if (isPasswordVisible) {
            passwordField.attr('type', 'password'); // 비밀번호 숨기기
            $(this).text('비밀번호보기'); // 버튼 텍스트 변경
        } else {
            passwordField.attr('type', 'text'); // 비밀번호 보이기
            $(this).text('비밀번호숨기기'); // 버튼 텍스트 변경
        }
    });

	// 비밀번호 확인 로직
	$('#checkPasswordMatch').on('click', function(event) {
	    event.preventDefault(); // 기본 링크 동작 방지
	    
	    var password = $('#ppass').val();
	    var confirmPassword = $('#ppassConfirm').val();
	    
	    if (password.length === 0 || confirmPassword.length === 0) {
	        alert('비밀번호를 입력해 주세요.');
	    } else if (password === confirmPassword) {
	        alert('비밀번호가 같습니다.');
	    } else {
	        alert('비밀번호가 같지 않습니다.');
	    }
	});

    $(document).ready(function() {
        $('#checkEmail').on('click', function(event) {
            event.preventDefault(); // 기본 링크 동작 방지
            checkEmail(function(){}); // 이메일 중복 체크만 수행
        });

        // 휴대폰 번호 입력 필드에 하이픈 자동 추가
        document.getElementById('phoneInput').addEventListener('input', function(e) {
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
        });

        // 생년월일 입력 필드에 하이픈 자동 추가
        document.getElementById('birthInput').addEventListener('input', function(e) {
            let input = e.target.value;

            // 숫자만 남기기
            input = input.replace(/\D/g, '');

            // 하이픈 추가
            if (input.length > 4 && input.length <= 6) {
                input = input.replace(/(\d{4})(\d+)/, '$1-$2');
            } else if (input.length > 6) {
                input = input.replace(/(\d{4})(\d{2})(\d+)/, '$1-$2-$3');
            }

            e.target.value = input;
        });
        
        // 이름 입력 필드에서 숫자 입력 방지
        $('#pname').on('input', function() {
            var input = $(this).val();
            // 숫자를 제거
            input = input.replace(/[0-9]/g, '');
            $(this).val(input);
        });

        $('#checkId').on('click', function(event) {
            event.preventDefault(); // 기본 링크 동작 방지
            checkUserId(function(){}); // 아이디 중복 체크만 수행
        });
		
		
		// 체크박스 로직
		$('#check1').on('change', function() {
		       var isChecked = $(this).is(':checked');
		       
		       // 체크박스 2, 3, 4 전체 체크/해제
		       $('#check2, #check3, #check4').prop('checked', isChecked);
		   });

		   $('form').on('submit', function(event) {
		       // 체크박스 2, 3, 4가 체크되지 않은 경우 알림
		       if (!$('#check2').is(':checked') || !$('#check3').is(':checked') || !$('#check4').is(':checked')) {
		           alert('모든 필수 체크박스를 체크해 주세요.');
		           event.preventDefault(); // 폼 제출 방지
		       }
		   });
		
    });
</script>

