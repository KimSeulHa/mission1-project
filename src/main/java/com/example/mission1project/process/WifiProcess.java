package com.example.mission1project.process;

import com.example.mission1project.dto.FreeWifi;
import com.example.mission1project.dto.History;
import com.example.mission1project.service.HistoryService;
import com.example.mission1project.service.WifiService;

import java.util.ArrayList;
import java.util.List;

public class WifiProcess {

    //가까운 와이파이 검색 및 히스토리 저장
    public List<FreeWifi> getNearByfreeWifiList(String LAT, String LNT){
        WifiService wifiService = new WifiService();
        HistoryService historyService = new HistoryService();

        //STEP1. 검색 히스토리 INSERT
        FreeWifi freeWifi = new FreeWifi();
        freeWifi.setLat(LAT);
        freeWifi.setLnt(LNT);
        if(!historyService.insertInfo(freeWifi)){
            System.out.println("검색 히스토리 저장에 실패 하였습니다.");
        }

        //STEP2. 가까운 와이파이 정보 SELECT
        List<FreeWifi> freeWifiList = new ArrayList<>();
        freeWifiList = wifiService.getFreeWifi(LAT,LNT);

        return freeWifiList;
    }

    //히스토리 목록
    public List<History> getHistoryList(){
        List<History> historyList = new ArrayList<>();
        HistoryService historyService = new HistoryService();

        historyList = historyService.getHistoryList();

        return historyList;
    }

    public boolean deleteHistory(String id) {
        HistoryService historyService = new HistoryService();
        return historyService.deleteInfo(id);
    }
}
