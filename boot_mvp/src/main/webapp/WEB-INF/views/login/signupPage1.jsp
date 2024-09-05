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
								    <a href="#" class="emaillink" id="sendVerificationCode">전송</a>
								</div>
								<!-- 인증번호 입력 필드와 링크를 이메일 전송 후에만 보이도록 합니다 -->
								<div class="log3" id="verificationSection" style="display: none;">
								    <h5 class="nobo">*</h5>
								    <input type="text" name="verificationCode" placeholder="인증번호" class="idpwbox3" id="verificationCodeInput" required>
								    <a href="#" class="emaillink" id="verifyCodeLink">확인</a>
								</div>
                                <div class="log3">
                                    <h5 class="nobo">*</h5>
                                    <input type="text" name="userid" placeholder="아이디" class="idpwbox3" required>
                                    <a href="#" class="emaillink" id="checkDuplicateId">중복확인</a>
                                    <span id="idCheckResult" style="color: red; display: none;"></span>
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
                                        <input type="checkbox" name="chk1" id="check1" class="chk" required>
                                        <label for="check1"></label>
                                        <h5 class="agrh">전체약관에 동의합니다</h5>
                                    </div>
                                    <div class="agr1">
                                        <input type="checkbox" name="chk2" id="check2" class="chk" required>
                                        <label for="check2"></label>
                                        <h5 class="agrh">만 14세 이상입니다</h5>
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
    function setGender(value) {
        document.getElementById('gender').value = value;
    }

    // 아이디 중복 검사 기능
    function checkDuplicateId(callback) {
        var userid = $('input[name="userid"]').val();
        if (userid.trim() === "") {
            alert("아이디를 입력해주세요.");
            return false;
        }

        // CSRF 토큰 가져오기
        var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

        $.ajax({
            url: "${pageContext.request.contextPath}/checkUserId",
            type: "POST",
            data: { userid: userid },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("${_csrf.headerName}", csrfToken); // CSRF 토큰을 헤더에 추가
            },
            success: function(response) {
                var resultElement = $('#idCheckResult');
                if (response.exists) {
                    resultElement.text("중복된 아이디입니다.");
                    resultElement.css("color", "red");
                    resultElement.show();
                    $('input[name="userid"]').focus(); // 중복된 경우 아이디 필드로 커서 이동
                    callback(false);
                } else {
                    resultElement.text("사용 가능한 아이디입니다.");
                    resultElement.css("color", "green");
                    resultElement.show();
                    callback(true);
                }
            },
            error: function() {
                alert("서버 오류. 다시 시도해주세요.");
                callback(false);
            }
        });
    }

    // 인증번호 전송 후 필드와 버튼 표시
    function showVerificationSection() {
        $('#verificationSection').show(); // 인증번호 입력 필드와 버튼 표시
    }

    // 이메일 인증번호 전송
    $('#sendVerificationCode').on('click', function() {
        var email = $('#emailInput').val();

        if (email.trim() === "") {
            alert("이메일 주소를 입력해 주세요.");
            return;
        }

        var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

        $.ajax({
            url: "${pageContext.request.contextPath}/send-verification-code",
            type: "POST",
            data: { email: email },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("${_csrf.headerName}", csrfToken); // CSRF 토큰을 헤더에 추가
            },
            success: function(response) {
                alert("인증번호가 전송되었습니다."); // 이메일 전송 후 알림 표시
                showVerificationSection(); // 인증번호 입력 필드와 버튼 표시
            },
            error: function() {
                alert("서버 오류. 다시 시도해주세요.");
            }
        });
    });

    // 인증번호 확인
    $('#verifyCodeLink').on('click', function(event) {
        event.preventDefault(); // 기본 링크 동작 방지
        
        var email = $('#emailInput').val();
        var code = $('#verificationCodeInput').val();

        if (email.trim() === "" || code.trim() === "") {
            alert("이메일 주소와 인증번호를 입력해 주세요.");
            return;
        }

        var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

        $.ajax({
            url: "${pageContext.request.contextPath}/verify-code",
            type: "POST",
            data: { email: email, code: code },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("${_csrf.headerName}", csrfToken);
            },
            success: function(response) {
                if (response === "Verification successful") {
                    alert("인증번호가 확인되었습니다."); // 인증번호 확인 후 알림 표시
                    $('#verificationSection').hide(); // 인증번호 입력 필드와 버튼 숨기기
                    $('#emailInput').attr("disabled", true); // 이메일 입력 필드 비활성화
                    $('#sendVerificationCode').hide(); // 전송 버튼 숨기기
                } else {
                    alert("인증번호 확인에 실패했습니다.");
                }
            },
            error: function() {
                alert("서버 오류. 다시 시도해주세요.");
            }
        });
    });

    // 폼 제출 전 아이디 중복 체크
    $(document).ready(function() {
        $('form').on('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 동작 방지

            checkDuplicateId(function(isValid) {
                if (isValid) {
                    $('form')[0].submit(); // 아이디가 중복되지 않은 경우 폼 제출
                }
            });
        });

        $('#checkDuplicateId').on('click', function(event) {
            event.preventDefault(); // 기본 링크 동작 방지
            checkDuplicateId(function(){}); // 중복검사만 수행
        });

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

            if (password === confirmPassword) {
                alert('비밀번호가 같습니다');
            } else {
                alert('비밀번호가 같지 않습니다');
            }
        });
		

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
</script>

