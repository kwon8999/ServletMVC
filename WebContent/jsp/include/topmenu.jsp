<%@page import="parsing.ParsingXmlNow"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	ParsingXmlNow now = new ParsingXmlNow();
	String weather = now.ParsingXmlnow();
%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("button[name=LogBtn]").click(function(){	
			if($("input[name=uid]").val() == ""){alert("아이디를 입력하세요"); $("input[name=uid]").focus(); return;} 
			if($("#pwd").val() == ""){alert("비밀번호를 입력하세요"); $("#pwd").focus(); return;}
			
//			$("#frm").attr({action:'/login', method:'post'}).submit();
			$.ajax({
				type: "post",
				url: '/login',
				data:{
					"uid" : $("input[name=uid]").val(),
					"pwd" : $("#pwd").val()
				},
				dataType: "text",
				success: function(data){
					if(data.trim()=="true"){
						alert(data.trim());
						alert('Welecome');
						location.href ='/';
					}else{
						alert(data.trim());
						alert('Login fail');
						location.href ='/';
					}
				},
				error: function(){
					alert('Ajax Error');
				}
			});
		});
		
		$("button[name=useradd]").click(function(){	
			
			var w = 604;    
			var h = 490;    
			var LeftPosition=(screen.width-w)/2;
			var TopPosition=(screen.height-h)/2;
			window.open('/jsp/UserForm.jsp?tab=1','_blank','width='+w+', height='+h+', top='+TopPosition+', left='+LeftPosition+', resizable=no, scrollbars=no');
		});
		
		$(":input").keypress(function(event){
			if(event.keyCode==13){
				
				if($(this).attr('name') == $("input[name=uid]").attr('name')){

					if($("input[name=uid]").val() == ""){alert("아이디를 입력하세요"); $("input[name=uid]").focus(); return;} 
					$("#pwd").focus();
				}

				if($(this).attr('name') == $("#pwd").attr('name')){

					if($("#pwd").val() == ""){alert("비밀번호를 입력하세요"); $("#pwd").focus(); return;}
					$("button[name=LogBtn]").trigger("click");
				}
			}
		});
		
		$("#Upuse").mouseover(function(){
			$(this).css('cursor','pointer');
		});
		
		$("#Upuse").click(function(){
			var w = 604;    
			var h = 490;    
			var LeftPosition=(screen.width-w)/2;
			var TopPosition=(screen.height-h)/2;
			window.open('/jsp/UserForm.jsp?tab=2','_blank','width='+w+', height='+h+', top='+TopPosition+', left='+LeftPosition+', resizable=no, scrollbars=no');
		});
	});
	
</script>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="/">홈으로</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="/jsp/Map.jsp?chk=2">지도 <span class="sr-only"></span></a></li>
	        <li><a href="http://onenable.tumblr.com/Onepiece" target="_blank">원나블 <span class="sr-only"></span></a></li>
	        <li><a href="http://sports.news.naver.com/sports/index.nhn?category=worldfootball" target="_blank">축구 <span class="sr-only"></span></a></li>
	        <li><a href="/jsp/Weather.jsp">현재날씨 : <%=weather %>  
			<span class="sr-only"></span></a></li>
	      </ul>
	      <%
	      if(session.getAttribute("nick") == null){
	      %>
	      	<form id="frm" class="navbar-form navbar-right" role="search">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="ID" name="uid">
	          <input type="password" class="form-control" placeholder="Password" name="pwd" id="pwd">
	        </div>
	        <button type="button" class="btn btn-default" name="LogBtn">로그인</button>
	        <button type="button" class="btn btn-default" name="useradd">회원가입</button>
	      	</form>	
	      <%	
	      }
	      %>
	      <ul class="nav navbar-nav navbar-right">
	     <%
	     if(session.getAttribute("nick") != null){%><li id="Upuse" style="margin-top: 15px;"><strong><%= session.getAttribute("nick") %></strong>님 환영합니다</li><%}
	     %>
	      </ul>
	    </div>
	  </div>
</nav>