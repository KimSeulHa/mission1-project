<%@ page import="com.example.mission1project.dto.FreeWifi" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.mission1project.service.WifiService" %>
<%@ page import="com.example.mission1project.process.WifiProcess" %>
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
    WifiProcess wifiProcess = new WifiProcess();
    List<FreeWifi> list = new ArrayList<>();
    list = null;

    if(request.getParameter("LAT")!= null && request.getParameter("LNT")!= null) {
        String LAT = request.getParameter("LAT").toString();
        String LNT = request.getParameter("LNT").toString();
        list = wifiProcess.getNearByfreeWifiList(LAT,LNT);
    }
%>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="common/head.jsp"/>
<br><br>
LAT:<input type="text" id="LAT" name="LAT" value=""/>
, LNT:<input type="text" id="LNT" name="LNT" value=""/>
<button id="getMyPosition">내 위치 가져오기</button>
<button id="getNearbyWifi">근처 WIFI 정보 보기</button>

<br><br>
<table>
    <thead>
        <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
    </thead>
    <tbody>
        <%
            if(list == null){
        %>
                <tr>
                    <td colspan="17">위치 정보를 입력한 후에 조회해 주세요!!</td>
                </tr>
        <%}else{
                for(FreeWifi freeWifi : list){
        %>
                    <tr>
                        <td><%=freeWifi.getLat()%></td>
                        <td><%=freeWifi.getxSwifiMgrNo()%></td>
                        <td><%=freeWifi.getxSwifiWrdofc()%></td>
                        <td><a href="/detail.jsp?xSwifiMgrNo=<%=freeWifi.getxSwifiMgrNo()%>"><%=freeWifi.getxSwifiMainNm()%></a></td>
                        <td><%=freeWifi.getxSwifiAdres1()%></td>
                        <td><%=freeWifi.getxSwifiAdres2()%></td>
                        <td><%=freeWifi.getxSwifiInstlFloor()%></td>
                        <td><%=freeWifi.getxSwifiInstlTy()%></td>
                        <td><%=freeWifi.getxSwifiInstlMby()%></td>
                        <td><%=freeWifi.getxSwifiSvcSe()%></td>
                        <td><%=freeWifi.getxSwifiCmcwr()%></td>
                        <td><%=freeWifi.getxSwifiCnstcYear()%></td>
                        <td><%=freeWifi.getxSwifiInoutDoor()%></td>
                        <td><%=freeWifi.getxSwifiRemars3()%></td>
                        <td><%=freeWifi.getLat()%></td>
                        <td><%=freeWifi.getLnt()%></td>
                        <td><%=freeWifi.getWorkDttm()%></td>
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
        //내 위치 가져오기
        $("#getMyPosition").click(function(){
            navigator.geolocation.getCurrentPosition((position) => {
                $("#LAT").val(position.coords.latitude);
                $("#LNT").val(position.coords.longitude);
            });
        });
        //근처 wifi정보 가져오기
        $("#getNearbyWifi").click(function(){
            if($("#LAT").val() == '' || $("#LNT").val() == ''){
                alert("현재 위치를 가져와주세요.");
                return false;

            }else{
                var LAT = $("#LAT").val();
                var LNT = $("#LNT").val()
                window.location.href = "/index.jsp?LAT="+LAT+"&LNT="+LNT;
            }
        });

    });
</script>
</html>
