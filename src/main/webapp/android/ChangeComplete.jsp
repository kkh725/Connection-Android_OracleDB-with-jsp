<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   String id = request.getParameter("id"); //여기 파라미터에서 받는 "id"값과, 안드로이드스튜디오에서 "&id" 문자열이 같아야한다.
   String pw = request.getParameter("pw");
   String NAME = request.getParameter("NAME");
   String PHONE = request.getParameter("PHONE");
   String BIRTH = request.getParameter("BIRTH");
   String MAIL = request.getParameter("MAIL");
   String CITY = request.getParameter("CITY");
   
   String register = connectDB.Complete(id,pw,NAME,PHONE,BIRTH,MAIL,CITY);
   
   System.out.println(id);
   System.out.println(pw);
   // 안드로이드로 전송
   //jsp 파일에 출력하는 구문이 out.println() 이기때문에 안드로이드에서 jsp와 구동 후 읽어옴.
   out.println(register);
%>