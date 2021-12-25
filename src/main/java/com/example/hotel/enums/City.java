package com.example.hotel.enums;

public enum City {
    Beijing("北京"),
    Nanjing("南京"),
    Chongqing("重庆"),
    Shanghai("上海");

    private String value;

    City(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
