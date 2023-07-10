<%@ page import="com.example.mission1project.dto.FreeWifi" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.mission1project.service.WifiService" %>
<%@ page import="com.example.mission1project.process.BookmarkProcess" %>
<%@ page import="com.example.mission1project.dto.BookMark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>북마크 그룹 삭제</title>
    <style>
        table{
            width: 50%;
            text-align: center;
        }
        th{
            background-color: mediumseagreen;
            color: white;
            padding: 10px;
            font-size: 0.7em;
            width: 30%;

        }
        td{
            border: solid gray 1px;
            padding: 10px;
            font-size: 0.7em;
        }
    </style>
</head>
<body>
<%
    String id = (String) request.getParameter("id");
    BookMark bookMark = new BookMark();
    BookmarkProcess process = new BookmarkProcess();
    bookMark = process.getBookMarkGrpInfo(id);
%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/common/head.jsp"/>
<br><br>
<form action="/bookmark/bookmark-delete-success.jsp" method="get">
    <table>
        <input type="hidden" name="id" value="<%=id%>"/>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input name="bookmarkGroupNm" id="bookmarkGroupNm" value="<%=bookMark.getBookmarkGroupNm()%>"/>
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input name="seq" id="seq" value="<%=bookMark.getSeq()%>"/>
            </td>
        </tr>

    </table>
    <a href="/bookmark/bookmark-group.jsp">돌아가기</a>
    <input type="submit" value="삭제">
</form>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

</script>
</html>
