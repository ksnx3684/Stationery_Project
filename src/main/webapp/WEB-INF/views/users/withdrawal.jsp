<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <c:import url="../template/header.jsp"></c:import>
	<link rel="stylesheet" href="../resources/css/hamberger.css">
<body>
    <div class="container" style="width:500px;">
        <h2 style="text-align: center;">탈퇴를 진행하시겠습니까?</h2>
        <h4 style="text-align: center;">본인확인을 위하여 아이디와 비밀번호를 입력해 주십시오</h4>
        <form class="frm" action="./withdrawal" method="post">
            <div class="form-group">
                <label for="id">ID</label><br>
                <input type="text" class="form-control" placeholder="ID" name="id">
            </div>
            <div class="form-group">
                <label for="pwd">비밀번호</label><br>
                <input type="password" class="form-control" placeholder="PW" name="pw">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">로그인</button>
            </div>
        </form>
    </div>
    <script src="../resources/js/hamberger.js"></script>
</body>
</html>