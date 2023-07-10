package com.example.mission1project;

import com.example.mission1project.dto.FreeWifi;
import com.example.mission1project.service.WifiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getOpenWifiInfo", value = "/getOpenWifiInfo")
public class GetOpenWifiInfo extends HttpServlet {
        WifiService wifiService = new WifiService();

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            System.out.println("들어옴");
            String key = "7259455a4f6b696d3130375472496773";
            JsonObject json = null;
            int start = 1;
            int end = 20;

            //OpenApi로 데이터 가져오기-1차
            json = (JsonObject) URLConnection(key, start, end);
            JsonObject datas = (JsonObject) json.get("TbPublicWifiInfo");
            int totalCount = Integer.parseInt(datas.get("list_total_count").toString());

            //DB insert -1차
            boolean result = false;
            result = insertWifiInfo(datas);
            if(result){
                //OpenApi로 데이터 가져오기-n차
                for(int i = end+1; i <= totalCount; i++){
                    int k = i+20;

                    if(k>totalCount){
                        k = totalCount;
                        System.out.println("마지막입니다.");
                    }

                    System.out.println("start>"+i);
                    System.out.println("end>"+k);

                    json = URLConnection(key, i, k);
                    datas = (JsonObject) json.get("TbPublicWifiInfo");
                    result = insertWifiInfo(datas);
                    if(!result){
                        break;
                    }
                    i=k+1;
                }

            }

            request.setAttribute("totalCount",totalCount);

            RequestDispatcher rq = request.getRequestDispatcher("/success.jsp");
            rq.forward(request,response);

        }

        public JsonObject URLConnection(String key, int start, int end)throws IOException, ServletException {
            JsonObject json = null;

            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
            urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") );
            urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") );
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start),"UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end),"UTF-8"));


            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");
            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            json = (JsonObject) new JsonParser().parse(sb.toString());

            return json;
        }

        public boolean insertWifiInfo(JsonObject datas){
            boolean result = false;

            JsonArray dataArr = (JsonArray) datas.get("row");
            System.out.println(datas);

            List<FreeWifi> freeWifiList = new ArrayList<>();
            for(int i = 0; i < dataArr.size(); i++){
                JsonObject data = (JsonObject) dataArr.get(i);
                FreeWifi freeWifi = new FreeWifi();
                freeWifi.setxSwifiMgrNo(data.get("X_SWIFI_MGR_NO").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiWrdofc(data.get("X_SWIFI_WRDOFC").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiMainNm(data.get("X_SWIFI_MAIN_NM").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiAdres1(data.get("X_SWIFI_ADRES1").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiAdres2(data.get("X_SWIFI_ADRES2").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiInstlFloor(data.get("X_SWIFI_INSTL_FLOOR").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiInstlTy(data.get("X_SWIFI_INSTL_TY").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiInstlMby(data.get("X_SWIFI_INSTL_MBY").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiSvcSe(data.get("X_SWIFI_SVC_SE").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiCmcwr(data.get("X_SWIFI_CMCWR").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiCnstcYear(data.get("X_SWIFI_CNSTC_YEAR").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiInoutDoor(data.get("X_SWIFI_INOUT_DOOR").toString().replaceAll("\"", ""));
                freeWifi.setxSwifiRemars3(data.get("X_SWIFI_REMARS3").toString().replaceAll("\"", ""));
                freeWifi.setLat(data.get("LAT").toString().replaceAll("\"", ""));
                freeWifi.setLnt(data.get("LNT").toString().replaceAll("\"", ""));
                freeWifi.setWorkDttm(data.get("WORK_DTTM").toString().replaceAll("\"", ""));

                freeWifiList.add(freeWifi);
            }

            if(!wifiService.insertInfo(freeWifiList)){
                System.out.println("공공와이파이 정보 db저장 실패!");
            }
            else{
                result = true;
            }

            return  result;

        }
    }
