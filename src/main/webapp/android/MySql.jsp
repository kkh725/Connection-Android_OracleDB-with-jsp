<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1> mysql ���� ����̹� �׽�Ʈ</h1>
	<%
	 	String jdbcUrl = "jdbc:mysql //localhost:3306/world%";
		String Id = "root";
		String dbPwd = "root";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl, id, dbPwd);
			out.println("MySQL ���Ἲ��");
			
		}
		catch(Exception ex){
			out.println("���� �����Դϴ�. �����޼��� : ");
		}
	 	%>
</body>
</html>