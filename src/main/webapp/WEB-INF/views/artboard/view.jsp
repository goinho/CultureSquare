<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />  
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		
// 		if('${emailcheck}' == 'N')
		
		$form = $("<form>").attr({
			action: "/reply/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"boardno",
				value:"${view.boardno }"
			})
		).append(
			$("<input>").attr({
				type:"hidden",
				name:"userno",
				value:"${LoginUser.userno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "recontents")
				.css("display", "none")
				.text($("#recontents").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	});
	
});
//댓글 삭제
function deleteReply(replyno) {
	$.ajax({
		type: "post"
		, url: "/reply/delete"
		, dataType: "json"
		, data: {
			replyno: replyno
		}
		, success: function(data){
			if(data.success) {
				console.log(replyno);
				$("[data-replyno='"+replyno+"']").remove();
				
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("error");
		}
	});
}

</script>	
<script type="text/javascript">
$(document).ready(function() {
	
	var money;
	
	//로그인 안했을 경우 후원하기 버튼 누르면 모달
	$("#noLoginDonationbtn").click(function() {
		$("#artNotLoginModal .content").text('로그인 후 후원이 가능합니다.');
		$("#artNotLoginModal").modal({backdrop: 'static', keyboard: false});
		return false;
	});
	
	//로그인 후 후원하기버튼 클릭 시 모달
	$("#donationbtn").click(function() {
		var msg = '결제가 완료되었습니다.';
  		msg += '<div class="custom-control custom-radio">'
  		msg +=    '<input type="radio" class="custom-control-input" id="1000won" name="donprice" value="1000">';
  		msg +=     '<label class="custom-control-label" for="1000won">1000원</label>';
  		msg +=   '</div>';
  		msg +=   '<div class="custom-control custom-radio">';
  		msg +=      '<input type="radio" class="custom-control-input" id="5000won" name="donprice" value="5000">';
  		msg +=     '<label class="custom-control-label" for="5000won">5000원</label>';
  		msg +=   '</div>';
  		msg +=   '<div class="custom-control custom-radio">';
  		msg +=     '<input type="radio" class="custom-control-input" id="10000won" name="donprice" value="10000">';
  		msg +=      '<label class="custom-control-label" for="10000won">10000원</label>';
  		msg +=  '</div>';
  		msg +=  '<div class="custom-control custom-radio">';
  		msg +=      '<input type="radio" class="custom-control-input" id="etc" name="donprice" value="">';
  		msg +=      '<label class="custom-control-label" for="etc">기타</label>';
  		msg +=     '<input id = "etcinput" name = "etcinput"  class="form-control"  disabled="disabled"/>';
  		msg +=  '</div>';
  		
  		$("#donationModal .content").html(msg);
		$("#donationModal").modal({backdrop: 'static', keyboard: false});
	});
	
	//후원 모달에서 후원하기 눌렀을때
	$("#donation").click(function() {
		
		var donChecked = $("input[name=donprice]:checked").val();
		var inputCost = $('#etcinput').val();
		
		//숫자 정규식
		var regexp = /^[0-9]*$/
		
		//화면에 나온 금액 입력
		if(donChecked == '1000' || donChecked == '5000' || donChecked == '10000'){
			
			// 라디오 초기화
			$('input[name="donprice"]').removeAttr('checked');
			console.log(donChecked + "입니다.");
			
		    	 var IMP = window.IMP; // 생략가능
		         IMP.init('imp21286391'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		         var msg;
		         
		         IMP.request_pay({
//		              pg: 'inicis', // version 1.1.0부터 지원.
		             pg: 'kakaopay', // version 1.1.0부터 지원.
		             /* 
		                 'kakao':카카오페이, 
		                 html5_inicis':이니시스(웹표준결제)
		                     'nice':나이스페이
		                     'jtnet':제이티넷
		                     'uplus':LG유플러스
		                     'danal':다날
		                     'payco':페이코
		                     'syrup':시럽페이
		                     'paypal':페이팔
		                 */
		             pay_method: 'card',
		             /* 
		                 'samsung':삼성페이, 
		                 'card':신용카드, 
		                 'trans':실시간계좌이체,
		                 'vbank':가상계좌,
		                 'phone':휴대폰소액결제 
		             */
		             merchant_uid: 'merchant_' + new Date().getTime(),
		             /* 
		              */
		             name: '주문명: 카카오결제테스트',
		             //결제창에서 보여질 이름
		             amount: donChecked, 
		             
		             //가격 
		             buyer_email: 'iamport@siot.do',
		             buyer_name: '구매자이름',
		             buyer_tel: '010-1234-5678',
		             buyer_addr: '서울특별시 강남구 삼성동',
		             buyer_postcode: '123-456',
		             m_redirect_url: 'https://www.yourdomain.com/payments/complete'
		             /*  
		                 모바일 결제시,
		                 결제가 끝나고 랜딩되는 URL을 지정 
		                 (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
		                 */
		         }, function (rsp) {
		             console.log(rsp);
		             if (rsp.success) {
		            	 
		            	 money = rsp.paid_amount;
		            	 
		                 var msg = '결제가 완료되었습니다.';
		                 msg +='<br>';
		                 msg += '고유ID : ' + rsp.imp_uid;
		                 msg +='<br>';
		                 msg += '상점 거래ID : ' + rsp.merchant_uid;
		                 msg +='<br>';
		        		 msg += '후원자 : ${usernick}'
		        		 msg +='<br>';
		        		 msg += '후원받는 사람 : ${writer.usernick}'
			        	 msg +='<br>';
		                 msg += '결제 금액 : ' + rsp.paid_amount +"원";
		                 msg +='<br>';
		                 
		                 $(".content").html(msg);
				         $("#donationSuccess").modal({backdrop: 'static', keyboard: false});
		             } else {
		                 var msg = '결제에 실패하였습니다.';
		                 msg +='<br>';
		                 msg += '에러내용 : ' + rsp.error_msg;
		                 $("#donationFail .content").html(msg);
				         $("#donationFail").modal({backdrop: 'static', keyboard: false});
		             }
		         })
			
			
		}
		//기타 금액 입력
		else{
			//빈칸 입력
			if(inputCost ==""){
				//후원 에러 모달 호출
				$("#donationErrorModal .content").html("빈칸은 입력할 수 없습니다.");
				$("#donationErrorModal").modal({backdrop: 'static', keyboard: false});
			}
			
			//숫자가 아닌 경우
			else if(!regexp.test(inputCost)){
				//숫자 입력하라는 모달 호출
				$("#donationNumberModal .content").html("숫자만 입력 가능합니다.");
				$("#donationNumberModal").modal({backdrop: 'static', keyboard: false});
			}
			
			// 숫자 입력 결제 시스템 호출
			else{
			    	 var IMP = window.IMP; // 생략가능
			         IMP.init('imp21286391'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
			         var msg;
			         
			         IMP.request_pay({
//			              pg: 'inicis', // version 1.1.0부터 지원.
			             pg: 'kakaopay', // version 1.1.0부터 지원.
			             /* 
			                 'kakao':카카오페이, 
			                 html5_inicis':이니시스(웹표준결제)
			                     'nice':나이스페이
			                     'jtnet':제이티넷
			                     'uplus':LG유플러스
			                     'danal':다날
			                     'payco':페이코
			                     'syrup':시럽페이
			                     'paypal':페이팔
			                 */
			             pay_method: 'card',
			             /* 
			                 'samsung':삼성페이, 
			                 'card':신용카드, 
			                 'trans':실시간계좌이체,
			                 'vbank':가상계좌,
			                 'phone':휴대폰소액결제 
			             */
			             merchant_uid: 'merchant_' + new Date().getTime(),
			             /* 
			              */
			             name: '주문명: 카카오결제테스트',
			             //결제창에서 보여질 이름
			             amount: inputCost, 
			             
			             //가격 
			             buyer_email: 'iamport@siot.do',
			             buyer_name: '구매자이름',
			             buyer_tel: '010-1234-5678',
			             buyer_addr: '서울특별시 강남구 삼성동',
			             buyer_postcode: '123-456',
			             m_redirect_url: 'https://www.yourdomain.com/payments/complete'
			             /*  
			                 모바일 결제시,
			                 결제가 끝나고 랜딩되는 URL을 지정 
			                 (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
			                 */
			         }, function (rsp) {
			             console.log(rsp);
			             if (rsp.success) {
			            	 
			            	 money = rsp.paid_amount;
			            	 
			                 var msg = '결제가 완료되었습니다.';
			                 msg +='<br>';
			                 msg += '고유ID : ' + rsp.imp_uid;
			                 msg +='<br>';
			                 msg += '상점 거래ID : ' + rsp.merchant_uid;
			                 msg +='<br>';
			        		 msg += '후원자 : ${usernick}'
			        		 msg +='<br>';
			        		 msg += '후원받는 사람 : ${writer.usernick}'
				        	 msg +='<br>';
			                 msg += '결제 금액 : ' + rsp.paid_amount +"원";
			                 msg +='<br>';
			                 $("#donationSuccess .content").html(msg);
					         $("#donationSuccess").modal({backdrop: 'static', keyboard: false});
			             } else {
			                 var msg = '결제에 실패하였습니다.';
			                 msg +='<br>';
			                 msg += '에러내용 : ' + rsp.error_msg;
			                 
			                 $("#donationFail .content").html(msg);
					         $("#donationFail").modal({backdrop: 'static', keyboard: false});
			             }
			         })
			}
			
			// 값 초기화
			$('#etcinput').val("");
			$('input[name="donprice"]').removeAttr('checked');
			console.log("요긴 기타 버튼 : " +  inputCost);
		}
			
	});
	   $("#donationModal").on("click","input:radio[name=donprice]", function(){
		   var donChecked = $(this).val();
// 		   var donChecked = $("input[name=donprice]:checked").val();
			console.log(donChecked);
			if(donChecked == '1000'){
				$('#donationModal #etcinput').val("");
				$("#donationModal #etcinput").attr("disabled", true);
			}else if(donChecked == '5000'){
				$('#donationModal #etcinput').val("");
				$("#donationModal #etcinput").attr("disabled", true);
			}else if(donChecked == '10000'){
				$('#donationModal #etcinput').val("");
				$("#donationModal #etcinput").attr("disabled", true);
			}else{
				console.log(1)
				$("#donationModal #etcinput").attr("disabled", false);
			}
	   })
	   
	   //후원 하고 확인 버튼 눌렀을때 DB에 저장
	   $("#donationSuccessModalBtn").click(function() {

		 //후원 처리 컨트롤러로 이동
		 location.href="/artboard/donation?donprice="+money+"&usernick=${usernick}&boardno=${ view.boardno}";
		   
		   console.log("후원자 : ${usernick}");
		   console.log("금액 : " + money);
		   console.log("게시글 번호 : ${ view.boardno}" );
	   })
});
</script>

<style type="text/css">
#view_head{
	background-color: #343a40;
	border: 1px solid black;
	max-width: 95%;
	height: 45px;
	text-align: center;
	color: white;
	padding: 6px;
}
#writer_head{
	background-color: #343a40;
	border: 1px solid black;
	max-width: 95%;
	height: 45px;
	color: white;
	padding: 6px;
	font-size: 25px;
}
.con_left{
	width: 68%;
	border: 1px solid black;
	float: left;
}
.con_right{
	width: 32%;
	border: 1px solid black;
	float: right;
}
#view_writer{
	border: 1px solid black;
	max-width: 95%;
    height: 35px;
	padding: 6px;

}
#view_content{
	border: 1px solid black;
	max-width: 95%;
	height: auto;
}
#writer_nick{
	width:30%;
	float: left;
}

