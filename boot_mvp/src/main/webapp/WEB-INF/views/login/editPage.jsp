<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 정보 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        h2 {
            margin: 0 0 20px;
            text-align: center;
        }
        p {
            margin: 10px 0;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group input:focus {
            border-color: #007bff;
            outline: none;
        }
        .button-group {
            display: flex;
            justify-content: center;
        }
        .button-group button {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 정보 수정</h2>
        <form action="/update" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <div class="form-group">
                <label for="pname">이름</label>
                <input type="text" id="pname" name="pname" value="${user.pname}"/>
            </div>
            <div class="form-group">
                <label for="userid">아이디</label>
                <input type="text" id="userid" name="userid" value="${user.userid}" readonly/>
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="text" id="email" name="email" value="${user.email}"/>
            </div>
            <div class="form-group">
                <label for="ppass">비밀번호</label>
                <input type="password" id="password" name="ppass" placeholder="새 비밀번호 입력"/>
            </div>
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="text" id="phone" name="phone" value="${user.phone}"/>
            </div>
            <div class="form-group">
                <label for="birth">생일</label>
                <input type="text" id="birth" name="birth" value="${user.birth}"/>
            </div>
            <div class="form-group">
                <label for="gender">성별</label>
                <select id="gender" name="gender">
                    <option value="1" ${user.gender == 1 ? 'selected' : ''}>남성</option>
                    <option value="0" ${user.gender == 0 ? 'selected' : ''}>여성</option>
                </select>
            </div>

            <div class="button-group">
                <button type="submit">저장하기</button>
            </div>
        </form>
    </div>
</body>
</html>
