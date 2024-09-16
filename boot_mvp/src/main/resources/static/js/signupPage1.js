document.addEventListener('DOMContentLoaded', () => {
    // 전체 약관 동의 체크박스 이벤트
    $('#check1').on('change', function() {
        // check1이 체크되면 check2, check3, check4 모두 체크
        var isChecked = $(this).is(':checked');
        $('#check2, #check3, #check4').prop('checked', isChecked);
    });

    // 성별 선택 버튼
    const maleButton = document.getElementById('male');
    const femaleButton = document.getElementById('female');

    maleButton.addEventListener('click', () => {
        maleButton.classList.add('active');
        femaleButton.classList.remove('active');
    });

    femaleButton.addEventListener('click', () => {
        femaleButton.classList.add('active');
        maleButton.classList.remove('active');
    });
});
// "다음으로" 버튼 클릭 시 동작
$('form').on('submit', function(event) {
    event.preventDefault();

    // 1. 빈값 확인
    if ($('#emailInput').val().trim() === '' || $('#userIdInput').val().trim() === '' || $('#ppass').val().trim() === '' || $('#ppassConfirm').val().trim() === '' || $('#phoneInput').val().trim() === '' || $('#birthInput').val().trim() === '') {
        alert('모든 필드를 채워 주세요.');
        return;
    }

    // 2. 이메일 중복 확인 여부
    if (!emailCheckedOnce || !isEmailChecked) {
        alert("이메일 중복 확인을 해주세요.");
        return;
    }

    // 3. 아이디 중복 확인 여부
    if (!userIdCheckedOnce || !isUserIdChecked) {
        alert("아이디 중복 확인을 해주세요.");
        return;
    }


    // 4. 비밀번호 일치 확인
    if (!passwordCheckedOnce || !isPasswordChecked) {
		alert("비밀번호 중복 확인을 해주세요.");
        return;
    }
	
    // 5. 성별 선택 여부 확인
    if (!checkGenderSelection()) {
		alert("성별을 선택 해주세요.");
        return;
    }

    // 6. 체크박스 확인
    if (!checkRequiredCheckboxes()) {
		alert("약관에 동의 해주세요.");
        return;
    }

    // 모든 조건이 만족되었을 때 폼 제출
    $('form').off('submit').submit(); // 폼 제출
});


// 중복 확인 상태 변수
var isEmailChecked = false;
var isUserIdChecked = false;
var isPasswordChecked = false;

// 중복 확인 버튼을 눌렀는지 여부
var emailCheckedOnce = false;
var userIdCheckedOnce = false;
var passwordCheckedOnce = false;

// 이메일 중복 확인 함수
function checkEmail(callback) {
    var email = $('#emailInput').val();
    emailCheckedOnce = true;

    // 이메일 형식 검사
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("이메일 형식이 틀렸습니다.");
        return false;
    }

    var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

    $.ajax({
        url: "/email/check-email",
        type: "GET",
        data: { email: email },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
        },
        success: function(response) {
            if (response.emailExists) {
                alert("이미 등록된 이메일입니다.");
                isEmailChecked = false;
                callback(false);
            } else {
                var confirmUsage = confirm("사용 가능한 이메일입니다. 사용하시겠습니까?");
                if (confirmUsage) {
                    $('#emailInput').addClass('readonly');
                    $('#checkEmail').hide();
                    $('#emailInput').prop('readonly', true);
                    isEmailChecked = true;
                }
                callback(true);
            }
        },
        error: function() {
            alert("서버 오류. 다시 시도해주세요.");
            isEmailChecked = false;
            callback(false);
        }
    });
}

// 아이디 중복 확인 함수
function checkUserId(callback) {
    var userId = $('#userIdInput').val();
    userIdCheckedOnce = true;

    if (userId.length === 0) {
        alert("아이디를 입력해 주세요.");
        return false;
    }

    var csrfToken = $('input[name="${_csrf.parameterName}"]').val();

    // 두 개의 테이블에서 중복 확인을 진행
    $.when(
        // 첫 번째 테이블에서 중복 체크
        $.ajax({
            url: "/checkUserId",
            type: "GET",
            data: { userid: userId },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
            }
        }),
        // 두 번째 테이블에서 중복 체크 (OAuth 테이블)
        $.ajax({
            url: "/oauthCheckUserid",
            type: "GET",
            data: { userid: userId },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
            }
        })
    ).done(function(checkUserIdResponse, oauthCheckUseridResponse) {
        // 두 테이블 응답에서 userExists 값을 확인
        var userIdExists = checkUserIdResponse[0].userExists;
        var oauthUserIdExists = oauthCheckUseridResponse[0].userExists;

        if (!userIdExists && !oauthUserIdExists) {
            var confirmUsage = confirm("사용 가능한 아이디입니다. 사용하시겠습니까?");
            if (confirmUsage) {
                $('#userIdInput').addClass('readonly');
                $('#checkId').hide();
                $('#userIdInput').prop('readonly', true);
                isUserIdChecked = true;
            }
            callback(true);
        } else {
            alert("이미 사용 중인 아이디입니다.");
            isUserIdChecked = false;
            callback(false);
        }
    }).fail(function() {
        alert("서버 오류. 다시 시도해주세요.");
        isUserIdChecked = false;
        callback(false);
    });
}

