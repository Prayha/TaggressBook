<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String[] tag = new String[7];
	tag[0] = request.getParameter("tag1");
	tag[1] = request.getParameter("tag2");
	tag[2] = request.getParameter("tag3");
	tag[3] = request.getParameter("tag4");
	tag[4] = request.getParameter("tag5");
	tag[5] = request.getParameter("tag6");
	tag[6] = request.getParameter("tag7");
	
	
	out.println(id + tag[0]);
		
//------

	String url_mysql = "jdbc:mysql://192.168.0.82:3306/dbnw?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=FALSE";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	    Statement stmt_mysql = conn_mysql.createStatement();
	    
	 	String WhereDefault = "UPDATE tag SET tName = ? WHERE user_uId = ? AND tNumber = ?;";
	
	    ps = conn_mysql.prepareStatement(WhereDefault);
	    
	    for(int i=1; i<=7; i++){
	    	
	    ps.setString(1, tag[i-1]);
	    ps.setString(2, id);
	    ps.setInt(3, i);
	    
	    ps.executeUpdate();
	    
	    }
	
	    conn_mysql.close();
	} 
	
	catch (Exception e){
	    e.printStackTrace();
	}

%>
