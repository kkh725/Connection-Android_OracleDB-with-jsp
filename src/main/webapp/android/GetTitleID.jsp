<%@page import="com.db.ConnectDB"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   
   String tag1 = request.getParameter("TAG1");
   String tag2 = request.getParameter("TAG2");
   String tag3 = request.getParameter("TAG3");

   String id_title = connectDB.getTitle_ID(tag1,tag2,tag3);
   System.out.println(id_title);
   
   // 안드로이드로 전송
   //jsp 파일에 출력하는 구문이 out.println() 이기때문에 안드로이드에서 jsp와 구동 후 읽어옴.
   out.println(id_title);
   
%>