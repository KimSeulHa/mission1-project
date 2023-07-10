<%@ page import="com.example.mission1project.process.WifiProcess" %>
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
    String id = request.getParameter("id").toString();
    boolean result = false;

    WifiProcess wifiProcess = new WifiProcess();
    result = wifiProcess.deleteHistory(id);

%>
<br/>
    <% if(result){ %>
        <h1><%=id%>가 삭제되었습니다.</h1>
    <%}else{%>
        <h1><%=id%>가 삭제에 실패했습니다.</h1>
    <%}%>
<a href="/index.jsp">홈 으로 가기</a>

</body>
</html>