<%@ page import="com.example.mission1project.dto.FreeWifi" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.mission1project.service.WifiService" %>
<%@ page import="com.example.mission1project.process.WifiProcess" %>
<%@ page import="com.example.mission1project.dto.History" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
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
    List<History> historyList = new ArrayList<>();

    WifiProcess wifiProcess = new WifiProcess();
    historyList = wifiProcess.getHistoryList();

%>
<h1>위치 히스토리 목록</h1>
<jsp:include page="common/head.jsp"/>
<br><br>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회 일자</th>
            <th>비고</th>
        </tr>
    </thead>
    <tbody>
        <% for(History history : historyList ){ %>
        <tr>
            <td><%=history.getID()%></td>
            <td><%=history.getxPosition()%></td>
            <td><%=history.getyPosition()%></td>
            <td><%=history.getSearchDate()%></td>
            <td><button onclick="deleteHistory(<%=history.getID()%>);">삭제</button></td>
        </tr>
        <%}%>
    </tbody>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    function deleteHistory(id){
        window.location.href = "/deleteHistory.jsp?id="+id;
    }
</script>
</html>
