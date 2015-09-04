<%@page import="java.util.ArrayList"%>
<%@page import="parsing.ParsingXmlWeather"%>
<%@page import="java.util.List"%>
<%@page import="Dto.WeatherDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	ParsingXmlWeather xml = new ParsingXmlWeather();
	List<WeatherDto> list = new ArrayList<WeatherDto>();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/boardList.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<title>Weather Parsing</title>
</head>
<body>
	<div>
		<%@ include file="/jsp/include/topmenu.jsp" %>
	</div>
	<div>
		<div class="board">
		<div class="add2">
			<h1>서울날씨</h1>
		</div>
		<table class="table table-hover">
			<tr>
				<th>시간</th><th>최저기온</th><th>최고기온</th><th>날씨</th><th>호우지수</th>
			</tr>
		<%
			list = xml.ParsingWeather();
			WeatherDto dto = null;
			
			for(int i = 0; i < list.size(); i++){
				dto = (WeatherDto)list.get(i); 
		%>
			
			<tr>
				<td><%=dto.getTmEf() %>
				</td>
				<td><%=dto.getTmn() %>
				</td>
				<td><%=dto.getTmx() %>
				</td>
				<td><%=dto.getWf() %>
				</td>
				<td><%=dto.getReliability() %>
				</td>
			</tr>
				
			<% }
		%>
		</table>
	</div>
	</div>
</body>
</html>