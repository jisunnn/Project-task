<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>DoDo</title>
<script src="resources/myLib/jquery-3.2.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="header.jsp" %>
<div class="row"> 
  <div class="col-2" style="background-color: lavender;"></div>
  <div class="col-8">
    <%-- <%@ include file="challenge/clist.jsp" %> --%>
    <%@ include file="notice.jsp" %>
  </div>
  <div class="col-2" style="background-color: lavender;"></div>
</div>
<div class="container-fluid">
	<%@ include file="footer.jsp" %>
</div>



</body>
</html>
