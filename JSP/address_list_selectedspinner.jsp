<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String userid = request.getParameter("userid");
	int tagnum = Integer.parseInt(request.getParameter("aTag"));

	String url_mysql = "jdbc:mysql://192.168.0.82/dbnw?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select aSeqno, aName, aImage, aPNum, aEmail, aMemo, aTag from address where user_uSeqno = (select uSeqno from user where uId = '" + userid + "') AND aTag like '%"+ tagnum + "%'";
    int count = 0;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault); // 
%>
		{ 
  			"address_info"  : [ 
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
			"aSeqno" : "<%=rs.getInt(1) %>", 
			"aName" : "<%=rs.getString(2) %>",   
			"aImage" : "<%=rs.getString(3) %>",  
			"aPNum" : "<%=rs.getString(4) %>",
			"aEmail" : "<%=rs.getString(5) %>",
			"aMemo" : "<%=rs.getString(6) %>",
			"aTag" : "<%=rs.getString(7) %>"
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
