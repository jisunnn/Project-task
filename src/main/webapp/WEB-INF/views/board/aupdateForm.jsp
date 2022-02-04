<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의_수정하기</title>
	<script src="resources/myLib/jquery-3.2.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<br>
<div>
  <p>문의사항_수정하기</p>
</div>
<hr>
<form action="aupdate" method="post">
<!-- 상세보기 table -->
<div class="row">
  <div>
    <table class="table" border="2" style="width: 60em; margin-left:auto; margin-right: auto;">
      <tr>
        <th colspan="9" style="border-color:GhostWhite; text-align: center; background: GhostWhite;" >글번호: <input name="seq" value="${aapple.seq}" hidden><input name="title" value="${aapple.title}"></th>
      </tr>
      <tr>
        <th scope="col" colspan="5" style="border-bottom-color:gray; text-align: center; font-size:5px; background: GhostWhite">작성자: ${aapple.id}  작성일 :${aapple.regdate}</th>
      </tr>
      <tr>
		<td style="text-align: center;">
		  <textarea cols="40" rows="10" name="content">${aapple.content}</textarea>></td>        
      </tr>
      <tr style="text-align: center;">
        <td><input type="submit" value="수정">&nbsp;&nbsp;
			<input onclick="javascript:history.go(-1)" type="reset" value="취소"></td>
      </tr>
    </table>
  </div>
</div>
</form>


</body>
</html>