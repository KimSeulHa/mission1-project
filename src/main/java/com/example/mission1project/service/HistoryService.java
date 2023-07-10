package com.example.mission1project.service;

import com.example.mission1project.dto.FreeWifi;
import com.example.mission1project.dto.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    /*
    * 검색 히스토리 저장
    * @param 좌표 정보
    * @return 성공여부 boolean
    *  */
    public boolean insertInfo(FreeWifi freeWifi){
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
            String sql = "INSERT INTO position_history(X_POSITION, Y_POSITION, SEARCH_DATE) VALUES(?,?,now());";

            statement = connection.prepareStatement(sql);
            statement.setString(1, freeWifi.getLat());
            statement.setString(2, freeWifi.getLnt());

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(freeWifi.getxSwifiMgrNo()+"저장 실패");
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
     * 히스토리 정보 삭제
     * @param String ID
     * @return 성공여부 boolean
     *  */
    public boolean deleteInfo(String id){
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
            String sql = "DELETE FROM position_history WHERE ID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);


            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(id+"삭제 실패");
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
     * 정보 목록
     * @param String, String
     * @return 정보 목록
     *  */
    public List<History> getHistoryList(){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        List<History> historyList = new ArrayList<>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT ID, X_POSITION, Y_POSITION, SEARCH_DATE FROM position_history ORDER BY ID DESC";

            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            while(rs.next()){
                History history = new History();

                history.setID(rs.getString("ID"));
                history.setxPosition(rs.getString("X_POSITION"));
                history.setyPosition(rs.getString("Y_POSITION"));
                history.setSearchDate(rs.getString("SEARCH_DATE"));

                historyList.add(history);
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

        return historyList;
    }

}
