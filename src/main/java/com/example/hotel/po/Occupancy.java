package com.example.hotel.po;


import com.example.hotel.enums.RoomType;

public class Occupancy {
    private Integer id;
    private Integer hotelId;
    private RoomType roomType;
    private String checkInDate;
    private String checkOutDate;
    private Integer orderId;

    public Integer getId(){
        return id;
    }

    public Integer getHotelId(){
        return hotelId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
