package com.example.mission1project.process;

import com.example.mission1project.dto.BookMark;
import com.example.mission1project.dto.History;
import com.example.mission1project.service.BookMarkService;
import com.example.mission1project.service.HistoryService;
import com.example.mission1project.service.WifiService;

import java.util.ArrayList;
import java.util.List;

public class BookmarkProcess {

    //북마크 그룹 리스트 가져오기
    public List<BookMark> getBookMarkGrpList(){
        BookMarkService service = new BookMarkService();

        List<BookMark> bookMarkList = new ArrayList<>();
        bookMarkList = service.getBookMarkGrpList();

        return bookMarkList;
    }

    public BookMark getBookMarkGrpInfo(String id){
        BookMarkService service = new BookMarkService();
        return service.getBookMarkGrpInfo(id);
    }
    public boolean insertBookMarkGrp(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.insertBookmarkGrp(bookMark);
    }

    public boolean updateBookMarkGrp(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.updateBookmarkGrp(bookMark);
    }

    public boolean deleteBookMarkGrp(String id) {
        BookMarkService service = new BookMarkService();
        return service.deleteBookMarkGrp(id);
    }

    public boolean insertBookMark(BookMark bookMark) {
        BookMarkService service = new BookMarkService();
        return service.insertBookmark(bookMark);
    }

    public List<BookMark> getBookMarkList(){
        BookMarkService service = new BookMarkService();

        List<BookMark> bookMarkList = new ArrayList<>();
        bookMarkList = service.getBookMarkList();

        return bookMarkList;
    }

    public boolean deleteBookMark(String id) {
        BookMarkService service = new BookMarkService();
        return service.deleteBookMark(id);
    }
}