#viewcount{
	width: 20%;
	float:right;
}
#write_date{
	width: 30%;
	float:right;
}

#view_buttonarea{
	max-width: 95%;
	text-align: right;
}
#writer_title{
	background-color: #343a40;
	color : white;
}
#writer_photo{
    width: 100px;
    height: 100px;
	float:left;
}
#profileImg{
	width:98px;
	height:98px;
	display: block;
	margin: 0 auto;
}

#writer_info{
    width: 150px;
    height: 100px;
	float:right;
	padding: 10px;
	text-align: center;
}

#reply_head{
	background-color: #343a40;
	border: 1px solid black;
	max-width: 95%;
	height: 45px;
	color: white;
	padding: 6px;
	font-size: 25px;
}

#reply_date{
	width: 20%;
	float:right;
}
#view_recontents{
	border: 1px solid black;
	max-width: 95%;
    height: 35px;
	padding: 6px;
}
#recontents{
	float: left;
}
#deleteReplyBtn{
	font-size: 12px; 
	float: right;
}

#replyinputheader{
	background-color: #343a40;
	border: 1px solid black;
	max-width: 95%;
	height: 45px;
	color: white;
	padding: 6px;
	font-size: 25px;
}
#replyinputbody{
	border: 1px solid black;
	max-width: 95%;
    height: 80px;
	padding: 6px;
}
</style>
<%
	Date date = new Date();
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	String strdate = simpleDate.format(date);
%>
<%
	Calendar cal = Calendar.getInstance();
