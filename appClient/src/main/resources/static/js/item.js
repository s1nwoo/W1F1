document.write("<script src='js/_define.js'></script>");
document.write("<script src='js/map.js'></script>");


/***** item : 공구 검색 / 리스트 출력 / 정렬 / 등록 / 삭제  *****************************/

//var loginInfo = sessionStorage.getItem("loginInfo");
//var loginMidx = document.getElementById("loginMidx");
//var loginName = sessionStorage.getItem("loginName");
//var loginMidx = 1;


var recomItems = [];    	// 추천리스트 담아두는 배열
var items = [];				// 일반리스트 담아두는 배열
var sortRecomItems = []; 	// 추천리스트 평점순 정렬 담아두는 배열
var sortItems = []; 		// 일반리스트 평점순 정렬 담아두는 배열
var itemView = [];				// itemView 담아두는 배열
var pageNum = 1;
var sortNum = 1;

/***** 지도를 위한 전역변수 선언  *****/
var location_y=0.0;
var location_x=0.0;

// 초기 접속 websocket session 생성
sock.onopen = function(){
	console.log("socket session open");
	var msg = "초기접속";
	var list = [];
	sock.send(JSON.stringify({senderMidx: loginMidx, receiverList: list, receiverMidx: -1, iidx: 0, label: msg}));
}

$(document).ready(function(){

	var openPay;
	profile();  // 기능코드는 buyer.js에 작성
	allItemlist();  // 전체 공구리스트 출력

	$('#keywordBox').keydown(function(key){
		if(key.keyCode == 13){
		   searchList();
		   
		}
	 });

	/* ing 검색기능 */
	$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#myTable tr").filter(function() {
		  $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	  });

	

	/* ing 나의 공구구매현황[구매자] - 평점등록  */
    /* 별 표시  */
    /* 1. Visualizing things on Hover - See next part for action on click */
    $('#stars li').mouseover(function(){
        var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
    
        // Now highlight all the stars that's not after the current hovered star
        $(this).parent().children('li.star').each(function(e){
        if (e < onStar) {
            $(this).addClass('hover');
        }
        else {
            $(this).removeClass('hover');
        }
        });
        
    
    }).mouseout(function(){
        $(this).parent().children('li.star').each(function(e){
            $(this).removeClass('hover');
        });
    });
    


    /* ing 나의 공구구매현황[구매자] - 평점등록  */
    /* 별 표시 - 평점계산  */
    /* 2. Action to perform on click */
    $('#stars li').click(function(){

        var onStar = parseInt($(this).data('value'), 10); // The star currently selected
        var stars = $(this).parent().children('li.star');

        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
        }

        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
        }

        // JUST RESPONSE (Not needed)
        var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);

        $(this).parent().eq(2).data('value',ratingValue);

    });
    
    /* 결제 시스템 함수 */
    $('#regSubmit').click(function test_reg () {
			var payInfo = document.getElementById('setPayInfo').value;

			console.log(payInfo);
			console.log(typeof(payInfo));
		
			var checkRadio = $('input:radio[id=recommend]').is(':checked');
		
			if(checkRadio) {
				
				if(payInfo == ""){
				
					window.name = "regItemForm";
					openPay = window.open("kakaoPay", "successPay", "width=500, height=500, left=200, top=200");
					var paytest = document.getElementById('regForm');
					paytest.target = "kakaoPay";
					paytest.action = "kakaoPay";
					
					paytest.submit();
				
				} else if (payInfo != ""){
					regSubmit();
				}
				
			}else {
				regSubmit();
			}
			
			
		});
    

});  // ready end



// 검색 관련 ------------------------------------------------------------------

	/* 카테고리 정렬 */
	function filterSelection(c) {
		
		$(".item_card_big").css('display', 'none');
		$(".item_card").css('display', 'none');
		$('.'+c).css("display","inline-block");
	
	};  // filterSelection(c) end
	
	
	
	$('.btn_search_category').click(function(){
	
		$('.btn_search_category').removeClass("active");
		$(this).addClass("active");
	
	});





