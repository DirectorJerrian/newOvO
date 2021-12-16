package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.OccupiedMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.Occupancy;
import com.example.hotel.vo.RoomVO;
import com.example.hotel.po.HotelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private OccupiedMapper occupiedMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public void insertRoomInfo(RoomVO roomVO) {
        roomMapper.insertRoom(roomVO);
    }

    @Override
    public void updateRoomInfo(Integer orderId, Integer hotelId, String roomType, String checkInDate, String checkOutDate, Integer rooms) {
        if(rooms>0){
            Occupancy occupancy = new Occupancy();
            occupancy.setOrderId(orderId);
            occupancy.setHotelId(hotelId);
            occupancy.setCheckInDate(checkInDate);
            occupancy.setCheckOutDate(checkOutDate);
            occupancy.setRoomType(RoomType.valueOf(roomType));
            for(int i=0; i<rooms; i++){
                occupiedMapper.insertOccupancy(occupancy);
            }
        }
        else if(rooms<0){
            occupiedMapper.deleteOccupancy(orderId);
        } else{
            System.out.println("Rooms Number Error!");
        }
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType, String checkInDate, String checkOutDate) {
        int occupied = occupiedMapper.getOccupiedNumber(hotelId, roomType, checkInDate, checkOutDate);
        int total = roomMapper.getRoomTotalNum(hotelId, roomType);
        return total-occupied;
    }

    @Override
    public void addRoomNum(int roomId){
        roomMapper.addTargetNum(roomId);

    }
    @Override
    public void subRoomNum(int roomId){
        roomMapper.subTargetNum(roomId);

    }

    //判断目标酒店是否有房型
    @Override
    public List<Boolean> hasType(Integer hotelId) {
        List<Boolean> allType= new ArrayList<>();
        System.out.println(hotelId+"MPL");
        List<RoomVO> bigList= roomMapper.getBig(hotelId);
        if(bigList.size()==0) allType.add(true);
        else allType.add(false);
        List<RoomVO> DoubleList= roomMapper.getDouble(hotelId);
        if(bigList.size()==0) allType.add(true);
        else allType.add(false);
        List<RoomVO> FamList= roomMapper.getFam(hotelId);
        if(bigList.size()==0) allType.add(true);
        else allType.add(false);
        return allType;
    }


    @Override
    public void deleteRoomsByHotelId(Integer hotelId){
        roomMapper.deleteRoomsByHotelId(hotelId);
    }
}
