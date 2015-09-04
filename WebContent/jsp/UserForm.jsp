<%@page import="util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String name = "";
	String nick = "";
	String tel = "";
	String email = "";
	String uid = "";
	if(session.getAttribute("uid")!=null){
		 name = CommonUtil.Nullchk((String)session.getAttribute("name"));
		 nick = CommonUtil.Nullchk((String)session.getAttribute("nick"));
		 tel = CommonUtil.Nullchk((String)session.getAttribute("tel"));
		 email = CommonUtil.Nullchk((String)session.getAttribute("email"));
		 uid = (String)session.getAttribute("uid");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="../css/div.css">
<script type="text/javascript">

function invaile(){
	var regType1 = /^[A-Za-z0-9+]*$/;
	var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;  
	
	if($("input[name=inputId]").val()==""){ alert('아이디를 입력하세요.'); $("input[name=inputID]").focus(); return;}
	if('<%=session.getAttribute("nick")%>'==null){
		if($("#inputPwd").val()==""){ alert('비밀번호를 입력하세요.'); $("#inputPwd").focus(); return;}
	}

	if($("input[name=inputNm]").val()==""){ alert('이름을 입력하세요.'); $("input[name=inputNm]").focus(); return;}
	if($("input[name=inputTel]").val()==""){ alert('전화번호를 입력하세요.'); $("input[name=inputTel]").focus(); return;}
	if($("input[name=inputEmail]").val()==""){ alert('이메일을 입력하세요.'); $("input[name=inputEmail]").focus(); return;}
	if($("input[name=inputNick]").val()==""){ alert('닉네임을 입력하세요.'); $("input[name=inputNick]").focus(); return;}

	if('<%=session.getAttribute("nick")%>'==null){
		if(regType1.test($("input[name=inputId]").val())===false) { alert('아이디는 4~12자리 또는 영문으로만 가능합니다.'); $("input[name=inputID]").focus(); return;}
		if($("#inputPwd").val().length < 7){ alert("비밀번호를 6자리 이상 입력하세요."); $("#inputPwd").focus(); return;}
	}

	if(regex.test($("input[name=inputEmail]").val())===false){ alert('이메일 형식이 옳바르지 않습니다.'); $("input[name=inputEmail]").focus(); return;}

	return true;
}

	$(document).ready(function(){
		
		$("#Okadd").click(function(){
			
			if(!invaile()) return;

			$("#frm").attr({method:'post', action:'/Adduser'}).submit();
		});
		
		$("#Okupdate").click(function(){
			
			if(!invaile()) return;

			$("#frm").attr({method:'post', action:'/Update'}).submit();
		});
		
		
		
		
		$("#cancel").click(function(){
			window.close();
		});
	});
</script>
<title>회원가입</title>
</head>
<body>
	<div class="userAdd">
		<div>
			<h2>회원 가입</h2>
		</div>
		<form id="frm" class="form-horizontal">
			<% if(session.getAttribute("nick")==null){%>
  			<div class="form-group">
 				<div class="col-sm-6">
    				<input type="text" class="form-control" name="inputId" placeholder="아이디" >
   				</div>
  			</div>
  			
  			<div class="form-group">
   				<div class="col-sm-6">
     				<input type="password" class="form-control" name="inputPwd" id="inputPwd" placeholder="비밀번호">
   				</div>
  			</div>
  			<% }else{%>
  			<input type="hidden" name="uid" value="<%=uid%>">
  			<%} %>
  			<div class="form-group">
   				<div class="col-sm-6">
     				<input type="text" class="form-control" name="inputNm" id="inputPwd" placeholder="이름" value="<%=name%>" >
   				</div>
  			</div>
  			<div class="form-group">
   				<div class="col-sm-6">
     				<input type="text" class="form-control" name="inputTel" id="inputTel" onkeypress="if ((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" style="ime-mode:disabled" maxlength="11" placeholder="전화번호" value="<%=tel%>" >
   				</div>
  			</div>
  			<div class="form-group">
   				<div class="col-sm-6">
     				<input type="text" class="form-control" name="inputEmail" id="inputEmail" placeholder="이메일" value="<%=email%>" >
   				</div>
  			</div>
  			<div class="form-group">
   				<div class="col-sm-6">
     				<input type="text" class="form-control" name="inputNick" id="inputNick" placeholder="닉네임" value="<%=nick%>">
   				</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<div class="checkbox">
        				<label>
          					<input type="checkbox"> 이메일 수신
        				</label>
      				</div>
    			</div>
  			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<% if(session.getAttribute("nick")==null){%>
					<button type="button" class="btn btn-default" id="Okadd">가입하기</button>
				<% }else{%>
					<button type="button" class="btn btn-default" id="Okupdate">수정하기</button>
				<% }%>
			    	<button type="button" class="btn btn-default" id="cancel">취소</button>
			    </div>
			</div>
		</form>
	</div>	
</body>
</html>