// 리스트 관련 ------------------------------------------------------------------

	/* 모든 공구글 리스트 */
	function allItemlist(){

		$('.commentlistWrap').css('display','none');
		
		$('#showmore_btn').css('display','block');
		$('#showrating_btn').css('display','none');
				
		recomItemlist();
		sortNum=1;
		pageNum=1;
		itemlist();
	};

	/* 추천 공구 리스트 */
	function recomItemlist(){

		$('.sort_reg').css('background-color', 'teal');
		$('.sort_rvs').css('background-color', 'aquamarine');
		
		$.ajax({
			url: domain+'/items/recomItem',
			type: 'GET',
			success: function(data){

				// 배열 새로 비우고 담아야한다.. 안그러면 정렬하고 돌아오면 배열요소 추가로 담겨 평점정렬할 때 요소 2배로 나온다..
				recomItems=[];
				
				// 추천 공구 리스트 ㅡ> 배열에 담기
				for (i in data) {
					recomItems.push(data[i]);
				}

				
				recomItemlist_print(data);

			} // success end

		});


	}; // recomItemlist()  end



	/* 0917 윤원수정 */
	/* 일반 공구 리스트 */
	function itemlist(){

		var html = '';
		var category = '';

		$('.sort_reg').css('background-color', 'teal');
		$('.sort_rvs').css('background-color', 'aquamarine');
		
		
		
		
		
		$.ajax({
			url: domain+'/items',
			type: 'GET',
			data: {
				'istate': 0,
				'page': pageNum,
				'count': 8,
				'searchType' :  $('#searchType').val(),
				'keyword': $('#keywordBox').val()
			},
			success: function(data){
			
			
			if(pageNum>data.pageTotalCount) {
				
				return false;
        	 }

				console.log('일반공구리스트성공 : '+data);
				
				

		console.log(data.itemList);
		
		// 배열 새로 비우고 담아야한다.. 안그러면 정렬하고 돌아오면 배열요소 추가로 담겨 평점정렬할 때 요소 2배로 나온다..
			//items=[]; 
			itemView =[]; 
            
            html += '<div class="itemlist_buttons">';

			// 일반 공구 리스트 ㅡ> 배열에 담기
			for (i in data.itemList) {
				items.push(data.itemList[i]);
				console.log('itemList[i] : '+data.itemList[i]);
			}
			
			var imgPath;
		
            for(var i=0; i<data.itemList.length; i++){

                switch(data.itemList[i].category){
                    case 0: 
                        category = '';
                        break;

                    case 1: 
                        category = '과일/채소';
                        break;

                    case 2: 
                        category = '육류/해산물';
                        break;

                    case 3:
                        category = '생필품/기타';
                        break;
                }

				imgPath = domain+'/upload/'+data.itemList[i].photo;				
				console.log('imgPath : '+imgPath);


                html += '	<button type="button" class="item_card category'+data.itemList[i].category+'" onclick="showDetails('+data.itemList[i].iidx+','+loginMidx+')">';
                //html += '		<input type="hidden" value="'+data[i].iidx+'">';
                //html += '		<img class="item_img" src="/order/upload/'+data.itemList[i].photo+'">';
                html += '		<img class="item_img" src="'+imgPath+'" alt="'+imgPath+'">';
                
                //html += '" alt="/order/upload/'+data.itemList[i].photo+'">';
                //console.log('img : '+html);
                html += '		<div class="item_info">';
                //html += '				<h2 class="item_category">'+category+'</h2>';
                html += '				<h2 ><b class="item_title">'+data.itemList[i].iidx+': '+data.itemList[i].title+'</b></h2>';
                //html += '				<h4 class="seller_rating">view '+data[i].view_count+'</h4>';
                html += '				<h3 class="item_price">price : '+data.itemList[i].price+'</h4> ';
                html += '				<h3 class="item_limitDate">D-day : '+data.itemList[i].receive+'</h4>';                
                html += '				<h3 class="item_addr">addr : '+data.itemList[i].addr+'</h4>';
                html += '				<h3 class="seller_name">'+data.itemList[i].midx+'.'+data.itemList[i].name+' | 평균 ★ '+data.itemList[i].rvs_avg+'(총 '+data.itemList[i].rvs_totalRow+'건)</h4>';
                html += '		</div>';
                html += '	</button>';


            } // for end

            html += '</div>';
				
				itemlist_print(html, pageNum);
				pageNum++;

			} // success end

		});

	}; // itemlist() end



	/* 추천공구글 출력기능 */
	function recomItemlist_print(data){

		var html = '';
		var category = '';
				
		// 출력되어있는 추천 공구리스트 지우고 ㅡ> 다시 출력
		$('.swiper-container').html(' ');

		$('#itemlist_area').css('display','block');
		$('#itemRegForm_area').css('display','none');
		$('#itemView_area').css('display','none');


			html += '<ul class="swiper-wrapper ag-slide_list">';
		
		var imgPath;

		for(var i=0; i<data.length; i++){

			switch(data[i].category){
				case 0: 
					category = '';
					break;
				
				case 1: 
					category = '과일/채소';
					break;

				case 2: 
					category = '육류/해산물';
					break;

				case 3:
					category = '생필품/기타';
					break;
			}
			imgPath = domain+'/upload/'+data[i].photo;				
			console.log('imgPath : '+imgPath);
                
			
			html += '	<li class="swiper-slide ag-slide_item" data-swiper-autoplay="1500" data-swiper-slide-index="'+i+'">';			
			html += '		<button type="button"  class="item_card_big category'+data[i].category+'" onclick="showDetails('+data[i].iidx+','+loginMidx+')">';
			//html += '			<img src="/order/upload/'+data[i].photo+'" class="ag-slide_img" alt="">';
			html += '		<img class="ag-slide_img" src="'+imgPath+'" alt="'+imgPath+'">';
			
			html += '			<div class="ag-slide_info clearfix">';
			
			html += '				<div class="ag-slide-info_descr">';
			html += '					<small class="ag-slide-info_category">'+category+'</small>';
			if(data[i].title.length > 13){
				html += '					<h6 class="ag-slide-info_title"><b>'+data[i].iidx+': '+data[i].title.substr(0,12)+'..</b></h6>';
			}else {
				html += '					<h6 class="ag-slide-info_title"><b>'+data[i].iidx+': '+data[i].title+'</b></h6>';
			}
			html += '					<h5 class="ag-slide-info_title">price : '+data[i].price+'</h6>';
			html += '					<h5 class="ag-slide-info_title">D-day : '+data[i].receive+'</h6>';
			html += '					<h5 class="ag-slide-info_title">addr : '+data[i].addr+'</h6>';
			html += '					<span class="ag-slide-info_route">';
			html += '						<span class="seller_name">'+data[i].midx+'.'+data[i].name+' | 평균 ★ '+data[i].rvs_avg+'(총 '+data[i].rvs_totalRow+'건)</span><br>';
			html += '					</span>';
			html += '				</div>';
			
			html += '				<div class="ag-slide-info_rate">';
			html += '					<b class="ag-slide-info_strong">'+data[i].view_count+'</b>';
			html += '					<span class="ag-slide-info_comments">view</span>';
			html += '				</div>';
			
			html += '			</div>';

			html += '		</button>';
			html += '	</li>';
			

		} // for end
		
			html += '</ul>';
			

			// 표시되는 위치
			$('.swiper-container').html(html);



			//★ swiper 기능을 이 안에 두니까 에러가 해결되었다 ㅠㅠㅠㅠㅠ
			(function ($) {
				$(function () {
			
				var agSwiper = $('.swiper-container');
			
				if (agSwiper.length > 0) {
			
				var sliderView = 3;
				var ww = $(window).width();
				if (ww >= 1700) sliderView = 3;
				if (ww <= 1700) sliderView = 3;
				if (ww <= 1560) sliderView = 3;
				if (ww <= 1400) sliderView = 3;
				if (ww <= 1060) sliderView = 3;
				if (ww <= 800) sliderView = 3;
				if (ww <= 560) sliderView = 3;
				if (ww <= 400) sliderView = 1;
			
				var swiper = new Swiper('.swiper-container', {
					slidesPerView: sliderView,
					spaceBetween: 100,
					loop: true,
					loopedSlides: 10,
					speed: 450,
					autoplay: true,
					autoplayDisableOnInteraction: true,
					centeredSlides: true
				});
			
				$(window).resize(function () {
					var ww = $(window).width();
					if (ww >= 1700) swiper.params.slidesPerView = 3;
					if (ww <= 1700) swiper.params.slidesPerView = 3;
					if (ww <= 1560) swiper.params.slidesPerView = 3;
					if (ww <= 1400) swiper.params.slidesPerView = 3;
					if (ww <= 1060) swiper.params.slidesPerView = 3;
					if (ww <= 800) swiper.params.slidesPerView = 3;
					if (ww <= 560) swiper.params.slidesPerView = 2;
					if (ww <= 400) swiper.params.slidesPerView = 1;
				});
			
				$(window).trigger('resize');
			
				var mySwiper = document.querySelector('.swiper-container').swiper;
			
				agSwiper.mouseenter(function () {
					mySwiper.autoplay.stop();
					console.log('slider stopped');
				});
			
				agSwiper.mouseleave(function () {
					mySwiper.autoplay.start();
					console.log('slider started again');
				});
				}
			
				});
			})(jQuery);
		


	}; // recomItemlist_print(data) end

	function searchList(){
		pageNum=1;
		itemlist();
		$('#searchType').find('option:first').attr('selected','selected');
		$('#keywordBox').val('');
		
	};


	/* 일반공구글 출력기능 */
	function itemlist_print(html, pageNum){
	
		if(pageNum==1){
			// 출력되어있는 일반 공구리스트 지우고 ㅡ> 다시 출력
			$('#itemlist_small_area').html(' ');
		}
		
		$('#itemlist_area').css('display','block');
		$('#itemRegForm_area').css('display','none');
		$('#itemView_area').css('display','none');
		
		 // 표시되는 위치
        $("#itemlist_small_area").append(html);
		
		

	}; // itemlist_print(data) end

	function sortingRvs(){
	
		var html = '';
		var category = '';
		pageNum=1;
		
		$('#showmore_btn').css('display','none');
		$('#showrating_btn').css('display','block');

		$('.sort_reg').css('background-color', 'teal');
		$('.sort_rvs').css('background-color', 'aquamarine');
		
		$.ajax({
			url: domain+'/items',
			type: 'GET',
			data: {
				'istate':0,
				'page':sortNum,
				'count': 8,
				'searchType' : 'rvs_avg',
				'keyword': 'rvs_avg'
			},
			success: function(data){
			
				if(sortNum>data.pageTotalCount) {

					sortNum=1;
					return false;
					
					
	        	 }

				console.log('평점순 정렬 성공 : '+data);
				

				console.log(data.itemList);
		
				// 배열 새로 비우고 담아야한다.. 안그러면 정렬하고 돌아오면 배열요소 추가로 담겨 평점정렬할 때 요소 2배로 나온다..
				items=[]; 
				itemView =[]; 
	            
	            html += '<div class="itemlist_buttons">';
	
				// 일반 공구 리스트 ㅡ> 배열에 담기
				for (i in data.itemList) {
					items.push(data.itemList[i]);
				}
			
            	for(var i=0; i<data.itemList.length; i++){

                switch(data.itemList[i].category){
                    case 0: 
                        category = '';
                        break;

                    case 1: 
                        category = '과일/채소';
                        break;

                    case 2: 
                        category = '육류/해산물';
                        break;

                    case 3:
                        category = '생필품/기타';
                        break;
                }
                imgPath = domain+'/upload/'+data.itemList[i].photo;				
				console.log('imgPath : '+imgPath);
                       

                html += '	<button type="button" class="item_card category'+data.itemList[i].category+'" onclick="showDetails('+data.itemList[i].iidx+','+loginMidx+')">';
                //html += '		<input type="hidden" value="'+data[i].iidx+'">';
                //html += '		<img class="item_img" src="/order/upload/'+data.itemList[i].photo+'">';
                html += '		<img class="item_img" src="'+imgPath+'" alt="'+imgPath+'">';
                html += '		<div class="item_info">';
                //html += '				<h2 class="item_category">'+category+'</h2>';
                html += '				<h2 ><b class="item_title">'+data.itemList[i].iidx+': '+data.itemList[i].title+'</b></h2>';
                //html += '				<h4 class="seller_rating">view '+data[i].view_count+'</h4>';
                html += '				<h3 class="item_price">price : '+data.itemList[i].price+'</h4> ';
                html += '				<h3 class="item_limitDate">D-day : '+data.itemList[i].receive+'</h4>';
                html += '				<h3 class="item_location">addr : '+data.itemList[i].addr+'</h4>';
                html += '				<h3 class="seller_name">'+data.itemList[i].midx+'.'+data.itemList[i].name+' | 평균 ★ '+data.itemList[i].rvs_avg+'(총 '+data.itemList[i].rvs_totalRow+'건)</h4>';
                html += '		</div>';
                html += '	</button>';


            } // for end

            html += '</div>';
				
				itemlist_print(html, sortNum);
				sortNum++;

			} // success end

		});
		
	}

	function ratingSort(){
	
		//sortNum=1;
		sortingRvs();
	}

	/* 평점순 클릭시 ㅡ> 평점순 정렬 후 리스트 출력  */
	function allItemlist_sortRvs(){

		// 버튼 색상변경
		$('.sort_rvs').css('background-color', 'teal');
		$('.sort_reg').css('background-color', 'aquamarine');

		// 평점순 정렬기능 호출 ㅡ> 공구 리스트 다시 출력
		sortRvs(recomItems);
		recomItemlist_print(recomItems);

		sortRvs(items);
		itemlist_print(html);

	};



	/* 평점순 정렬기능 */
	function sortRvs(arr){
		arr.sort(function (a, b) { 
			return b.rvs_avg - a.rvs_avg;  
		});
	};






