package com.tas.modal;

import java.util.List;
import java.util.Map;

public class OutputRow {
    Map<String,String> yilNotMap;
    List<String> nots;
    String ders;

    public String getDers() {
        return ders;
    }

    public void setDers(String ders) {
        this.ders = ders;
    }

    public Map<String, String> getYilNotMap() {
        return yilNotMap;
    }

    public void setYilNotMap(Map<String, String> yilNotMap) {
        this.yilNotMap = yilNotMap;
    }

    public List<String> getNots() {
        return nots;
    }

    public void setNots(List<String> nots) {
        this.nots = nots;
    }
}