%>
<div class="container list-container">

<div class="h2"><h2> CALLENDAR </h2></div>
<hr>
<h3>VIEWVIEW</h3>
<br>
<div class="row">
	<div class="col-9">
		<div class="container container-fluid" style="margin-bottom: 600px">
			<div id = "view_head" class="col-xs-12 col-sm-6 col-md-8">
				<span>${view.title }</span>
			</div>
		<div id = "view_writer" class="col-xs-12 col-sm-6 col-md-8" >
			<div id = "writer_nick" class="col-md-4">
			${writer.usernick }
			</div>
			<div id = "write_date"  class="col-md-4">
			${view.writtendate }
			<div id = "viewcount">
			${view.views }
			</div>
			</div>
		</div>
		<!-- 글내용 -->
		<div id = view_content class="col-xs-12 col-sm-6 col-md-8">
			${view.contents }<br>
		<br><br><br><br><br>
		</div>
		<!-- 버튼 -->
		<div id = "view_buttonarea" class="btn col-md-4" role="group">
		
<!-- 		로그인 여부 처리 -->
		<c:choose>
			<c:when test="${not login}">
				<button type = "button" class="btn  bbc" id = "noLoginDonationbtn">후원하기</button>
			</c:when>
			<c:when test="${login}">
					<button type = "button" class="btn  bbc" id = "donationbtn">후원하기</button>
			</c:when>
		</c:choose>
			<button type = "button" class="btn  bbc" >추천</button>
			<a href="/artboard/list?bo_table=calendar&cal_year=<%= cal.get(Calendar.YEAR)%>&cal_month=<%=(cal.get(Calendar.MONTH)+1< 10) ?"0"+(cal.get(Calendar.MONTH)+1) :cal.get(Calendar.MONTH)+1%>">
			<button type = "button" class="btn  bbc" >목록</button>
			</a>
		</div>
		</div>
	</div>