// 비밀번호 일치 확인 함수
function checkPasswordMatch() {
    passwordCheckedOnce = true;

    var password = $('#ppass').val();
    var confirmPassword = $('#ppassConfirm').val();

    if (password.length === 0 || confirmPassword.length === 0) {
        alert('비밀번호를 입력해 주세요.');
        isPasswordChecked = false;
        return;
    }

    if (password === confirmPassword) {
        alert('비밀번호가 같습니다.');
        isPasswordChecked = true;
    } else {
        alert('비밀번호가 같지 않습니다.');
        isPasswordChecked = false;
    }
}

// 필수 체크박스 확인 함수
function checkRequiredCheckboxes() {
    if (!$('#check2').is(':checked')) {
        alert('(필수) 만 14세 이상입니다 항목을 체크해 주세요.');
        return false;
    }

    if (!$('#check3').is(':checked')) {
        alert('(필수) 서비스 이용 약관에 동의해 주세요.');
        return false;
    }

    if (!$('#check4').is(':checked')) {
        alert('(필수) 개인정보 수집 및 이용 동의 항목을 체크해 주세요.');
        return false;
    }

    return true;
}



// 성별 설정 함수
function setGender(genderValue) {
    document.getElementById('gender').value = genderValue;
}

// 성별 선택 확인 함수
function checkGenderSelection() {
    const gender = $('#gender').val();
    if (!gender) {
        alert('성별을 선택해 주세요.');
        return false;
    }
    return true;
}




$(document).ready(function() {
    $('#checkEmail').on('click', function(event) {
        event.preventDefault();
        checkEmail(function(){}); // 이메일 중복 체크만 수행
    });

    $('#checkId').on('click', function(event) {
        event.preventDefault();
        checkUserId(function(){}); // 아이디 중복 체크만 수행
    });

    $('#checkPasswordMatch').on('click', function(event) {
        event.preventDefault();
        checkPasswordMatch(); // 비밀번호 일치 확인 함수 호출
    });

    // 휴대폰 번호 입력 필드에 하이픈 자동 추가 및 유효성 검사
    document.getElementById('phoneInput').addEventListener('input', function(e) {
        let input = e.target.value;
        input = input.replace(/\D/g, ''); // 숫자만 남기기

        // 하이픈 추가
        if (input.length > 7) {
            input = input.replace(/(\d{3})(\d{4})(\d+)/, '$1-$2-$3');
        } else if (input.length > 3) {
            input = input.replace(/(\d{3})(\d+)/, '$1-$2');
        }
        e.target.value = input;
    });

    document.getElementById('phoneInput').addEventListener('blur', function(e) {
        const input = e.target.value;
        if (input === '') return;

        const phonePattern = /^010-\d{4}-\d{4}$/;
        if (!phonePattern.test(input)) {
            alert('휴대폰 번호를 올바른 형식(010-1234-5678)으로 입력해 주세요.');
            e.target.value = '';
        }
    });

    // 생년월일 입력 필드에 하이픈 자동 추가 및 유효성 검사
    document.getElementById('birthInput').addEventListener('input', function(e) {
        let input = e.target.value;
        input = input.replace(/\D/g, ''); // 숫자만 남기기

        if (input.length > 4 && input.length <= 6) {
            input = input.replace(/(\d{4})(\d+)/, '$1-$2');
        } else if (input.length > 6) {
            input = input.replace(/(\d{4})(\d{2})(\d+)/, '$1-$2-$3');
        }
        e.target.value = input;
    });

    document.getElementById('birthInput').addEventListener('blur', function(e) {
        const input = e.target.value;
        if (input === '') return;

        const birthPattern = /^\d{4}-\d{2}-\d{2}$/;
        if (!birthPattern.test(input)) {
            alert('생년월일을 올바른 형식(YYYY-MM-DD)으로 입력해 주세요.');
            e.target.value = '';
        } else {
            const parts = input.split('-');
            const year = parseInt(parts[0], 10);
            const month = parseInt(parts[1], 10);
            const day = parseInt(parts[2], 10);
            const date = new Date(year, month - 1, day);

            if (date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day) {
                alert('유효하지 않은 날짜입니다. 다시 입력해 주세요.');
                e.target.value = '';
            }
        }
    });

    // 이름 입력 필드에서 숫자 입력 방지
    $('#pname').on('input', function() {
        var input = $(this).val();
        input = input.replace(/[0-9]/g, ''); // 숫자를 제거
        $(this).val(input);
    });

    // 비밀번호 보기/숨기기 로직
    $('#togglePassword').on('click', function(event) {
        event.preventDefault();
        var passwordField = $('#ppass');
        var isPasswordVisible = passwordField.attr('type') === 'text';

        if (isPasswordVisible) {
            passwordField.attr('type', 'password'); // 비밀번호 숨기기
            $(this).text('비밀번호보기');
        } else {
            passwordField.attr('type', 'text'); // 비밀번호 보이기
            $(this).text('비밀번호숨기기');
        }
    });
});

