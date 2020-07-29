<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String[] color = {"빨간색", "주황색", "노란색", "초록색", "파란색", "보라색", "회색"};
	
//------

	String url_mysql = "jdbc:mysql://192.168.0.82:3306/dbnw?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=FALSE";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";
	
   
	
		PreparedStatement ps = null;
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		    Statement stmt_mysql = conn_mysql.createStatement();

		for(int i=1; i<=7; i++){
			String query = "INSERT INTO tag (tName, tNumber, user_uId) VALUES ('" + color[i-1] + "', " + i + ", '" + id + "')";
			ps = conn_mysql.prepareStatement(query);
			ps.executeUpdate();
		}
			conn_mysql.close();

  		  }
		
		catch (Exception e){
		    e.printStackTrace();
		}
   
%>
