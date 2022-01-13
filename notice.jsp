<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
     table { align-self: center; text-align: center; margin-left: auto; margin-right: auto;}
     td { font-size: 10pt;}
     caption {font-size: 20px;}

</style>
<script src="resources/myLib/jquery-3.2.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<br>
<!-- 공지사항 -->
<table class="table caption-top">
  <caption>Notice</caption>
  <thead>
    <tr>
      <th scope="col"> </th>
      <th scope="col">제목</th>
      <th scope="col">날짜</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>사이트 서버 점검 안내</td>
      <td>2022/01/02</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>정보제공 동의 안내</td>
      <td>2021/11/04</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>휴먼 계정 관련 정책</td>
      <td>2021/07/14</td>
    </tr>
    <tr>
      <th scope="row">4</th>
      <td>챌린지 규정 변경</td>
      <td>2021/06/26</td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>사이트 이용 안내</td>
      <td>2021/04/08</td>
    </tr>
  </tbody>
</table>

<!-- 공지사항 페이지 번호 -->
<br>
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<br>
</body>
</html>