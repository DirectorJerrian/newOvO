package com.example.hotel.po;

import com.example.hotel.enums.City;
import com.example.hotel.enums.HotelStar;

public class Hotel {
    private Integer id;
    private String hotelName;
    private String address;
    /**
     * 城市
     */
    private City city;
    private HotelStar hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    /**
     * 酒店管理员id
     */
    private Integer managerId;
    private String cover;
    private Integer minimumPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public HotelStar getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(HotelStar hotelStar) {
        this.hotelStar = hotelStar;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }

    public Integer getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Integer minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}