// 공구등록.수정.삭제 관련 (수정은 ing) ------------------------------------------------------------------
	
	/* 공구글 상세보기 */
	function showDetails(iidx, midx) {

	console.log('showDetails : '+iidx+midx);
		
		$.ajax({
			url: domain+'/items/'+iidx,
			type: 'GET',
			success: function(data){

				$('#itemView_context').html(' ');
				$('#itemlist_area').css('display','none');
				$('#itemRegForm_area').css('display','none');
				$('#itemView_area').css('display','block');
				
				var category = '';
				location_y=data.location_y;
				location_x=data.location_x;
				
				if(data.category==1){
					category='과일 / 채소';
				} else if (data.category==2){
					category='육류 / 해산물';
				} else {
					category='생필품 / 기타'; 
				}
				
				var imgPath = domain+'/upload/'+data.photo;				
				console.log('imgPath : '+imgPath);
				           
            
				var html = '';
					
				html += '<div class="w3-container" class="ItemView" style="margin-top:65px;" >';
				html += '	<h2 class="w3-xlarge text-purple"><b>Item View</b></h2>';
				html += '	<hr style="width:50px;border:5px solid purple;"  class="w3-round">';
				//html += '	<img class="detail_img" src="/order/upload/'+data.photo+'" style="">';
				html += '	<img class="detail_img" width=80% style="" src="'+imgPath+'" alt="'+imgPath+'">';
				
				html += '<div class="itemView_detail">';
				
				html +='		<h2 style="margin-top: 20px; padding-left: 20px;">'+data.title;
				html +='		</h2>';
				html +='		<h4>';
				html +='			<span class="element" style="color: #8c5394; font-weight: bold; ">'+data.name+' 평균 : '+data.rvs_avg+' 점 / 총 평점 : '+data.rvs_totalRow+'</span>';
				html +='			<span class="element" style="color: grey;">조회수 : '+data.view_count+'</span>';
				html +='		</h4>';
				html +='		<h4 style="color: gray;"><span class="element"> 분류 : '+category+'</span></h4>';
				html +='		<h3>';
				html +='			<span class="element" style="color: red;">가격 : ￦ '+data.price+'</span>';
				html +='			<span class="element">수령날짜 : '+data.receive+' 까지</span>';
				html +='		</h3>';
				html +='		<h3>';
				html +='			<span class="element" style="color: red; ">모집정원 : '+data.count_m+' 명</span>';
				html +='			<span class="element">대기정원 : '+(data.count_w)+' 명</span>';
				html +='		</h3>';
				html +='		<div class="item_content">';
				html +='			<span>'+data.content+'</span>';
				html +='		</div>';
				html +='		<div class="map_area">';
				html +='			<div id="show_map" style="width:400px; height:300px; padding-top: 20px; border-radius: 20px 20px 20px 20px;"></div> ';
				html +='		</div>';
				// 만약에 로그인한 사람이 작성자가 아니면, 참여신청버튼 활성화
				if(loginMidx != data.midx){
					html += '			<input type="button" class="btn_join request_btn btn_search_category" value="요청보내기" onclick="regOrder('+loginMidx+','+data.iidx+')">';
				}
					html += '		<input type="button" class="btn_itmelist btn_search_category" value="목록으로" onclick="allItemlist()">'; 

				

				// 만약에 로그인한 사람이 작성자와 같으면, 글수정. 글삭제 활성화
				if(loginMidx == data.midx){
					html += '			<input type="button" class="btn_itmelist btn_search_category" value="글수정" onclick="editItem('+data.iidx+')">'; 
					html += '			<input type="button" class="btn_itmelist btn_search_category" value="글삭제" onclick="delItem('+data.iidx+')">'; 
				}
				
				html += '</div>';
				html += '</div>';
				
				$('#itemView_context').html(html);
				console.log('showDetails : '+iidx+' '+midx);
				
				//20200920		
				getMap(location_y, location_x, data.addr);
				
				console.log('--showDetails : '+iidx+midx);
				// 해당글의 댓글 리스트 호출 및 출력
				getComment(iidx);

				// 댓글등록 폼 출력
				regCommentForm(iidx);
				
				$('.commentlistWrap').css('display','block');
				

				
			}


		});

	} //itemView() end

	
	/* 공구 삭제 */
	function delItem(iidx){

		if(confirm("정말 삭제하시겠습니까?")){
			$.ajax({
				url: domain+'/items/'+iidx,
				type: 'DELETE',
				success: function(data){
					allItemlist();
					myitem(midx);
					
				}

			});
		};
			
	};
	
	/* 가람이 Js 추가 (공구 등록 form) */
	
		// 1000 단위 쉼표입력 함수
	 function commaSeparateNumber(val){
	  while (/(\d+)(\d{3})/.test(val.toString())){
	    val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
	  }
	  return val;
	}
	$('#price').focusout(function(){
	  $(this).val(commaSeparateNumber($(this).val()));
	});
	
	// 공구 등록 form 활성화 함수
	function openRegForm(){
	      document.getElementById('regArea').style.display='block';
	  }
	// 공구 등록 form 비활성화 함수
	function closeRegForm(){
	  document.getElementById('regArea').style.display='none';
	  document.getElementById('regForm').reset();
	}
	function reload(){
	  $('#food').load(location.href + " #food");
	}
	
	// 공구 등록 함수
	function regSubmit(){
	
	
		console.log('arr_result.y : '+arr_result.y);
		console.log('arr_result.x : '+arr_result.x);
	
	  var regFormData = new FormData();
	  regFormData.append('title', $('#title').val());
	  regFormData.append('price', parseInt($('#price').val().replace(/,/g, ""), 10));
	  regFormData.append('count_m', $('#count_m').val());
	  regFormData.append('count_w', $('#count_m').val()*2);
	  regFormData.append('receive', moment($('#receive').val()).format('YYYY-MM-DD HH:mm:ss'));
	  regFormData.append('addr', $('#addr').val());
	  //regFormData.append('location', $('#location').val());
	  regFormData.append('location_y', arr_result.y);
	  regFormData.append('location_x', arr_result.x);
	  regFormData.append('content', $('#content').val());
	  // 파일 첨부
	  if($('#photo')[0].files[0] != null){
	    regFormData.append('photo', $('#photo')[0].files[0]);
	  }
	  regFormData.append('state', $('input[name=state]:checked').val());
	  regFormData.append('view_count', 0);
	  regFormData.append('category', $('#category').val());
	  regFormData.append('midx', $('#midx').val());
	  
	  $.ajax({
	    url : domain+'/items',
	    type : 'POST',
	    processData : false,
	    contentType : false,
	    data : regFormData,
	    success : function(data){
	      swal({
	        text: "등록되었습니다",
	        icon: "success",
	        button: "확인"
	      });
	
	      
	      //20200921 reload();
	      //20200921 getItemListView();
	      allItemlist();  // 전체 공구리스트 출력
	      closeRegForm();
	    },  // success end
	    error : function(data){
	      console.log(data);
	
	      swal({
	        title: "무언가 잘못된것 같네요",
	        text: "확인 후 다시 시도해주세요.",
	        icon: "warning",
	        button: "확인"
	      });
	    } // error end
	  })  // ajax end
	};  // regSubmit function end 
	
	
