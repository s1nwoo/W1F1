<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Main Page</title>

  <link rel="stylesheet" href="css/regForm.css" type="text/css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap" > <!-- 영문폰트 -->
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet"> <!-- 한글폰트 -->
  
  <link rel="stylesheet" href="css/swiper.css" type="text/css">
  <link rel="stylesheet" href="css/search.css" type="text/css">
  <link rel="stylesheet" href="css/aside.css" type="text/css">
  <link rel="stylesheet" href="css/item.css" type="text/css">
  <link rel="stylesheet" href="css/comment.css" type="text/css"> 
  <link rel="stylesheet" href="css/swiper.min.css" type="text/css"> <!-- swiper관련 -->
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> <!-- 평점등록 시 별모양 나타내는  -->
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  <!-- 평점등록 시 별 마우스오버 계속 반응  -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.27.0/moment.min.js"></script> <!-- 날짜포멧 -->
  <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.11/lodash.min.js"></script>   <!-- 데이터를 정렬/필터/색인할 수 있게 도와주는 오픈소스 Javascript Utility Library -->
  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script><!-- 판매처 지도표시를 위한 카카오 지도 api  -->
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f6d1cca0f8069384b56daa2529728c44&libraries=services"></script><!-- 공구 지도를 위한 카카오 지도 api  -->
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>       <!-- webSocket -->
  <script src="js/socket.js"></script>  <!-- Socket 객체 생성 -->
  
</head>
<body>

  <!-- aside 영역 : 프로필. 판매&구매현황 -->
  <%@include file="include/aside.jsp"%>


  <!-- !PAGE CONTENT! -->
  <div id="main" class="w3-main" style="margin-left:450px;margin-right:40px">
  	<input type="hidden" id="loginMidx" value="${loginMidx}">
    <input type="hidden" id="loginInfo" value="${loginInfo}">
    <input type="hidden" id="loginName" value="${loginName}">


    <!-- header영역 : 타이틀. 검색 -->
    <%@include file="include/header.jsp"%>
    <hr>



    <!-- content영역 : 공구등록폼 / 공구상세보기/ 공구리스트(추천글.일반글) -->
    

      <!-- content영역-1 : 공구등록 폼 -->
      <%@include file="include/regItemForm.jsp"%>


      	<!-- content영역-2 : 공구상세영역  -->
      	<div class="itemViewWrap">
	      	<div id="itemView_area" class="itemView_area">
	        <!-- 공구상세 내용 -->
	        <div id="itemView_context"></div>
        </div>
        
        <!-- 댓글 리스트 -->
        <div class="commentlistWrap">
          <h1>--- Comments ---</h1>
          <div class="commentlist"></div> 
          <div class="comment_formWrap"></div>  
        </div> <!-- comments_area end -->

      </div> <!-- itemView_area end -->



      <!-- content영역-3 : 공구리스트(추천글.일반글) -->
      <div id="itemlist_area">

        <!-- 추천글 Recommended item area-->
        <div class="w3-container" id="Recommended" style="margin-top:65px;" >
          <div class="title_search_area">
            <!-- 추천글 - 타이틀 -->
            <div class="mainItemTitle">
              <h2 class="w3-xxlarge text-purple"><b>Recommended</b></h2><br>
              <hr style="width:50px;border:5px solid purple;"  class="w3-round line">
            </div>
            
            <!-- 검색영역 -->
            <div id="search_area" >
              <div class="inputSearch">
                <select name="searchType" id="searchType">
					<option value="">선택</option>
					<option value="title">제목</option>
					<option value="name">게시자</option>
					<option value="both">제목 + 게시자</option>
				</select>
				<input type="text" name="search" class="search" placeholder="Search.." id="keywordBox"><br>
				<input type="submit" value="검색" style="width:120px;background-color: purple;color:white;border-radius:5px;" onclick="searchList();">
              </div>
              <div class="CategorySearch">
                <button type="button" class="btn_search_category" onclick="filterSelection('category3')">생필품/기타</button>
                <button type="button" class="btn_search_category" onclick="filterSelection('category2')">육류/해산물</button>
                <button type="button" class="btn_search_category" onclick="filterSelection('category1')">과일/채소</button>
                <button type="button" class="btn_search_category active" onclick="allItemlist()">전체</button>
              </div>
            </div>
          </div>


          <!-- 추천글 리스트 Swiper  -->
          <div id="swiper_area">
            <section class="ag-slide-block">
              <div class="swiper-container"></div>
            </section>
          </div>
        </div> <!-- Recommende end -->



        <!-- 일반글 Item area-->
        <div class="w3-container" id="item" style="margin-top:75px;" >

          <!-- 일반글 - 타이틀. 등록버튼. 정렬버튼 -->
          <div class="mainItemTitle">
            <h2 class="w3-xxlarge text-purple"><b>item</b></h2><br>
            <hr style="width:50px; border:5px solid purple; " class="w3-round line" >
          </div>
          <!-- Old 
          <div class="btn_sort_area">
            <button type="button" class="btn_sort sort_reg"  onclick="allItemlist()">Latest Sort</button>
            <button type="button" class="btn_sort sort_rvs" onclick="allItemlist_sortRvs()">Rating Sort</button>
            <button type="button" class="btn_basic" onclick="openRegForm()">item +</button>
          </div>
          	 -->
          <div class="btn_sort_area">
            <button type="button" class="btn_sort sort_reg"  onclick="allItemlist()">Latest Sort</button>
            <button type="button" class="btn_sort sort_rvs" onclick="sortingRvs()">Rating Sort</button>
            <button type="button" class="btn_basic" onclick="openRegForm()">item +</button>
          </div>
          
          <!-- 일반글 리스트 -->
          <div id="itemlist_small_area"></div> 

        </div>  <!-- Item area end -->
        
        	<br><br>
               <!--<button type="button" class="btnMore" id="showmore_btn" style="margin: auto; padding: 10px 30px; display: block; text-align: center; cursor:pointer; display: block;"  onclick="itemlist()">더보기 +</button>
               <button type="button" class="btnMore" id="showmore_btn" style="margin: auto; padding: 10px 30px; display: block; text-align: center; cursor:pointer; display: none;" onclick="ratingSort()">더보기 +</button>
               -->
               <button type="button" class="btnMore" id="showmore_btn" style="margin: auto; padding: 10px 30px; text-align: center; cursor:pointer; display: block;"  onclick="itemlist()">더보기 +</button>
               <button type="button" class="btnMore" id="showrating_btn" style="margin: auto; padding: 10px 30px;  text-align: center; cursor:pointer; display: none;" onclick="sortingRvs()">더보기 +</button>
           	<br><br>


      </div> <!-- itemlist_area end -->



  </div>  <!-- w3-main end -->




  <!-- footer -->
  <div class="w3-light-grey w3-container w3-padding-32" style="margin-top:75px;padding-right:58px">
    <p class="w3-right"></p>
  </div>

  

  

</body>
  <script src="js/swiper.min.js"></script>
  <script src="js/swiper.js"></script>
  <script src="js/_define.js"></script>
  <script src="js/alarm.js"></script>	<!-- webSocket -->
  <script src="js/comment.js"></script>	<!-- 댓글 -->
  <script src="js/seller.js"></script>	<!-- aside 판매자 -->
  <script src="js/buyer.js"></script>	<!-- aside 구매자 -->
  <script src="js/item.js"></script>	<!-- 공구 검색 / 리스트 출력 / 정렬 / 등록 / 삭제 -->
  <script src="./js/jquery.qrcode.js"></script>
  <script src="./js/qrcode.js"></script>
    
    


</html>
