<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 작성하기</title>
	<script src="resources/myLib/jquery-3.2.1.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	
<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if($("#title").val()==""){alert("제목을 입력하세요."); $("#title").focus(); return false;}
		if($("#content").val()==""){alert("내용을 입력하세요."); $("#content").focus(); return false;}
	});
});
</script>
</head>
<body>

<br>
<div>
  <p>1:1문의 작성하기</p>
</div>
<br>
<form action="ainsert" method="post">
<div class="row-col-3">
  <div>
  <table class="table" border="2" style="width: 60em; margin-left:auto; margin-right: auto;">
      <tr>
        <th style="border-color:GhostWhite; text-align: center; background: GhostWhite;" >제목 : <input type="text" name="title" id="title" placeholder="제목을 입력해주세요" size="40"></th>
      </tr>
      <tr>
        <th scope="col" colspan="5" style="border-bottom-color:Gainsboro; text-align: center;  background: GhostWhite">작성자 : 
        <input type="text" name="id" value="${loginID}" size="40" readonly="readonly"></th>
      </tr>
      <tr>
         <td style="text-align: center;"><textarea  cols="50" rows="10" name="content" id="content" placeholder="내용을 입력해주세요" ></textarea></td>       
      </tr>
      <!-- <tr>
        <th>사진첨부</th>
        <td><input type="file" name="uploadfilef" id="uploadfilef">
			<script>
			$('#uploadfilef').change(function(){
				if(this.files && this.files[0]) {
					var reader = new FileReader;
				 	reader.onload = function(e) {
			 			$(".select_img").attr("src", e.target.result)
			 			.width(100).height(100); 
			 		} // onload_function
			 		reader.readAsDataURL(this.files[0]);
			 	} // if
			}); // change			
			</script>
		</td>
      </tr> -->
      <tr style="text-align: center;">
        <td><input type="submit" id="submit" value="등록">&nbsp;&nbsp;
			<input onclick="javascript:history.go(-1)" type="reset" value="취소"></td>
      </tr>
      </table>
  </div>
</div>
</form>

</body>
</html>