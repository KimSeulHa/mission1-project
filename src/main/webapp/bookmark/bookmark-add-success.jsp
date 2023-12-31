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
    String bookmarkGroupNm = (String) request.getParameter("bookmarkGroupNm");
    String seq = (String) request.getParameter("seq");

    BookMark bookMark = new BookMark();
    bookMark.setBookmarkGroupNm(bookmarkGroupNm);
    bookMark.setSeq(Integer.parseInt(seq));

    BookmarkProcess process = new BookmarkProcess();
    boolean result = process.insertBookMarkGrp(bookMark);

%>
<br/>
<% if(result){ %>
    <h1><%=bookmarkGroupNm%>의 북마크 그룹을 등록했습니다.</h1>
    <a href="/index.jsp">홈 으로 가기</a>
<%}else{%>
    <h1><%=bookmarkGroupNm%>의 북마크 그룹 등록을 실패했습니다.</h1>
    <a href="/index.jsp">홈 으로 가기</a>
<%}%>
</body>
</html>