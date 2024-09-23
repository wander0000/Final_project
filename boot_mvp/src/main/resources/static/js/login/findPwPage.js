$(document).ready(function() {
        let verificationCodeSent = false; // 인증번호 전송 여부 확인
        let verificationSuccess = false;  // 인증 성공 여부 확인

        // 이메일 유효성 검사 함수
        function isValidEmail(email) {
            const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            return emailPattern.test(email);
        }

        // 인증번호 전송 버튼 클릭 시
        $('#sendCodeButton').on('click', function() {
            const email = $('#email').val();

            if (!email || !isValidEmail(email)) {
                alert('유효한 이메일을 입력해주세요.');
                return;
            }

            // 이메일로 인증번호 전송하는 Ajax 요청
            $.ajax({
                url: "/email/send-verification-code",
                type: "POST",
                data: { email: email },
                success: function(response) {
                    alert('인증번호가 전송되었습니다.');
                    verificationCodeSent = true; // 인증번호 전송 여부 업데이트
                    $('#sendCodeButton').hide(); // 전송 버튼 숨기기
                    $('#email').attr('readonly', true); // 이메일 입력 필드를 readonly로 설정
                },
                error: function() {
                    alert('이메일 전송에 실패했습니다.');
                }
            });
        });

        // 인증번호 확인 버튼 클릭 시
        $('#verifyCodeButton').on('click', function() {
            const email = $('#email').val();
            const code = $('#code').val();

            if (!email) {
                alert('이메일을 입력해주세요.');
                return;
            }

            if (!verificationCodeSent) {
                alert('먼저 인증번호를 전송해주세요.');
                return;
            }

            if (!code) {
                alert('인증번호를 입력해주세요.');
                return;
            }

            // 인증번호 확인하는 Ajax 요청
            $.ajax({
                url: "/email/verify-code",
                type: "POST",
                data: { email: email, code: code },
                success: function(response) {
                    if (response.includes('success')) {
                        alert('인증번호가 일치합니다');
                        verificationSuccess = true; // 인증 성공 여부 업데이트
                        $('#verifyCodeButton').hide(); // 인증 확인 버튼 숨기기
                        $('#code').attr('readonly', true); // 인증번호 입력 필드를 readonly로 설정
                        $('#finalSubmitButton').show(); // 비밀번호 변경 페이지로 이동 버튼 표시
                    } else {
                        alert('인증번호가 일치하지 않습니다.');
                    }
                },
                error: function() {
                    alert('인증번호 확인에 실패했습니다.');
                }
            });
        });

        // 비밀번호 변경 페이지로 이동 버튼 클릭 시
        $('#finalSubmitButton').on('click', function() {
            if (verificationSuccess) {
                // 인증이 성공하면 비밀번호 변경 페이지로 이동
                window.location.href = "/resetPwPage?email=" + $('#email').val();
            } else {
                alert('먼저 인증을 완료해주세요.');
            }
        });
    });