<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!-- aside 공구현황 -->

<!-- 로그인한 유저 세션값 추출 -->
	<!-- style="display:none" -->
	<input type="text" id="loginName" style="display:none" value="${loginName}">
	<input type="text" id="loginInfo" style="display:none" value="${loginInfo}">
	<input type="text" id="loginMidx" style="display:none" value="${loginMidx}">
	<input type="text" id="loginImg" style="display:none" value="${profile_img}">
	
<!-- <div id="aside_area"> -->

   <!-- Sidebar/menu -->
   <!--<nav id="mySidenav" class="sidenav w3-sidebar base-purple w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:400px;font-weight:bold;" ><br>-->
      <nav id="mySidenav" class="sidenav base-purple w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:400px;font-weight:bold;" ><br>
      

      <!-- 모바일상태일 때 aside x 버튼활성화 -->
      <!-- <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px"> 
         X 
      </a> -->
      <!-- 로그아웃 버튼 -->
      <button type="button" class="btn_logout" onclick="location.href='${logout_url}'">Logout</button>
      <!-- <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a> -->
         

      <div class="asideContext">

         <!-- 프로필 -->
         <div id = "profile" class="profile">

         </div>
         
            


         <!-- 탭버튼 내용 영역 : My Sale . My Order -->
         <div class="aside_tabs">
            <table style="width: 100%;" border=0 >
            <tr>
            <td>
               <span class="aside_tab"><button type="button" class="btn_myItemlist btn_tabA">My Sale <h4 id="alarm_s"></h4></button></span>
            </td>
            <td>
               <span class="aside_tab"><button type="button" class="btn_myOderlist btn_tabA">My Order<h4 id="alarm_b"></h4></button></span>
            </td>
            </tr>
            </table>            
            
         </div> 
            

         <!-- 내 아이템리스트 영역 -->
         <div class="aside_myitem">

               <!-- 내 구매현황 -->
               <div id="aside_myOrderlist" class="aside_myOrderlist"></div>  

               <!-- 내 판매현황 -->
               <div id="aside_myItemlist" class="aside_myItemlist"> </div>  
         
         </div> <!-- aside_mylist end -->
      
      </div>

		<div id="qrCodeArea">        
		  <br><br>
		  
		  <div id="qrCodeIn"></div>
		  <br>
		  <button type="button" class="btnStyleQR" onClick="closeQrCode()">닫기</button>
		  <br>
		  
		        
		</div>


   </nav>



   <!-- 모바일 상태일때, 적용되는 템플릿 -->
   <!-- Top menu on small screens 
   <header class="w3-container w3-top w3-hide-large base-purple w3-xlarge w3-padding">
      <a href="javascript:void(0)" class="w3-button base-purple w3-margin-right" onclick="w3_open()">☰</a>
      <span>Company Name</span>
   </header>
	-->
   <!-- Overlay effect when opening sidebar on small screens
   <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
	-->
    



<!-- </div> -->
<!-- aside_area end -->
