<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   String tpk = request.getParameter("tpk"); //여기 파라미터에서 받는 "id"값과, 안드로이드스튜디오에서 "&id" 문자열이 같아야한다.
 
   
   String Post_info = connectDB.getID_TITLE_CONTENT_DATE_TAG(tpk);
   
   System.out.println(Post_info);
   // 안드로이드로 전송
   //jsp 파일에 출력하는 구문이 out.println() 이기때문에 안드로이드에서 jsp와 구동 후 읽어옴.
   out.println(Post_info);
%>