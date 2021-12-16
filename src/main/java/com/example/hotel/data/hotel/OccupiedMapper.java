package com.example.hotel.data.hotel;

import com.example.hotel.po.Occupancy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface OccupiedMapper {
    /**
    获取某酒店某种房型在某一段时间内的占用数量
     */
    int getOccupiedNumber(@Param("hotelId") int hotelId, @Param("roomType") String roomType, @Param("checkInDate") String checkInDate, @Param("checkOutDate") String checkOutDate);

    /**
     * 插入一段酒店房间占用信息
     * @param occupancy
     */
    void insertOccupancy(Occupancy occupancy);

    /**
     * 删除跟该订单有关的所有酒店房间占用信息
     */
    void deleteOccupancy(@Param("orderId") int orderId);
}
