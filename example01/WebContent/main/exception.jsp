<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%

	System.out.println("예외 클래스 : " +  exception.getClass().getName());
	System.out.println("예외 내용 : " + exception.getMessage());
	
	out.println("<script>");
	out.println("alert('잘못된 접근방식 또는 서버에 문제가 발생하였습니다.\\n문제가 계속 되면 고객센터에 문의해주시기 바랍니다.');");
	out.println("location.href='../index.do';");
	out.println("</script>");
%>