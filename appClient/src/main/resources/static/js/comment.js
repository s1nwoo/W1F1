
var domainC = "http://ec2-13-209-70-0.ap-northeast-2.compute.amazonaws.com:8080/wifi/comments";
//var domainC = "http://localhost:8082/comments";
//var domain = 'http://localhost:8080/comments';


/***** comment : 댓글 리스트 / 등록 / 수정 / 삭제  *****************************/

// var loginMidx = 2;
// var loginName = 'loginName';
// var iidx=130;

//var loginInfo = sessionStorage.getItem("loginInfo");
//var loginMidx = sessionStorage.getItem("loginMidx");
//var loginName = sessionStorage.getItem("loginName");



/* 댓글 등록시, 글자수 제한 */
$(document).on('keyup', '.comment_content', function(e){
	var text = $(this).val();
	if(getBytes(text) >250){
		alert("250자 이상 입력할수 없습니다.");
		$(this).val("");
		$(this).val(byteTxt);
	} else {
		$('.textCounter').text(getBytes(text));  
	};
});
 
function getBytes(str){
    var cnt = 0;
    for(var i =0; i<str.length; i++) {
		cnt += (str.charCodeAt(i) >128) ? 2: 1;
	};
    return cnt;
};




/* 댓글 리스트 */
function getComment(iidx) {
	
	$.ajax({
		url : domainC+'/'+iidx,
		type : 'GET',
		success : function(data) {

			if (data != null) {
				 
				// 본인 댓글이면, 수정.삭제 버튼 활성화
				var html = '';
				var content='';

				for(var i=0; i<data.length; i++){

					html +='				<div class="comment_card comment_card_'+data[i].cidx+'">';
					html +='				<input type="hidden" class="iidx_'+data[i].cidx+'" value="'+data[i].iidx+'">';
					html +='					<div class="comment_imgWrap"><img src="img/default-profile.png" style="background-color: #FEE500"></div>';
					html +='					<div class="comment_box">';
					html +='						<div class="comment_text comment_text_'+data[i].cidx+'">'+data[i].content+'</div>';
					html +='						<div class="comment_footer">';
					html +='							<div class="comment_info">';
					html +='								<span class="comment_mname">'+data[i].name+'</span>';
					html +='								<span class="comment_date">'+data[i].regdate+'</span>';
					html +='							</div>';
					html +='							<div class="comment_actions comment_actions_'+data[i].cidx+'">';
					
					if(data[i].midx == loginMidx){
						html +='								<button type="button" class="btn_editComment" onclick="editCommentForm('+data[i].cidx+')">수정</button>';
						html +='								<button type="button" class="btn_delComment" onclick="delCommant('+data[i].cidx+','+iidx+')">삭제</button>';
					}

//					html +='								<button type="button" onclick="">Reply</button>';
					html +='							</div>';
					html +='						</div>';
					html +='					</div>';
					html +='				</div>';
					html +=' ';

				}

				$('.commentlist').html(html);

			} else {
				alert('댓글 리스트 실패');
			}

		}

	});
} // getComment() end


/* 댓글 등록 폼 */
function regCommentForm(iidx){

	var chtml = '';

	chtml +='		<form id="comment_form" name="comment_form" onsubmit="return false;">';
	chtml +='		<table width=100% border=0>';
	chtml +='			<tr><td width=80px>';
	chtml +='	   			     <div class="comment_imgWrapN">';
	chtml +='					 <img src="img/default-profile.png" style="background-color: #8C5394; width: 100%; height:100%;">';
	chtml +='				     </div>';
	chtml +='				</td><td>';
	chtml +='					<div class="formRow">';
	chtml +='						<h4 class="comment_mname">&nbsp&nbsp'+loginName+'</h4>';
	chtml +='						<input type="hidden" class="comment_midx" value="2">';
	chtml +='					</div>';
	chtml +='				</td><td>';	
	chtml +='					<div class="formRow">';
	chtml +='						<textarea class="comment_content" name="comment_content" rows="3" placeholder="Add comment..." required></textarea>';		
	chtml +='					</div>';
	
	chtml +='					<div class="formRow">';
	chtml +='						<span style="color:#aaa;" class="textCounter">0</span>&nbsp;<span>/ 250자</span>';
	chtml +='					</div>';
//	chtml +='				  </td></tr>";
//	chtml +='				  <tr><td></td><td></td><td>";
		
	chtml +='				     </td></tr></table>';
	chtml +='					<br><input type="submit" value="Add Comment" class="btn_regComment btn_search_category" onclick="regComment('+iidx+')">';
	chtml +='		</form>';

	$('.comment_formWrap').html(chtml);

}







/* 댓글 등록 */
function regComment(iidx) {

	// alert('댓글등록 시작 iidx : '+iidx);
	// var text =$("#comment_content");

	var regFormData = new FormData();
	//regFormData.append('content', $('textarea[name=comment_content]').html());
	regFormData.append('content', $(".comment_content").val());
	regFormData.append('midx', loginMidx);
	regFormData.append('iidx', iidx);

	alert('regFormData : '+regFormData);
	alert('regFormData.content : '+$(".comment_content").val());
	//alert('regFormData.content : '+text.val());


	$.ajax({
		url : domainC,
		type : 'POST',
		processData: false, // File 전송시 필수
		contentType: false, // multipart/form-data
		data : regFormData,
		success : function(data) {
			if (data == 1) {
				alert('댓글 등록 성공');
				getComment(iidx);
				document.getElementById('comment_form').reset();
			} else {
				alert('댓글 등록 실패');
			}
			;
		}

	});
} // regComment()  end



/* 댓글 수정 폼 */
function editCommentForm(cidx) {

	// alert('댓글수정 시작 cidx : '+cidx);
	
	var html = '';
	var html2 = '';
	var content = $('.comment_text_'+cidx).text();
	// alert('댓글수정 시작 content : '+content);


	html +='						<textarea class="comment_content_'+cidx+'" required>'+content+'</textarea>';
	$(".comment_text_"+cidx).html(html);

	html2 +='						<button type="button" onclick="editComment('+cidx+')">수정</button>';
	$(".comment_actions_"+cidx).html(html2);

};




/* 댓글 수정 */
function editComment(cidx) {

	var content = $('.comment_content_'+cidx).val();

	if (confirm('댓글을 수정하시겠습니까?')) {

		$.ajax({
			url : domainC,
			type : 'PUT',
			data : {cidx : cidx, content : content},
			success : function(data) {

				if (data > 0) {
					alert('댓글을 수정했습니다.');
					getComment(data);

				} else {
					alert('댓글을 수정하지 못했습니다. 다시 시도해주세요.');
				}
			}

		});
	}
	;
} // editComment() end

/* 댓글 삭제 */
function delCommant(cidx,iidx) {

	if (confirm('댓글을 삭제하시겠습니까?')) {

		$.ajax({
			url : domainC + '/' + cidx,
			type : 'PUT',
			success : function(data) {

				if (data == 1) {
					alert('댓글을 삭제했습니다.');

				} else {
					alert('댓글을 삭제하지 못했습니다. 다시 시도해주세요.');
				}

			},
			complete: function(){
				getComment(iidx);
			}

		});
	};
};
