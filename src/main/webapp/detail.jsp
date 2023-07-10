<%@ page import="com.example.mission1project.dto.FreeWifi" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.mission1project.service.WifiService" %>
<%@ page import="com.example.mission1project.dto.BookMark" %>
<%@ page import="com.example.mission1project.process.BookmarkProcess" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
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
    String xSwifiMgrNo = request.getParameter("xSwifiMgrNo");

    WifiService WService = new WifiService();
    FreeWifi freeWifi = WService.getFreeWifiDetail(xSwifiMgrNo);

    BookmarkProcess process = new BookmarkProcess();
    List<BookMark> list = new ArrayList<>();
    list = process.getBookMarkGrpList();

%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="common/head.jsp"/>
<br>
<select id="bookMarkGrpId">
    <% for(BookMark bookMark: list){%>
    <option value="<%=bookMark.getID()%>"><%=bookMark.getBookmarkGroupNm()%></option>
    <%}%>
</select>
<button id="addBookMark">북마크 추가하기</button>
<br>
<table>
<input type="hidden" name="wifiNm" id="wifiNm" value="<%=freeWifi.getxSwifiMainNm()%>"/>
        <tr>
            <th>거리(Km)</th>
            <td><%=freeWifi.getLnt()%></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td><%=freeWifi.getxSwifiMgrNo()%></td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=freeWifi.getxSwifiWrdofc()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=freeWifi.getxSwifiMainNm()%></td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><%=freeWifi.getxSwifiAdres1()%>/td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%=freeWifi.getxSwifiAdres2()%></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%=freeWifi.getxSwifiInstlFloor()%></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%=freeWifi.getxSwifiInstlTy()%></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%=freeWifi.getxSwifiInstlMby()%></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%=freeWifi.getxSwifiSvcSe()%></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%=freeWifi.getxSwifiCmcwr()%></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%=freeWifi.getxSwifiCnstcYear()%></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%=freeWifi.getxSwifiInoutDoor()%></td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td><<%=freeWifi.getxSwifiRemars3()%>/td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%=freeWifi.getLat()%></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%=freeWifi.getLnt()%></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=freeWifi.getWorkDttm()%></td>
        </tr>
</table>

</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    $(function () {
        $("#addBookMark").click(function(){
            var bookMarkGrpId = $("#bookMarkGrpId option:selected").val();
            var wifiNm = $("#wifiNm").val();
            window.location.href = "/bookmark-add-success.jsp?bookMarkGrpId="+bookMarkGrpId+"&wifiNm="+wifiNm;
        });
    });
</script>
</html>
