package com.example.mission1project.service;

import com.example.mission1project.dto.FreeWifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiService {

    /*
    * 정보 저장
    * @param 공공와이파이 정보
    * @return 성공여부 boolean
    *  */
    public boolean insertInfo(List<FreeWifi> freeWifiList){
        boolean result = false;

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url,userId,passwd);
            String sql = "INSERT INTO public_wifi_info VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'))";

            for(FreeWifi freeWifi : freeWifiList){
                statement = connection.prepareStatement(sql);
                statement.setString(1, freeWifi.getxSwifiMgrNo());
                statement.setString(2, freeWifi.getxSwifiWrdofc());
                statement.setString(3,freeWifi.getxSwifiMainNm());
                statement.setString(4,freeWifi.getxSwifiAdres1());
                statement.setString(5,freeWifi.getxSwifiAdres2());
                statement.setString(6,freeWifi.getxSwifiInstlFloor());
                statement.setString(7,freeWifi.getxSwifiInstlTy());
                statement.setString(8,freeWifi.getxSwifiInstlMby());
                statement.setString(9,freeWifi.getxSwifiSvcSe());
                statement.setString(10,freeWifi.getxSwifiCmcwr());
                statement.setString(11,freeWifi.getxSwifiCnstcYear());
                statement.setString(12,freeWifi.getxSwifiInoutDoor());
                statement.setString(13,freeWifi.getxSwifiRemars3());
                statement.setString(14,freeWifi.getLat());
                statement.setString(15,freeWifi.getLnt());
                statement.setString(16,freeWifi.getWorkDttm());

                int affected = statement.executeUpdate();
                if(affected > 0) {
                    result = true;
                }else {
                    System.out.println(freeWifi.getxSwifiMgrNo()+"저장 실패");
                }
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {

            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /*
     * 정보 저장
     * @param 공공와이파이 정보
     * @return 성공여부 boolean
     *  */
    public boolean deleteInfo(FreeWifi freeWifi){
        boolean result = false;

        return result;
    }

    /*
     * 정보 목록
     * @return 정보 목록
     *  */
    public List<FreeWifi> getFreeWifi(String LAT, String LNT){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        List<FreeWifi> freeWifiList = new ArrayList<>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,"
                        +" X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
                        +" X_SWIFI_REMARS3,LAT, LNT, WORK_DTTM"
                        +" FROM public_wifi_info"
                        +" LIMIT 20";

            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            while(rs.next()){
                FreeWifi freeWifi = new FreeWifi();

                freeWifi.setxSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
                freeWifi.setxSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                freeWifi.setxSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
                freeWifi.setxSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
                freeWifi.setxSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
                freeWifi.setxSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                freeWifi.setxSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
                freeWifi.setxSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
                freeWifi.setxSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
                freeWifi.setxSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
                freeWifi.setxSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
                freeWifi.setxSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
                freeWifi.setxSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
                freeWifi.setLat(rs.getString("LAT"));
                freeWifi.setLnt(rs.getString("LNT"));
                freeWifi.setWorkDttm(rs.getString("WORK_DTTM"));

                freeWifiList.add(freeWifi);
            }


        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {

            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return freeWifiList;
    }

    /*
     * 정보 목록
     * @return 정보 목록
     *  */
    public FreeWifi getFreeWifiDetail(String xSwifiMgrNo){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        FreeWifi freeWifi = new FreeWifi();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR,"
                    +" X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR,"
                    +" X_SWIFI_REMARS3,LAT, LNT, WORK_DTTM"
                    +" FROM public_wifi_info"
                    +" WHERE X_SWIFI_MGR_NO = ? ";

            statement = connection.prepareStatement(sql);
            statement.setString(1,xSwifiMgrNo);

            rs = statement.executeQuery();

            while(rs.next()){
                freeWifi.setxSwifiMgrNo(rs.getString("X_SWIFI_MGR_NO"));
                freeWifi.setxSwifiWrdofc(rs.getString("X_SWIFI_WRDOFC"));
                freeWifi.setxSwifiMainNm(rs.getString("X_SWIFI_MAIN_NM"));
                freeWifi.setxSwifiAdres1(rs.getString("X_SWIFI_ADRES1"));
                freeWifi.setxSwifiAdres2(rs.getString("X_SWIFI_ADRES2"));
                freeWifi.setxSwifiInstlFloor(rs.getString("X_SWIFI_INSTL_FLOOR"));
                freeWifi.setxSwifiInstlTy(rs.getString("X_SWIFI_INSTL_TY"));
                freeWifi.setxSwifiInstlMby(rs.getString("X_SWIFI_INSTL_MBY"));
                freeWifi.setxSwifiSvcSe(rs.getString("X_SWIFI_SVC_SE"));
                freeWifi.setxSwifiCmcwr(rs.getString("X_SWIFI_CMCWR"));
                freeWifi.setxSwifiCnstcYear(rs.getString("X_SWIFI_CNSTC_YEAR"));
                freeWifi.setxSwifiInoutDoor(rs.getString("X_SWIFI_INOUT_DOOR"));
                freeWifi.setxSwifiRemars3(rs.getString("X_SWIFI_REMARS3"));
                freeWifi.setLat(rs.getString("LAT"));
                freeWifi.setLnt(rs.getString("LNT"));
                freeWifi.setWorkDttm(rs.getString("WORK_DTTM"));

            }


        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {

            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(statement != null && !statement.isClosed()){
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return freeWifi;
    }

}