function openModal(type) {
       var modal = document.getElementById("modal");
       var modalText = document.getElementById("modalText");
       var modalTitle = document.getElementById("modalTitle");
   
       // 페이지 수직 스크롤 방지
       document.body.style.overflowY = "hidden";
       document.documentElement.style.overflowY = "hidden"; // HTML 요소에도 적용
   
       if (type === 'service') {
           modalTitle.textContent = "MVP 서비스 이용약관"; // 타이틀 변경
           modalText.innerHTML = `
               <p class='bcbc'>
                   본 약관은 2024년 09월 09일부터 적용됩니다.<br><br>
   
                   제 1 조 목적<br>
                   이 약관은 주식회사 MVP(이하 "회사")에서 제공하는 MVP에서 제공하는 제반 서비스(이하 "서비스")에 접속과 사용자에 의해 업로드 및 다운로드 되어 
                   표시되는 모든 정보, 텍스트, 이미지 및 기타 자료를 이용하는 이용자(이하 "회원")와 서비스 이용에 관한 권리 및 의무와 책임사항, 기타 필요한 사항을 
                   규정하는 것을 목적으로 합니다.<br><br>
   
                   제 2 조 약관의 게시와 효력, 개정<br>
                   ① 회사는 서비스의 가입 과정에 본 약관을 게시합니다.<br>
                   ② 회사는 관련법에 위배되지 않는 범위에서 본 약관을 변경할 수 있으며, 개정 전 약관과 함께 적용일자 7일 전부터 웹사이트에서 확인할 수 있도록 
                   게시합니다. 다만, 이용자에게 불리하게 약관을 변경하는 경우에는 적용일자 30일 전에 개정내용을 이용자가 확인할 수 있도록 게시합니다.<br>
                   ③ 회원은 회사가 전항에 따라 변경하는 약관에 동의하지 않을 권리가 있으며, 이 경우 회원은 회사에서 제공하는 서비스 이용 중단 및 탈퇴 의사를 
                   표시하고 서비스 이용 종료를 요청할 수 있습니다. 다만, 회사가 회원에게 변경된 약관의 내용을 통보하면서 회원에게 "7일 이내 의사 표시를 하지 
                   않을 경우 의사 표시가 표명된 것으로 본다는 뜻"을 명확히 통지하였음에도 불구하고, 거부의 의사표시를 하지 아니한 경우 회원이 변경된 약관에 
                   동의하는 것으로 봅니다.<br><br>
   
                   제 3 조 약관의 해석과 예외 준칙<br>
                   ① 회사는 제공하는 개별 서비스에 대해서 별도의 이용약관 및 정책을 둘 수 있으며, 해당 내용이 이 약관과 상충할 경우 개별 서비스의 이용약관을 
                   우선하여 적용합니다.<br>
                   ② 본 약관에 명시되지 않은 사항이 관계법령에 규정되어 있을 경우에는 그 규정에 따릅니다.
   
                   제 4 조 약관의 게시와 효력, 개정<br>
                   ① 회사는 서비스의 가입 과정에 본 약관을 게시합니다.<br>
                   ② 회사는 관련법에 위배되지 않는 범위에서 본 약관을 변경할 수 있으며, 개정 전 약관과 함께 적용일자 7일 전부터 웹사이트에서 확인할 수 있도록 
                   게시합니다. 다만, 이용자에게 불리하게 약관을 변경하는 경우에는 적용일자 30일 전에 개정내용을 이용자가 확인할 수 있도록 게시합니다.<br>
                   ③ 회원은 회사가 전항에 따라 변경하는 약관에 동의하지 않을 권리가 있으며, 이 경우 회원은 회사에서 제공하는 서비스 이용 중단 및 탈퇴 의사를 
                   표시하고 서비스 이용 종료를 요청할 수 있습니다. 다만, 회사가 회원에게 변경된 약관의 내용을 통보하면서 회원에게 "7일 이내 의사 표시를 하지 
                   않을 경우 의사 표시가 표명된 것으로 본다는 뜻"을 명확히 통지하였음에도 불구하고, 거부의 의사표시를 하지 아니한 경우 회원이 변경된 약관에 
                   동의하는 것으로 봅니다.<br><br>
   
                   제 5 조 약관의 해석과 예외 준칙<br>
                   ① 회사는 제공하는 개별 서비스에 대해서 별도의 이용약관 및 정책을 둘 수 있으며, 해당 내용이 이 약관과 상충할 경우 개별 서비스의 이용약관을 
                   우선하여 적용합니다.<br>
                   ② 본 약관에 명시되지 않은 사항이 관계법령에 규정되어 있을 경우에는 그 규정에 따릅니다.
               </p>`;
       } else if (type === 'privacy') {
           modalTitle.textContent = "개인정보 수집 및 이용 동의"; // 타이틀 변경
           modalText.innerHTML = `
               <p class='bcbc'>
                   본 약관은 2024년 09월 09일부터 적용됩니다.<br><br>
   
                   제 1 조 목적<br>
                   이 약관은 주식회사 MVP(이하 "회사")에서 제공하는 MVP에서 제공하는 제반 서비스(이하 "서비스")에 접속과 사용자에 의해 업로드 및 다운로드 되어 
                   표시되는 모든 정보, 텍스트, 이미지 및 기타 자료를 이용하는 이용자(이하 "회원")와 서비스 이용에 관한 권리 및 의무와 책임사항, 기타 필요한 
                   사항을 규정하는 것을 목적으로 합니다.<br><br>
   
                   제 2 조 약관의 게시와 효력, 개정<br>
                   ① 회사는 서비스의 가입 과정에 본 약관을 게시합니다.<br>
                   ② 회사는 관련법에 위배되지 않는 범위에서 본 약관을 변경할 수 있으며, 개정 전 약관과 함께 적용일자 7일 전부터 웹사이트에서 확인할 수 있도록 
                   게시합니다. 다만, 이용자에게 불리하게 약관을 변경하는 경우에는 적용일자 30일 전에 개정내용을 이용자가 확인할 수 있도록 게시합니다.<br>
                   ③ 회원은 회사가 전항에 따라 변경하는 약관에 동의하지 않을 권리가 있으며, 이 경우 회원은 회사에서 제공하는 서비스 이용 중단 및 탈퇴 의사를 
                   표시하고 서비스 이용 종료를 요청할 수 있습니다. 다만, 회사가 회원에게 변경된 약관의 내용을 통보하면서 회원에게 "7일 이내 의사 표시를 하지 
                   않을 경우 의사 표시가 표명된 것으로 본다는 뜻"을 명확히 통지하였음에도 불구하고, 거부의 의사표시를 하지 아니한 경우 회원이 변경된 약관에 
                   동의하는 것으로 봅니다.<br><br>
   
                   제 3 조 약관의 해석과 예외 준칙<br>
                   ① 회사는 제공하는 개별 서비스에 대해서 별도의 이용약관 및 정책을 둘 수 있으며, 해당 내용이 이 약관과 상충할 경우 개별 서비스의 이용약관을 
                   우선하여 적용합니다.<br>
                   ② 본 약관에 명시되지 않은 사항이 관계법령에 규정되어 있을 경우에는 그 규정에 따릅니다.<br><br>
                   
                   제 4 조 약관 준칙<br>
                   ① 회사는 제공하는 개별 서비스에 대해서 별도의 이용약관 및 정책을 둘 수 있으며, 해당 내용이 이 약관과 상충할 경우 개별 서비스의 이용약관을 
                   우선하여 적용합니다.<br>
                   ② 본 약관에 명시되지 않은 사항이 관계법령에 규정되어 있을 경우에는 그 규정에 따릅니다.
               </p>`;
       }
   
       modal.style.display = "block";
   }

function closeModal() {
    var modal = document.getElementById("modal");

    document.body.style.overflowY = "auto";
    document.documentElement.style.overflowY = "auto";

    modal.style.display = "none";
}