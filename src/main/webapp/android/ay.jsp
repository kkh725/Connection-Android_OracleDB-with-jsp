<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>
</head>
<body>
<button type="button" onclick="location.href='https://app' ">다시 어플로 이동</button>
	
	
	
   <%
   String status = request.getParameter("status");
   System.out.println(request.getRequestURL().toString()); 
	System.out.println(request.getQueryString()); 
	out.print(request.getRequestURL().toString()+"<br>"); 
	out.print(request.getQueryString()+"<br>");
	out.print(status);%>
</body>
</html>