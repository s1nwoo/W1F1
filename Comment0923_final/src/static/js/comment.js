var loginMidx = 2;
var iidx=130;
var domain = 'http://localhost:8080/comments';
//var domain = 'http://ec2-13-209-70-0.ap-northeast-2.compute.amazonaws.com:8080/comments';  //이걸로 배포
//var domain = 'http://ec2-13-209-70-0.ap-northeast-2.compute.amazonaws.com:8080/Comment0916';

//var loginInfo = sessionStorage.getItem("loginInfo");
//var loginMidx = sessionStorage.getItem("loginMidx");
//var loginName = sessionStorage.getItem("loginName");



$(document).ready(function() {
	getComment(iidx); 


});


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
		url : domain+'/'+iidx,
		type : 'GET',
		success : function(data) {

			if (data != null) {
				// alert('댓글 리스트 성공');

				// 본인 댓글이면, 수정.삭제 버튼 활성화
				var html = '';
				var content='';

				for(var i=0; i<data.length; i++){

					html +='				<div class="comment_card comment_card_'+data[i].cidx+'">';
					html +='				<input type="hidden" class="iidx_'+data[i].cidx+'" value="'+data[i].iidx+'">';
					html +='					<div class="comment_imgWrap"><img src="/img/사과.jpg"></div>';
					html +='					<div class="comment_box">';
					html +='						<div class="comment_text comment_text_'+data[i].cidx+'">'+data[i].content+'</div>';
					html +='						<div class="comment_footer">';
					html +='							<div class="comment_info">';
					html +='								<span class="comment_mname">'+data[i].midx+'</span>';
					html +='								<span class="comment_date">'+data[i].regdate+'</span>';
					html +='							</div>';
					html +='							<div class="comment_actions comment_actions_'+data[i].cidx+'">';
					
					if(data[i].midx == loginMidx){
						html +='								<button type="button" class="btn_editComment" onclick="editCommentForm('+data[i].cidx+')">수정</button>';
						html +='								<button type="button" class="btn_delComment" onclick="delCommant('+data[i].cidx+')">삭제</button>';
					}

					html +='								<button type="button" onclick="">Reply</button>';
					html +='							</div>';
					html +='						</div>';
					html +='					</div>';
					html +='				</div>';
					html +=' ';

				}

				$('.commentlist').html(html);
				regCommentForm(iidx);

			} else {
				alert('댓글 리스트 실패');
			}

		}

	});
} // getComment() end


/* 댓글 등록 폼 */
function regCommentForm(iidx){

	var html = '';

	html +='				<div class="comment_imgWrap">';
	html +='					<img src="http://lorempixel.com/200/200/people">';
	html +='				</div>';
	html +='				<form class="comment_form" name="comment_form" onsubmit="return false;">';
	html +='					<div class="formRow">';
	html +='						<textarea class="comment_content" name="comment_content" cols="50" rows="5" placeholder="Add comment..." required></textarea>';
	html +='						<span style="color:#aaa;" class="textCounter">0</span>&nbsp;<span>/ 250자</span>';
	html +='					</div>';
	html +='					<div class="formRow">';
	html +='						<h4 class="comment_mname">'+loginMidx+'</h4>';
	html +='						<input type="hidden" class="comment_midx" value="2">';
	html +='					</div>';
	html +='					<div class="formRow">';
	html +='						<input type="submit" value="Add Comment" class="btn_regComment" onclick="regComment('+iidx+')">';
	html +='					</div>';
	html +='				</form>';

	$('.comment_formWrap').html(html);
}







/* 댓글 등록 */
function regComment(iidx) {

	//alert('댓글등록 시작 iidx : '+iidx);
	//var text =$("#comment_content");

	var regFormData = new FormData();
	//regFormData.append('content', $('textarea[name=comment_content]').html());
	regFormData.append('content', $(".comment_content").val());
	regFormData.append('midx', loginMidx);
	regFormData.append('iidx', iidx);

	alert('regFormData : '+regFormData);
	alert('regFormData.content : '+$(".comment_content").val());
	//alert('regFormData.content : '+text.val());


	$.ajax({
		url : domain,
		type : 'POST',
		processData: false, // File 전송시 필수
		contentType: false, // multipart/form-data
		data : regFormData,
		success : function(data) {
			if (data == 1) {
				alert('댓글 등록 성공');
				getComment(iidx);
			} else {
				alert('댓글 등록 실패');
			}
			;
		}

	});
} // regComment()  end



/* 댓글 수정 폼 */
function editCommentForm(cidx) {

	//alert('댓글수정 시작 cidx : '+cidx);
	
	var html = '';
	var html2 = '';
	var content = $('.comment_text_'+cidx).text();
	alert('댓글수정 시작 content : '+content);


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
			url : domain,
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
function delCommant(cidx) {

	if (confirm('댓글을 삭제하시겠습니까?')) {

		$.ajax({
			url : domain + '/' + cidx,
			type : 'PUT',
			success : function(data) {

				if (data == 1) {
					alert('댓글을 삭제했습니다.');
					getComment(iidx);

				} else {
					alert('댓글을 삭제하지 못했습니다. 다시 시도해주세요.');
				}

			}

		});
	};
};
