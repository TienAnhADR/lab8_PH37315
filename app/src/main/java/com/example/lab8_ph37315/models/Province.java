package com.example.lab8_ph37315.models;

public class Province {
    private int ProvinceID;
    private String ProvinceName;

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public Province(int provinceID, String provinceName) {
        ProvinceID = provinceID;
        ProvinceName = provinceName;
    }

    public Province() {
    }
}
