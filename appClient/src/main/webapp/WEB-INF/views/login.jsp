<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>같이: 가치</title>
  
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet">
  <link rel="stylesheet" href="css/login.css">
  <meta name="viewport" content="width=device-width, initial-scale=1"><link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>

  <!-- jQuery src -->
  <script src="http://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<!-- template src : https://codepen.io/Mhmdhasan/pen/JbJzZv -->

<body>
<!-- partial:index.partial.html -->
<div class="container">
   <section id="formHolder">

      <div class="row">

         <!-- Brand Box -->
         <div class="col-sm-6 brand">
            <a href="#" class="logo">1Ps <span>.</span></a>

            <div class="heading">
               <h2>W1F1</h2>
               <p>1인가구를 위한</p>
               <p>공구 커뮤니티 플랫폼</p>
            </div>
         </div>


         <!-- Form Box -->
         <div class="col-sm-6 form">
            <div class="login form-peice">
            	<form class="login-form" action="#" method="post">
            		<div class="form-group">
            		<c:if test="${userId eq null}">
            			<a href="${login_url}">
            				<img src="img/kakaoLogin.png">
                     </a>
					   </c:if>
					   <c:if test="${userId ne null}">
						   <a href="${login_url}">
            				<img src="img/kakaoLogin.png">
                        </a>
                        <a href="${logout_url}">
                        	<img src="img/kakao_logout.png">
                        </a>
                  </c:if>
					</div>
					
					<div class="form-group">
					</div>
					
					<div class="CTA">
					</div>
				</form>
			</div><!-- End Login Form -->


            <!-- Signup Form -->
            <c:if test="${new_member eq null}">
              <div class="signup form-peice switched">
            </c:if>
            <c:if test="${new_member ne null}">
              <div class="signup form-peice">
            </c:if>
               <form class="signup-form" action="#" method="post">

                  <div class="form-group">
                     <label for="name" class="active">이름</label>
                     <input type="text" name="username" id="name" class="name" value="${name}">
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="email" class="active">이메일</label>
                     <input type="email" name="emailAdress" id="email" class="email" value="${email}" readonly>
                  </div>

                  <div class="form-group">
                     <label for="phone">연락처</label>
                     <input type="text" name="phone" id="phone" class="phone">
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="address">주소 - <small><button type="button" class="btn btn-secondary" onclick="daumPostcode()">찾기</button></small></label>
                     <input type="text" name="addr" id="addr" class="addr" readonly>
                     <span class="error"></span>
                  </div>

                  <div class="form-group">
                     <label for="addressDet">상세 주소</label>
                     <input type="text" name="addressDet" id="addressDet" class="addrDetail">
                     <span class="error"></span>
                  </div>

                  <div class="CTA">
                     <input type="submit" value="Signup Now" id="submit">
                     <a href="${logout_url}" class="switch">I have an account</a>
                  </div>
               </form>
            </div><!-- End Signup Form -->
         </div>
      </div>

   </section>

</div>

<!-- partial -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>

<!-- login js -->
<script src="js/login.js"></script>

<!-- alert style-->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- 지도 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f6d1cca0f8069384b56daa2529728c44&libraries=services"></script>

</body>
</html>