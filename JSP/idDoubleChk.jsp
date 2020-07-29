<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");

 	int check = 0;
 	
	//out.println(id + pw);
		
//------

	String url_mysql = "jdbc:mysql://192.168.0.82:3306/dbnw?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=FALSE";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	    Statement stmt_mysql = conn_mysql.createStatement();

	 	String WhereDefault = "SELECT count(uId) FROM user WHERE uId = '" + id + "';";
		
	 	
	    ps = conn_mysql.prepareStatement(WhereDefault);

	 	ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	check = rs.getInt(1);
        }
	
	    conn_mysql.close();
	} 
	
	catch (Exception e){
	    e.printStackTrace();
	}

	out.println(check);
%>
