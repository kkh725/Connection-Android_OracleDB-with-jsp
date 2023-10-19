<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1> mysql 연결 드라이버 테스트</h1>
	<%
	 	String jdbcUrl = "jdbc:mysql //localhost:3306/world%";
		String Id = "root";
		String dbPwd = "root";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl, id, dbPwd);
			out.println("MySQL 연결성공");
			
		}
		catch(Exception ex){
			out.println("연결 오류입니다. 오류메세지 : ");
		}
	 	%>
</body>
</html>