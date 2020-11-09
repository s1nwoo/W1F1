<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- 장윤원 2020. 09.20 추가 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f6d1cca0f8069384b56daa2529728c44&libraries=services"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<style>


/* named upload */
.filebox .upload-name {
    display: inline-block;
  height: 35px;
  font-size:18px; 
  padding: 0 10px;
    vertical-align: middle;
    background-color: #f5f5f5;
  border: 1px solid #ebebeb;
  border-radius: 5px;

}
.filebox label {
  display: inline-block;
  padding: .5em .75em;
  color: #fff;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #5cb85c;
  cursor: pointer;
  border: 1px solid #4cae4c;
  border-radius: .25em;
  -webkit-transition: background-color 0.2s;
  transition: background-color 0.2s;
}

.filebox label:hover {
  background-color: #6ed36e;
}

.filebox label:active {
  background-color: #367c36;
}

.filebox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>

<!-- regArea (hidden by default) -->
<div id="regArea" class="w3-modal">
   <div class="regForm w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
      <div class="w3-center"><br>
         <span onclick="closeRegForm();" class="w3-button w3-xlarge w3-transparent w3-display-topright" title="Close Modal">×</span>
      </div>

      <form id="regForm" class="w3-container" onsubmit="return false;">
         <div class="w3-section">
            <label><b><strong>제목</strong></b></label>
            <input class="w3-input w3-border w3-margin-bottom" type="text" name="title" id="title" required>

            <label><b><strong>가격</strong></b></label>
            <input class="w3-halfInput w3-border w3-margin-bottom" type="text" pattern="[0-9]*" name="price" id="price" required>

            <label><b><strong>공구인원</strong></b></label>
            <input class="w3-halfInput w3-border w3-margin-bottom" type="number" name="count_m" id="count_m" min="1" required> <br>

            <label><b><strong>수령날짜</strong></b></label>
            <input class="w3-input w3-border w3-margin-bottom" type="datetime-local" name="receive" id="receive" required>

            <!-- 장윤원 2020. 09. 20 : 주소와 좌표 입력 기능 추가-->
            <label><b><strong>판매처 주소</strong></b></label>
            <div class="w3-input w3-border w3-margin-bottom" style="height: 60px;">
            <input type="text" class="" name="addr" id="addr" onclick="sample5_execDaumPostcode()" placeholder="주소" style="float:left; border:  none; width: 80%;height: 40px;">
            <input type="button" class="w3-button w3-green " onclick="sample5_execDaumPostcode()" value="주소 검색" style="float: right;"><br>
            </div>
            <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

            <label><b><strong>분류</strong></b></label>
            <select id="category" class="w3-padding-8 w3-border w3-margin-bottom">
              <option value="0">전체</option>
              <option value="1">과일/채소</option>
              <option value="2">육류/해산물</option>
              <option value="3">생필품/기타</option>
            </select>
    
            
            <label><b><strong>사진</strong></b></label>
         <span class="filebox"> 
           <label for="photo">파일선택</label> 
           <input type="file" id="photo"> 
           
             <input class="upload-name" value="선택된 파일 없음">
         </span>

            <br>


            <label><b><strong>내용</strong></b></label>
            <textarea class="w3-itemContent w3-border w3-margin-bottom" name="content" id="content" required></textarea>

            <label><b><strong>공구 상태</strong></b></label><br>
            <input class="w3-radio w3-border" type="radio" name="state" id="recommend" value="1"><label for="recommend">추천공구로 등록하기</label><input class="w3-radio w3-border" type="radio" name="state" id="normal" value="0"><label for="normal">일반공구로 등록하기</label><br>
            <input type="text" id="setPayInfo" style="display:none" readonly/>

            <!-- 김승연 2020. 09. 18 -->
            <!-- 로그인한 멤버 idx 추출 -->
            <!-- 김승연 2020. 09. 20 style 속성 추가 display:none -->
            <input type="number" name="midx" id="midx" value="${loginMidx}" style="display:none">

            <button class="w3-button .w3-halfBlock w3-green w3-section w3-padding" type="submit" id="regSubmit">공구등록</button>
            <button onclick="closeRegForm();" type="button" class="w3-button w3-red">취소</button>
         </div>
      </form>
   </div>
</div> <!-- regArea end --> 

<script>

   $("#photo").on('change',function(){
     var fileName = $("#photo").val();
     $(".upload-name").val(fileName);
   });


</script>