<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>유저 리스트</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
    </style>
</head>
<body>
    <h2>유저 리스트</h2>
    <table>
        <tr>
            <th>아이디</th>
            <th>이름</th>
            <th>이메일</th>
            <th>전화번호</th>
            <th>생일</th>
            <th>성별</th>
        </tr>
        <c:forEach items="${list}" var="u">
        <tr>
            <td>${u.userid}</td>
            <td>${u.pname}</td>
            <td>${u.email}</td>
            <td>${u.phone}</td>
            <td>${u.birth}</td>
            <td>${u.gender == 1 ? '남성' : '여성'}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>
