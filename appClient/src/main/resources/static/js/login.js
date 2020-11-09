/*global $, document, window, setTimeout, navigator, console, location*/
var localUrl = "http://localhost:8081"


var awsUrl = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client"

function daumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = data.address; // 최종 주소 변수

			// 주소 정보 필드 레이블 위치 변경
			$("#addr").siblings('label').addClass('active');
			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("addr").value = addr;
			
		}// oncomplete function end
	}).open();
};	// daumPostcode function end

$(document).ready(function () {

    'use strict';

    var usernameError = true,
        phoneError    = true,
        addrError     = true,
        addrDetError  = true;

    // Detect browser for css purpose
    if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
        $('.form form label').addClass('fontSwitch');
    }

    // Label effect
    $('input').focus(function () {

       $(this).siblings('label').addClass('active');
    });

    // Form validation
    $('input').blur(function () {

        // User Name
        if ($(this).hasClass('name')) {
            if ($(this).val().length === 0) {
                $(this).siblings('span.error').text('Please type your full name').fadeIn().parent('.form-group').addClass('hasError');
                usernameError = true;
            } else if ($(this).val().length > 1 && $(this).val().length < 3) {
                $(this).siblings('span.error').text('Please type at least 3 characters').fadeIn().parent('.form-group').addClass('hasError');
                usernameError = true;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                usernameError = false;
            }
        }

        // Phone
        if ($(this).hasClass('phone')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('Please type your phone number').fadeIn().parent('.form-group').addClass('hasError');
                phoneError = true;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                phoneError = false;
            }
        }
        
        // Address
        if ($(this).hasClass('addr')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('Please type your address').fadeIn().parent('.form-group').addClass('hasError');
                addrDetError = true;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                addrDetError = false;
            }
        }

        // Address Detail
        if ($(this).hasClass('addrDetail')) {
            if ($(this).val().length == '') {
                $(this).siblings('span.error').text('Please type your address details').fadeIn().parent('.form-group').addClass('hasError');
                addrDetError = true;
            } else {
                $(this).siblings('.error').text('').fadeOut().parent('.form-group').removeClass('hasError');
                addrDetError = false;
            }
        }

        // label effect
        if ($(this).val().length > 0) {
            $(this).siblings('label').addClass('active');
        } else {
            $(this).siblings('label').removeClass('active');
        }
    });

    
    // form switch
    $('a.switch').click(function (e) {
        $(this).toggleClass('active');

        if ($('a.switch').hasClass('active')) {
            $(this).parents('.form-peice').addClass('switched').siblings('.form-peice').removeClass('switched');
        } else {
            $(this).parents('.form-peice').removeClass('switched').siblings('.form-peice').addClass('switched');
        }
    });

    // Form submit
    $('form.signup-form').submit(function (event) {
        event.preventDefault();
        
        // User Name 입력 확인
        if ($('.name').val().length >= 3) {
            usernameError = false;
        }
        if ($('.addr').val().length > 0){
            addrError = false;
        }

        if (usernameError == true || phoneError == true || addrError == true || addrDetError == true) {
            $('.name, .phone, .addr, .addrDetail').blur();
        } else {
            // FromData: JavaScript에서 제공
            var regFormData = new FormData();
            regFormData.append('id', $("#email").val());
            regFormData.append('name', $("#name").val());
            regFormData.append('phone', $("#phone").val());
            regFormData.append('addr', $("#addr").val()+' '+$('#addressDet').val());
            
            $.ajax({
                url: awsUrl+"/members",
                type: "POST",
                processData: false,
                contentType: false,
                data: regFormData,
                success: function(data){

                    console.log("success data : "+data);
                    console.log("success data type: "+typeof(data));

                    if(data != 0){
                        swal({
                            title: "축하드립니다!",
                            text: "회원가입이 완료되었습니다!",
                            icon: "success",
                            button: "확인"
                        }).then(function() {
                            //location.href="/";
                            location.href=awsUrl;
                        });
                        
                    } else{
                        swal({
                            title: "무언가 잘못된것 같네요",
                            text: "확인 후 다시 시도해주세요.",
                            icon: "warning",
                            button: "확인"
                        })
                    }
                },
                error: function(err){
                    console.log("memberReg fail");
                }
            })  // ajax end
        }
    });

    // Reload page
    $('a.profile').on('click', function () {
        location.reload(true);
    });


}); // document ready end