</div>



<!-- 댓글 처리 -->
<div>

<hr>
		<div id="commentbody"></div>
		<%-- 댓글입력 시 이동 위치 --%>
<!-- 비로그인상태 -->
<c:if test="${not login }">
<strong>로그인이 필요합니다</strong><br>
</c:if>

<!-- 로그인상태 -->
<c:if test="${login }">
<!-- 댓글 입력 -->
<div class="col-9">
<div id = "replyinputheader">
	코멘트 남기기
</div>
<div  id = "replyinputbody" class="form-inline text-center col-9">
	<input type="hidden"  id="userno" name="userno" value="${LoginUser.userno }" />
	<input type="hidden"  id="boardno" name="boardno" value="${ view.boardno}" />
<%-- 	<input type="text" size="10" class="form-control" id="replyWriter" name = "usernick" value="${LoginUser.usernick }" readonly="readonly"/> --%>
	<textarea rows="2" cols="60" class="form-control" id="recontents" name="recontents"></textarea>
	<button id="btnCommInsert" class="btn">입력</button>
</div>	<!-- 댓글 입력 end -->
</div>
</c:if>

<br>
<!-- 댓글 리스트 -->
<!-- <table class="table table-striped table-hover table-condensed"> -->
<!-- <thead> -->
<!-- <tr> -->
<!-- 	<th style="width: 5%;">번호</th> -->
<!-- 	<th style="width: 10%;">작성자</th> -->
<!-- 	<th style="width: 50%;">댓글</th> -->
<!-- 	<th style="width: 20%;">작성일</th> -->
<!-- 	<th style="width: 5%;"></th> -->
<!-- </tr> -->
<!-- </thead> -->
<!-- <tbody id="commentBody"> -->
<%-- <c:forEach items="${replyList }" var="reply"> --%>
<%-- <tr data-commentno="${reply.replyno }"> --%>
<%-- 	<td>${reply.rnum }</td> --%>
<%-- 	<td>${reply.usernick }</td><!-- 닉네임으로 해도 좋음 --> --%>
<%-- 	<td>${reply.recontents }</td> --%>
<%-- 	<td>${reply.replydate}</td> --%>
<!-- 	<td> -->
<%-- 		<c:if test="${LoginUser.userno eq reply.userno }"> --%>
<!-- 		<button class="btn btn-default btn-xs" style="font-size: 12px;" -->
<%-- 			onclick="deleteReply(${reply.replyno });">삭제</button> --%>
<%-- 		</c:if> --%>
<!-- 	</td> -->
<!-- </tr> -->
<%-- </c:forEach> --%>
<!-- </tbody> -->
<!-- </table>	댓글 리스트 end -->

