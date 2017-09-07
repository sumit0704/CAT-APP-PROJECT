<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
Variables received:
User ID: 		"${User_ID}"<br></br>
Authorization:	"${Authorization}"<br></br>
Change_password:"${Change_password}"<br></br>
Password_2: 	"${Password_2}"<br></br>
		

<sql:setDataSource var="snapshot"
driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />
 

<c:choose>
	<c:when test="${Authorization == 'Yes_authorizing'}">
		<sql:update dataSource="${snapshot}" var="count">
  			update users SET approved = 'Y' where User_ID = '${User_ID}';
		</sql:update>
    </c:when>    
    <c:otherwise> 
		<sql:update dataSource="${snapshot}" var="count">
  			update users SET approved = 'N' where User_ID = '${User_ID}';
		</sql:update>
    </c:otherwise>  
</c:choose>
<c:if test="${Change_password == 'Change_password'}">
	<sql:update dataSource="${snapshot}" var="count">
  		update users SET Password = "${Password_2}" where User_ID = '${User_ID}';
	</sql:update>
</c:if>







<form action="${pageContext.request.contextPath}/Admin" Mehtod="post">
					
	<input type="submit" class="btn btn-primary" value ="Manage"></input>
</form>
					
<script type="text/javascript">
	$( document ).ready(function() {
		window.location = "${pageContext.request.contextPath}/Admin";
	});
</script>
		

</body>
</html>