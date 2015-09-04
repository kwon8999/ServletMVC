<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	PrintWriter writer = response.getWriter();
	String se = (String)session.getAttribute("nick");
	System.out.println(se);
	if(se == null || se.equals("")){
		writer.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		writer.println("<!--");
        writer.println("  alert('잘못된 접근입니다.');");
        writer.println("  location.href='/';");
        writer.println("//-->");
        writer.println("</SCRIPT>");
	}
%>
