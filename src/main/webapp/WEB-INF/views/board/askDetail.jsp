<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
	<script src="resources/myLib/jquery-3.2.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	<style>
      a {text-decoration: none;}
	</style>
</head>
<body>

<br>
<div style="margin-left: 5em;">
  <h4>1:1 문의</h4>
</div>
<hr style="width: 95%; margin-left: auto;margin-right: auto;">

<!-- 상세보기 table -->
<div class="row">
  <div style="width: 95%; margin-left: auto; margin-right: auto;">
    <table class="table">
      <tr>
        <th colspan="9" style="border-color:White; text-align: center; font-size:20px;" ><a hidden>${aapple.seq}${aapple.root}</a>${aapple.title}</th>
      </tr>
      <tr>
        <th scope="col" colspan="5" style="border-bottom-color:LightGray; text-align: center; font-size:10px;">문의 작성자: ${aapple.id}  작성일 :${aapple.regdate}</th>
      </tr>
      <tr>
		<td style="text-align: center; height: auto; font-size: 16px; border-bottom-color:DarkGray; border-bottom-width:1px;">
		  <p class="text-break" >${aapple.content}</p></td>        
      </tr>
    </table>
<c:if test="${aapple.indent > 0}">
  <c:if test="${loginID=='admin'}">
 	<a href="aupdatef?seq=${aapple.seq}" style="color:RebeccaPurple; font-size: 18px;">글수정</a>&nbsp;&nbsp;
	<a href="adelete?root=${aapple.root}&seq=${aapple.seq}" style="color: RebeccaPurple; font-size: 18px;">글삭제</a>&nbsp;&nbsp;
		<!-- 삭제시 원글삭제 or 답글삭제 확인을 위함 -->
  </c:if>
</c:if>
<c:if test="${aapple.indent == 0}">
<c:if test="${loginID==aapple.id  || loginID=='admin'}">
 	<a href="aupdatef?seq=${aapple.seq}" style="color:RebeccaPurple; font-size: 18px;">글수정</a>&nbsp;&nbsp;
	<a href="adelete?root=${aapple.root}&seq=${aapple.seq}" style="color: RebeccaPurple; font-size: 18px;">글삭제</a>&nbsp;&nbsp;
		<!-- 삭제시 원글삭제 or 답글삭제 확인을 위함 -->
  </c:if>
</c:if>
<c:if test="${loginID=='admin'}">
<a href="ansinsertf?root=${aapple.root}&indent=${aapple.indent}&step=${aapple.step}&id=${aapple.id}" style="color: RebeccaPurple; font-size: 18px;">답글등록</a><br>
</c:if>

</div>
</div>

<div align="center">
<a href='javascript:history.go(-1)' style="border-radius:3px; background-color: Gray; color:white; display:inline-block; width: 3em; height: 1.5em; font-size: 18px;">목록</a>&nbsp;&nbsp;
</div>
</body>
</html>