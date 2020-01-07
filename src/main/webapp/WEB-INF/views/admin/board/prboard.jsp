<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	$(document).ready(function() {
		$("a[class='page-link']").on("click", function() {
			$.ajax({
				type:"post",
				url: $(this).attr("href"),
				data: {
					"category" : 2,
				},
				datatype: "html",
				success : function(res){
					console.log(res);
					$("#prboard").html(res);
				},
				error: function(e){
				console.log(e);
				}
			});
			
			return false;
		});
	});
</script>

<script type="text/javascript">
	function checkedAll(){
		// checkbox들
	   var $checkboxes=$("input:checkbox[name='checkRow']");
	
	   // checkAll 체크상태 (true:전체선택, false:전체해제)
	   var check_status = $("#checkAlls").is(":checked");
	   
	   if( check_status ) {
	      // 전체 체크박스를 checked로 바꾸기
	      $checkboxes.each(function() {
	         this.checked = true;   
	      });
	   } else {
	      // 전체 체크박스를 checked 해제하기
	      $checkboxes.each(function() {
	         this.checked = false;   
	      });
	   }
	}
</script>

<!-- pr게시판 -->
	<div class="container" style="margin-top: 50px;">
		<div class="innercon2">
			<div class="src" style="text-align: right;">
				<form action="/admin/main" method="get">
					<select name="searchType"  style="padding-top: 4px; padding-bottom: 5px;">
						<option value="title">제목</option>
						<option value="usernick">닉네임</option>
						<option value="prname">게시판 유형</option>
					</select>
					<input type="text" name="search" id="search"/>
					<button id="btnSearch" class="btn btn-secondary" style="text-align: right;">검색</button>
				</form>
			</div>
			<br>
			<form action="" method="get">
				<table class="table table-hover">
					<thead> 
						<tr class = "info" style="text-align: center;" >
							<th style="width: 5%">
								<input type="checkbox" id="checkAlls" name="checkAlls" onclick="checkedAll();"/>
							</th>
							<th style="width: 10%">글번호</th>
							<th style="width: 45%">제목</th>					
							<th style="width: 10%">작성자</th>
							<th style="width: 10%">조회수</th>
							<th style="width: 20%">작성일</th>
						</tr>
					</thead>
					
					<tbody style="text-align: center;">
						<c:forEach items="${prlist }" var="prlist">
<%-- 						/admin/prboard?boardno=${prlist.boardno } --%>
						
							<tr onclick="location.href='/prboard/view?boardno=${prlist.boardno }';">
								<td><input type="checkbox" name="checkRow" value="${prlist.boardno  }"/></td>
								<td>${prlist.boardno }</td>
								<td>${prlist.title }</td>
								<td>${prlist.usernick }</td>
								<td>${prlist.views }</td>
								<td>${prlist.writtendate }</td>
							</tr>
						</c:forEach>
					</tbody>
					
				</table>
				<button class="btn btn-secondary">삭제</button>
			</form>
		</div>
		
		<jsp:include page="/WEB-INF/views/admin/layout/prpaging.jsp"/>
	</div> <!-- container -->