<c:forEach items="${replyList }" var="reply">
<div class="col-9">
		<div class="container container-fluid" style="margin-bottom: 20px">
			<div id = "reply_head" class="col-xs-12 col-sm-6 col-md-8">
				<span>${reply.usernick }</span>
				<div id = "reply_date" class="col-md-4">
					${reply.replydate}
				</div>
			</div>
		<div id = "view_recontents" class="col-xs-12 col-sm-6 col-md-8" >
			<div id = "recontents"  class="col-md-4" >
				${reply.recontents }
			</div>
				<c:if test="${LoginUser.userno eq reply.userno }">
					<div id = "deleteReplyBtn"  class="col-md-2"  >
					<button class="btn btn-default btn-xs" 
						onclick="deleteReply(${reply.replyno });">삭제</button>
					</div>
				</c:if>
		</div>
		<!-- 글내용 -->
		<!-- 버튼 -->
		
		</div>
	</div>
</c:forEach>


</div>	<!-- 댓글 처리 end -->



<div class="col-3">
<ul class="list-group" style="width: 300px;">
  <li id = "writer_title" class="list-group-item">
	작성자 프로필
	</li>
  <li class="list-group-item">
  <div id = "writer_photo">
	<img id="profileImg" src="/resources/img/userdefaultprofile.png" class="img-responsive img-circle"
							alt="Responsive image">
</div>
  <div id = "writer_info">${writer.usernick } 
  <br>
  		<div> <button class="btn btn-default" style="background-color: #343a40 !important; color: white !important; margin-top: 15px;">
  		팔로우</button></div>
   </div>
   </li>
</ul>
</div>


<br><br>

<!-- 게시글 후원 모달창 -->
<div class="modal fade" id="donationModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">후원하기</h4>
        <button id="inputPwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

        <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" id="donation" class="btn btn-info" data-dismiss="modal">후원하기</button>
      </div>

    </div>
  </div>
</div>


<!-- 로그인 실패시 모달창 -->
<div class="modal fade" id="artNotLoginModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">로그아웃 상태</h4>
        <button id="inputPwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="artLoginCheckBtn"class="btn btn-info" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

<!-- 후원 에러 모달창 -->
<div class="modal fade" id="donationErrorModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">후원하기 오류!</h4>
        <button id="inputPwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="donationErrorBtn"class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

<!-- 후원 숫자 에러 모달창 -->
<div class="modal fade" id="donationNumberModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">후원하기 오류!</h4>
        <button id="inputPwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="donationNumberModalBtn"class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>


<!-- 후원 성공 모달창 -->
<div class="modal fade" id="donationSuccess">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">후원완료!</h4>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="donationSuccessModalBtn"class="btn btn-info" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

<!-- 후원 실패 모달창 -->
<div class="modal fade" id="donationFail">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">후원실패!</h4>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="donationFailModalBtn"class="btn btn-info" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

</div> <!-- div_container -->

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />


