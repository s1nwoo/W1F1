<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
       <title>kakaoLogin</title>
       <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
   </head>
   <body>

        

		<!-- 로그인으로 이동 -->
	    <c:if test="${userId eq null}">
	        <!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=f5e0701c39876968f090e686afa45c4a&redirect_uri=http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/appClient0916/login&response_type=code"> -->
	        <a href="https://kauth.kakao.com/oauth/authorize?client_id=f5e0701c39876968f090e686afa45c4a&redirect_uri=http://localhost:8081/login&response_type=code">
	        <img src="/img/kakaoLogin.png">
	    </a>
	    </c:if>
	    
	    <!-- 로그인 시, 회원가입으로 이동(db정보 확인) -->
	    <c:if test="${userId ne null}">
	    	<!-- <script>location.href='http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/appClient0916/loginCheck';</script> -->
	    	<script>location.href='http://localhost:8081/loginCheck';</script>
	        <!-- <input type="button" value="로그아웃" onclick="location.href='/logout'"> -->
	    </c:if>
    
    


   </body>
   </html>