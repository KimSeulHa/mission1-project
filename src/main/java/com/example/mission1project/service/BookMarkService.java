package com.example.mission1project.service;

import com.example.mission1project.dto.BookMark;
import com.example.mission1project.dto.History;
import com.example.mission1project.dto.FreeWifi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookMarkService {

    /*
     * 북마크 목록
     * @param
     * @return 정보 목록
     *  */
    public List<BookMark> getBookMarkGrpList(){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        List<BookMark> bookMarkList = new ArrayList<>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT ID, BOOKMARK_GROUP_NM, SEQ, REG_DATE, UPDATE_DATE FROM bookmark_group ORDER BY SEQ";

            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            while(rs.next()){
                BookMark bookMark = new BookMark();

                bookMark.setID(rs.getString("ID"));
                bookMark.setBookmarkGroupNm(rs.getString("BOOKMARK_GROUP_NM"));
                bookMark.setSeq(rs.getInt("SEQ"));
                bookMark.setRegDate(rs.getString("REG_DATE"));
                bookMark.setUpdateDate(rs.getString("UPDATE_DATE"));

                bookMarkList.add(bookMark);
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

        return bookMarkList;
    }

    /*
     * 북마크 상세보기
     * @param
     * @return
     *  */
    public BookMark getBookMarkGrpInfo(String id){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        BookMark bookMark = new BookMark();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT ID, BOOKMARK_GROUP_NM, SEQ, REG_DATE, UPDATE_DATE FROM bookmark_group WHERE ID = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            rs = statement.executeQuery();

            while(rs.next()){

                bookMark.setID(rs.getString("ID"));
                bookMark.setBookmarkGroupNm(rs.getString("BOOKMARK_GROUP_NM"));
                bookMark.setSeq(rs.getInt("SEQ"));
                bookMark.setRegDate(rs.getString("REG_DATE"));
                bookMark.setUpdateDate(rs.getString("UPDATE_DATE"));

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

        return bookMark;
    }

    /*
    * 북마크 그룹 저장
    * @param 좌표 정보
    * @return 성공여부 boolean
    *  */
    public boolean insertBookmarkGrp(BookMark bookMark){
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
            String sql = "insert into bookmark_group(BOOKMARK_GROUP_NM,SEQ,REG_DATE) values(?,?,now());";

            statement = connection.prepareStatement(sql);
            statement.setString(1, bookMark.getBookmarkGroupNm());
            statement.setInt(2, bookMark.getSeq());

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(bookMark.getBookmarkGroupNm()+"저장 실패");
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
     * 북마크 그룹 수정
     * @param 좌표 정보
     * @return 성공여부 boolean
     *  */
    public boolean updateBookmarkGrp(BookMark bookMark){
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
            String sql = "update bookmark_group set SEQ = ?, BOOKMARK_GROUP_NM =  ? , UPDATE_DATE = NOW() where ID = ? ;";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, bookMark.getSeq());
            statement.setString(2, bookMark.getBookmarkGroupNm());
            statement.setString(3, bookMark.getID());

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(bookMark.getBookmarkGroupNm()+"수정 실패");
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
     * 북마크 그룹 삭제
     * @param String ID
     * @return 성공여부 boolean
     *  */
    public boolean deleteBookMarkGrp(String id){
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
            String sql = "DELETE FROM bookmark_group WHERE ID = ?";

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
     * 북마크 그룹 저장
     * @param 좌표 정보
     * @return 성공여부 boolean
     *  */
    public boolean insertBookmark(BookMark bookMark){
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
            String sql = "insert into bookmark(BOOKMARK_GRP_ID,WIFI_NM,REG_DATE) VALUES(?,?,NOW());";

            statement = connection.prepareStatement(sql);
            statement.setString(1, bookMark.getBookMarkGrpId());
            statement.setString(2, bookMark.getWifiNm());

            int affected = statement.executeUpdate();
            if(affected > 0) {
                result = true;
            }else {
                System.out.println(bookMark.getWifiNm()+"저장 실패");
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
     * 북마크 목록
     * @param
     * @return 정보 목록
     *  */
    public List<BookMark> getBookMarkList(){

        String url = "jdbc:mariadb://192.168.35.35:3306/mission1";
        String userId = "root";
        String passwd = "1111";

        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet rs = null;
        List<BookMark> bookMarkList = new ArrayList<>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,userId,passwd);

            String sql = "SELECT A.ID , WIFI_NM, A.REG_DATE , B.ID AS BOOKMARK_GRP_ID, B.BOOKMARK_GROUP_NM FROM bookmark A "
                        + " INNER JOIN bookmark_group B ON A.BOOKMARK_GRP_ID = B.ID";

            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            while(rs.next()){
                BookMark bookMark = new BookMark();

                bookMark.setID(rs.getString("ID"));
                bookMark.setWifiNm(rs.getString("WIFI_NM"));
                bookMark.setRegDate(rs.getString("REG_DATE"));
                bookMark.setBookMarkGrpId(rs.getString("BOOKMARK_GRP_ID"));
                bookMark.setBookmarkGroupNm(rs.getString("BOOKMARK_GROUP_NM"));

                bookMarkList.add(bookMark);
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

        return bookMarkList;
    }
    /*
     * 북마크 삭제
     * @param String ID
     * @return 성공여부 boolean
     *  */
    public boolean deleteBookMark(String id){
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
            String sql = "DELETE FROM bookmark WHERE ID = ?";

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

}
