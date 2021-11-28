<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">   
$(document).ready(function(){
	//form 서브밋
$("form").on("submit", function() {
 // id, 비밀번호가 0인경우 에러창 띄우고 전송 금지
var id=$("#userid").val();
var pw=$("#passwd").val();
if(id.length==0){
	alert("아이디 입력 안됨");
	$("#userid").focus();
	event.preventDefault();
}
if(pw.length==0){
	alert("비밀번호 입력 안됨");
	$("#passwd").focus();
	event.preventDefault();
}
	});
//비번확인
$("#passwd2").on("keyup", function() {
//키 이벤트 발생시 패스워드 일치여부 검사
var pw1=$("#passwd").val();
var pw2=$("#passwd2").val();
if (pw1!=pw2) {
	$("#result2").text("입력된 비밀번호와 다릅니다.")
} else{
	$("#result2").text("입력된 비밀번호와 동일합니다.")
}
/*
$("#passwd2").on("keyup",function(){
	var passwd = $("#passwd").val();
	var mesg = "비번 불일치";
	if(passwd == $(this).val()){
		mesg = "비번 일치";
	}
	$("#result2").text(mesg);
});
*/
});

$("#semail").on("change", function() {
	var sel = $("#email2");
	console.log($(this));
	sel.val($(this).val());
});

//아이디 확인
$("#userid").on("keyup", function() {
	$.ajax({
		type:"get",
		url: "idDuplicateCheck", 
		dataType:"text", // 받아올 응답 데이터 타입
		data:{
			userid: $("#userid").val() // controller로 보낼 data. userid = keyup event 발생할 입력하는 값
		},
		success: function(data, status, xhr) { 
			console.log(data);
			$("#result").text(data);
		},
		error: function(xhr,status,e) {
			console.log("error",e);
		}
	});
});
});
  </script> 
<form action="memberAdd" method="get">
*아이디:<input type="text" name="userid" id="userid">
<span id="result"></span>
<br> 
*비밀번호:<input type="text" name="passwd" id="passwd"><br> 
비빌번호확인:<input type="text" name="passwd2" id="passwd2">
<span id="result2"></span>
<br> 
이름:<input type="text" name="username"><br> 
<input type="text" name="post" id="sample4_postcode" placeholder="우편번호">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
<input type="text" name="addr2" id="sample4_jibunAddress" placeholder="지번주소">
<span id="guide" style="color:#999"></span>
<br>
전화번호:<select name="phone1">
  <option value="010">010</option>
  <option value="011">011</option>
</select>-
<input type="text" name="phone2" >-<input type="text" name="phone3" >
<br>
이메일:<input type="text" name="email1" id="email1">@
       <input type="text" name="email2" id="email2" placeholder="직접입력">
       <select  id="semail">
        <option value="daum.net">daum.net</option>
        <option value="naver.com">naver.com</option>
       </select>
<br>
<input type="submit" value="회원가입">
<input type="reset" value="취소">
</form>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script> /* form 바깥에 script를 붙여넣음 */
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('sample4_roadAddress').value = fullRoadAddr;
                document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>