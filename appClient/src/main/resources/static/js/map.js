/*장윤원 2020.09.20*/
/********공구 등록 시 카카오 지도 api로 주소와 좌표를 저장하고 읽어오기 위한 js***********************/
/*
1. 공구 등록 폼에서 주소검색 버튼을 클릭하고 판매처 주소를 입력한다.
2. 찾은 주소가 등록 폼 안에 주소란에 입력되는지 확인
3. 등록 submit
4. 좌표까지 DB에 잘 저장되있는지 확인
4-1. 등록되지 않는다면, 체크포인트 1 ) result.y와 result.x 변수 값 체크 : map.js와 item.js에 맵을 위한 펑션과 등록을 위한 펑션이 각각 분리되어있음
				체크포인트 2 ) Order서버 체크
*/

	// 좌표를 전역변수로 뺌
    var coords=null;
	var arr_result=null;

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };


    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("addr").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        arr_result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        coords = new daum.maps.LatLng(arr_result.y, arr_result.x);
                        // 받은 좌표 확인
                        console.log('coords : '+coords);
 						// ★arr_result.y 와 arr_result.x가 function regSubmit에서 formData에 넣어줄때 필요!!scope문제 발생..?

                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }




/*******************************/
