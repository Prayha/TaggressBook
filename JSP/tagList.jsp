<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");

	String url_mysql = "jdbc:mysql://192.168.0.82:3306/dbnw?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=FALSE";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
 	
    String WhereDefault = "SELECT tName FROM tag WHERE user_uId = '" + id + "'";
    
    int count = 0;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault); // 
%>
		{ 
  			"tag"  : [ 
<%
        while (rs.next()) {
            if (count == 0) {

            }else{
%>
            , 
<%
            }
%>            
			{
			"tName" : "<%=rs.getString(1) %>"
			}

<%		
             count++;
        }
%>
		  ] 
		} 
<%		
        conn_mysql.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
	
%>
