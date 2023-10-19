<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
	String id =  request.getParameter("id");
   
   String count_tag = connectDB.getCount_tags(id);
   System.out.println(count_tag);
   
   // 안드로이드로 전송
   //jsp 파일에 출력하는 구문이 out.println() 이기때문에 안드로이드에서 jsp와 구동 후 읽어옴.
   out.println(count_tag);
%>