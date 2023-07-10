package com.example.mission1project.dto;

public class BookMark {
    private String ID;                  //pk
    private String bookmarkGroupNm;     //북마크 그룹 이름
    private Integer seq;                 //순서
    private String regDate;             //등록일자
    private String updateDate;          //수정일자
    private String wifiNm;
    private String bookMarkGrpId;
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBookmarkGroupNm() {
        return bookmarkGroupNm;
    }

    public void setBookmarkGroupNm(String bookmarkGroupNm) {
        this.bookmarkGroupNm = bookmarkGroupNm;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getWifiNm() {
        return wifiNm;
    }

    public void setWifiNm(String wifiNm) {
        this.wifiNm = wifiNm;
    }

    public String getBookMarkGrpId() {
        return bookMarkGrpId;
    }

    public void setBookMarkGrpId(String bookMarkGrpId) {
        this.bookMarkGrpId = bookMarkGrpId;
    }
}