/*지도에 마커표시 */

   
   //지도를 표시할 div
   function getMap(location_y, location_x, addr){      
      var mapContainer = document.getElementById('show_map'),  
       mapOption = { 
           center: new kakao.maps.LatLng(location_y, location_x), // 지도의 중심좌표
           level: 4 // 지도의 확대 레벨
       };
      
      var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
      
      var imageSrc = 'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png', // 마커이미지의 주소입니다    
       imageSize = new kakao.maps.Size(31, 35), // 마커이미지의 크기입니다
       imageOption = {offset: new kakao.maps.Point(17, 59)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
         
      //마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
       markerPosition = new kakao.maps.LatLng(location_y, location_x); // 마커가 표시될 위치입니다
      
      //마커를 생성합니다
      var marker = new kakao.maps.Marker({
       position: markerPosition, 
       image: markerImage // 마커이미지 설정 
      });
      
       marker.setImage(markerImage);
      //마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);  
      
      
      var iwContent = '<div style="padding:5px;"><span style="text-align: center; font-weight: bold;">&nbsp&nbsp&nbsp&nbsp&nbsp물품 수령 장소 </span><br><a href="https://map.kakao.com/link/map/'+addr+','+location_y+','+location_x+'" style="color:#8C5394; font-weight: bold; text-decoration: none;" target="_blank">지도 크게보기</a> <a href="https://map.kakao.com/link/to/'+addr+','+location_y+','+location_x+'" style="color:#8C5394; font-weight: bold; text-decoration: none;" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
       iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다
      
      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
          position : iwPosition, 
          content : iwContent 
      });
        
      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker); 
   
   }