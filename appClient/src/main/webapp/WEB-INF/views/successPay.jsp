<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="css/wifi.css" type="text/css" />
<link rel="stylesheet" href="css/payment.css" type="text/css" />
<link rel="stylesheet" href="css/pay_footer.css" type="text/css" />
<noscript><link rel="stylesheet" href="css/noscript.css" ></noscript>
<script>

	$(document).ready(function(){
		
		setPayment();
		
	});
	
	function setPayment() {
		opener.document.getElementById("setPayInfo").value = document.getElementById("payInfo").value
	}
</script>
</head>
<body class="is-preload">

	<!-- 김승연 2020. 09. 18 추가 -->
	<!-- 결제 영수증을 위한 페이지 -->
	<!-- css 추가할 예정 -> 2020. 09. 20 css 추가 -->
	<!-- wifi.css, payment.css, pay_footer.css, noscript.css 파일 /resources/static/css 폴더에 추가 -->
	
	<!-- wrapper -->
	<div id="wrapper">

            <!-- Main -->
                <section id="main">
                	
                <!-- Header -->
                    <header>
                    
                    <!-- wifi symbol -->
                        <span class="wifi-symbol">
                            <div class="wifi-circle first"></div>
                            <div class="wifi-circle second"></div>
                            <div class="wifi-circle third"></div>
                            <div class="wifi-circle fourth"></div>
                        </span>
                    </header>
 					<p id="catchphrase">결제 완료 </p>
                    <div class="fields">
                        <div class="field half">
                            <input type="text" name="approveNum" value="승인번호 : ${successPay.tid }" readonly/>
                        </div>
                        <div class="field half">
                            <input type="text" name="partnerId" value="가맹점명 : ${successPay.partner_user_id }" readonly/>
                        </div>
                        <div class="field half">
                            <input type="text" name="itemName" value="결제상품 : ${successPay.item_name}" readonly/>
                            	<input type="text" value="결제 완료" id="payInfo" style="display:none" readonly/>
                        </div>
                        <div class="field half">
                            <input type="text" name="amount" value="결제금액 : ${successPay.amount.total } 원" readonly/>
                        </div>
                        <div class="field half">
                            <input type="text" name="request_at" value="결제 요청 일시 : ${successPay.created_at}" readonly/>
                        </div>
                        <div class="field half">
                            <input type="text" name="approved_at" value="결제 승인 일시 : ${successPay.approved_at}" readonly/>
                        </div>
                        
                    </div>
                    <p id="catchphrase"></p>
                    <footer>
                            <input id="close" type="button" value="확인" class="primary" onclick="window.close();"> 
                    </footer>
                </section>
        </div>
        <!-- 09. 21 김승연 추가 -->
        <script>
            if ('addEventListener' in window) {
                window.addEventListener('load', function() { document.body.className = document.body.className.replace(/\bis-preload\b/, ''); });
                document.body.className += (navigator.userAgent.match(/(MSIE|rv:11\.0)/) ? ' is-ie' : '');
            }
        </script>	
</body>
</html>