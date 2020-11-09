<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<form id="regMemberForm" onsubmit="return false;">
		<input type="hidden" name="id" id="id" value="${userInfo.id}"><br>
		<input type="hidden" name="name" id=name value="${userInfo.name}"><br>
		주소 <input type="text" name="addr" id="addr"><br>
		전화번호 <input type="text" name="phone" id="phone"><br>
		<input type="submit" value="회원가입" onclick="regMemberSubmit();"><br>
	</form>
	
	<script>
	
		var domain = "http://localhost:8081";
		
		
		function regMemberSubmit(){
			
			var regFormData = new FormData();

			regFormData.append('id', $('#id').val());
			regFormData.append('name', $('#name').val());
			regFormData.append('addr', $('#addr').val());
			regFormData.append('phone', $('#phone').val());
			
			$.ajax({
				url : domain+'/members',
				type : 'POST',
				processData: false, // File 전송시 필수
				contentType: false, // multipart/form-data
				data : regFormData,
				success : function(data){
					console.log(data);
					alert("회원가입 완료");
					location.href = "/";

				},

				Error: function(error){
					alert('회원가입 정상처리 에러');
				}
				
			});
		};
	
	</script>

</body>
</html>