<%@ page import="com.example.mission1project.dto.FreeWifi" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.mission1project.service.WifiService" %>
<%@ page import="com.example.mission1project.process.WifiProcess" %>
<%@ page import="com.example.mission1project.service.BookMarkService" %>
<%@ page import="com.example.mission1project.dto.BookMark" %>
<%@ page import="com.example.mission1project.process.BookmarkProcess" %>
<%@ page import="java.awt.print.Book" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>북마크 그룹</title>
    <style>
        table{
            width: 100%;
            text-align: center;
        }
        thead th{
            background-color: mediumseagreen;
            color: white;
            padding: 10px;
            font-size: 0.7em;

        }
        tbody td{
            border: solid gray 1px;
            padding: 10px;
            font-size: 0.7em;
        }
    </style>
</head>
<body>
<%
    BookmarkProcess process = new BookmarkProcess();

    List<BookMark> list = new ArrayList<>();
    list = process.getBookMarkGrpList();

%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="/common/head.jsp"/>
<br><br>
<button id="moveBookMarkAddPage">북마크 그룹 이름 추가</button>

<br><br>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>북마크이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody>
        <%
            if(list.size() == 0){
        %>
                <tr>
                    <td colspan="6">북마크 그룹을 등록해주세요.</td>
                </tr>
        <%}else{
                for(BookMark bookMark : list){
        %>
                    <tr>
                        <td><%=bookMark.getID()%></td>
                        <td><%=bookMark.getBookmarkGroupNm()%></td>
                        <td><%=bookMark.getSeq()%></td>
                        <td><%=bookMark.getRegDate()%></td>
                        <%--<td><a href="/detail.jsp?xSwifiMgrNo=<%=freeWifi.getxSwifiMgrNo()%>"><%=freeWifi.getxSwifiMainNm()%></a></td>--%>
                        <td><%=bookMark.getUpdateDate()%></td>
                        <td>
                            <a href = "/bookmark/bookmark-group-edit.jsp?id=<%=bookMark.getID()%>">수정</a>
                            <a href = "/bookmark/bookmark-group-delete.jsp?id=<%=bookMark.getID()%>">삭제</a>
                        </td>
                    </tr>
        <%      }
            }
        %>
    </tbody>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $("#moveBookMarkAddPage").click(function(){
            window.location.href = "/bookmark/bookmark-group-add.jsp";
        });

    });
</script>
</html>
