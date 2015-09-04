<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String Ip = request.getRemoteAddr();
	System.out.println(Ip);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/div.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("button[name=cancel]").click(function(){
			if(confirm("정말 취소하시겠습니까?")==true){
				location.href = "/";
			}else{
				return;
			}
		});
		
		$("button[name=okky]").click(function(){
			if($("input[name=title]").val() == ""){ alert("제목을 입력하세요"); return;}
			if($("textarea[name=context]").val() == ""){ alert("내용을 입력하세요"); return;}
			
			if(confirm("등록하시겠습니까?")){}else{return;}
			
			$("form[name=insertForm]").attr({action:'/Boardadd', method:'post'}).submit();
				
		});
		
	});
</script>
<body>
	<%@ include file="/jsp/include/CheckSession.jsp" %>
	<%@ include file="/jsp/include/topmenu.jsp" %>
	<form name="insertForm">
		<input type="hidden" name="lvl" value="1">
		<input type="hidden" name="Ip" value="<%=Ip%>">
		<input type="hidden" name="wordNick" value="<%=session.getAttribute("nick")%>">
		<div class="InsertBoard">
			<div style="padding: 10px;">
				<input type="text" class="form-control" name="title" placeholder="제목" >
			</div>
				
	
			<div style="padding: 10px;">
				<textarea class="form-control" rows="4" name="context" placeholder="내용"></textarea>
			</div>
			<div class="form-group">
	    		<label for="exampleInputFile">파일 업로드</label>
	    		<input type="file" id="upload">
	  		</div>
			 <p>
			  	<button type="button" class="btn btn-primary" name="okky">등록</button>
	  			<button type="button" class="btn btn-default" name="cancel">취소</button>
			</p>	
		</div>
	</form>
</body>
</html>