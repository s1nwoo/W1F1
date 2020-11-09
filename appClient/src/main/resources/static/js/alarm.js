document.write("<script src='js/_define.js'></script>");

// websocket 서버에서 신호를 보내면 자동으로 실행된다.
sock.onmessage = getAlarm;
// websocket과 연결을 끊고 싶을때 실행하는 메소드.
sock.onclose = onClose;

/* WebSocket으로 보내기 */
/* 실시간 알림 상황  (seller.js -> mybuyer function 참조)
   1. 판매자가 참여자를 선정 : 구매자
   2. 판매자가 참여자를 거절 : 다음기회에
   
   <sendMessage function>
   필요한 parameters
   1. sender midx = loginMidx
   2. receiver midx = data[i].buyer
   3. 해당 글 idx = data[i].iidx

   초기 접속시
   1. sender midx = loginMidx
   2. receiver midx = -1
   3. 해당 글 idx = 0
*/

function sendMessage(sid, list, rmidx, iidx, msg){
   
   console.log("sendMessage에 들어옴");
   console.log(msg);

   var alarm = {
      senderMidx : sid,    // 알림을 보내는 사람의 idx
      receiverList: list,  // 알림을 받는사람들 array
      receiverMidx: rmidx, // 알림을 받는 사람의 idx
      iidx : iidx,         // 변경 사항이 일어나는 게시글의 idx
      label : msg          // 알림 상태값 라벨
   };

   sock.send(JSON.stringify(alarm));
   console.log("알림 보내기 성공");
};
 
/* WebSocket으로 받기 */
function getAlarm(evt){

   console.log("getAlarm function 들어옴");
   var data = evt.data; // 전달받은 데이터
   
   msgData = JSON.parse(data);

   var midx = msgData.receiverMidx;
   var iidx = msgData.iidx;   // 해당 게시글 iidx
   var label = msgData.label; // 해당 게시글 label

   // 기존에 있던 참여자 class 제거
   $('.iidx'+iidx).children().first().removeClass('join');
   $('.c_'+iidx).removeClass('cancleOrder');
   $('.c_'+iidx).removeClass('btn_buyerAction');

   if(label == "구매자"){

      // 구매자 class, onclick, text추가
      $('.iidx'+iidx).children().first().addClass('buyer'); // 라벨 색상 변경
      $('.c_'+iidx).addClass('btnStyleQR');                 // QR 코드 버튼 css
      $('.c_'+iidx).attr('onclick', "viewQR('"+midx+","+iidx+"')");  // QR 코드 버튼 기능
      $('.c_'+iidx).text('QRcode');                         // 버튼 내용

   } else if(label == "다음기회에..."){
      // 다음기회에 class 추가
      $('.iidx'+iidx).children().first().addClass('next'); // 라벨 색상 변경
   }

   // 게시글의 상태값을 변경해 준다
   $('.label_'+iidx).text(label);
   // 탭에 알림 표시
   $('#alarm_b').addClass('alarm');

   // 해당 카드에 알림 표시
   $('#'+iidx).addClass('alarm');

};

function onClose(evt){
   console.log("websocket session close");
};

function cancleAlarm(iidx, midx, type){
   $.ajax({
      url: domain + '/alarm',
      type: 'POST',
      success: function(){
         console.log("alarm deactivated");
         $(this).removeClass('alarm');
      }
   })
};