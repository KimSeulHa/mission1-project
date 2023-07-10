<%@ page import="com.example.mission1project.process.BookmarkProcess" %>
<%@ page import="com.example.mission1project.dto.BookMark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body{
            text-align: center;
        }
    </style>
</head>
<body>
<%
    String id = (String) request.getParameter("id");

    BookmarkProcess process = new BookmarkProcess();
    boolean result = process.deleteBookMark(id);

%>
<br/>
<% if(result){ %>
<h1>삭제했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}else{%>
<h1>삭제를 실패했습니다.</h1>
<a href="/index.jsp">홈 으로 가기</a>
<%}%>
</body>
</html>