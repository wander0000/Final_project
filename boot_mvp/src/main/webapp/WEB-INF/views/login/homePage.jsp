<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 정보</title>
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
            font-size: 16px;
        }
        .button-group {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
        .button-group button {
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .button-group button[type="button"] {
            background-color: #007bff;
            color: #fff;
        }
        .button-group button[type="submit"] {
            background-color: #dc3545;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>${user.username}님의 회원 정보</h2>

        <p>아이디: ${user.userId}</p>
        <p>이름: ${user.username}</p>
        <p>이메일: ${user.email}</p>
        <p>전화번호: ${user.phone}</p>
        <p>생일: ${user.birth}</p>
        <p>성별: ${user.gender == 1 ? '남성' : '여성'}</p>
        <p>가입 일자: ${user.adate}</p>
        <p>수정 일자: ${user.mdate}</p>

        <div class="button-group">
            <button type="button" onclick="location.href='update'">수정하기</button>

            <form action="/logout" method="post" style="display:inline;">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit">로그아웃</button>
            </form>

            <form action="/delete" method="post" style="display:inline;">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit">탈퇴하기</button>
            </form>
        </div>
    </div>
</body>
</html>
