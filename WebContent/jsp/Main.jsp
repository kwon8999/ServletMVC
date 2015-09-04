<%@page import="Dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	@SuppressWarnings("unchecked")
	List<BoardDto> list = (List<BoardDto>)request.getAttribute("list");
	System.out.println("list : " + list.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="/css/div.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript">
	$(document).ready(function(){
		
		$("button[name=write]").click(function(){
			var se = '<%=session.getAttribute("nick")%>';
			if(se!='null'){
				location.href = "/jsp/InsertBoard.jsp";
			}else{
				alert("로그인을 먼저 하셔야 합니다."); $("input[name=uid]").focus(); return;
			}
		});
		
		$("button[name=ExcelDownload]").click(function(){
			$("#xls").attr({action:'/ExcelDownload', method:'post'}).submit();
		});
		
	});
</script>
</head>
<body>
	<div class="top">
		<%@ include file="/jsp/include/topmenu.jsp" %>
	</div>
	<div class="map">
		<jsp:include page="/jsp/Map.jsp">
			<jsp:param value="1" name="chk"/>
		</jsp:include>
	</div>
	<div class="center">
		<div class="in-btn">
			<button type="button" class="btn btn-primary" name="write">글쓰기</button>
		</div>
		<div class="table-responsive">
			<table class="table table-hover">
				<th>번호</th><th>제목</th><th>글쓴이</th><th>작성일</th><th>조회</th>
				<% if(list.size()!=0){
					for(BoardDto dto : list ){	
				%>
					<tr>
						<td><%=dto.getWordNum() %></td>
						<td><%=dto.getTitle() %></td>
						<td><%=dto.getWordNick() %></td>
						<td><%=dto.getWordDate() %></td>
						<td><%=dto.getRec() %></td>
					</tr>
					<%} %>
				<% }else{%>
					<tr>
						<td colspan="5">글이 없습니다</td>
					</tr>
				<%} %>
			</table>
		</div>
		<form id="xls">
			<div>
				<button type="button" class="btn btn-warning" name="ExcelDownload" >Download</button>&nbsp;
				<button type="button" class="btn btn-warning" name="ExcelUpload" >Upload</button>
			</div>
		</form>
	</div>
	<footer>
		Starting coding 2015.08.17
	</footer>
</body>
</html>