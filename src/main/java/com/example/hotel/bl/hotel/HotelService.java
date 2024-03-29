package com.example.hotel.bl.hotel;

import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.AvailableHotelVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;

    /**
     * 删除酒店
     * @param hotelId
     * @throws
     */
    void deleteHotel(int hotelId) throws ServiceException;

    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomOccupancy(Integer orderId, Integer hotelId, String roomType, String checkInDate, String checkOutDate, Integer rooms);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 改为根据日期和地点搜索有可用房间的酒店
     */
    List<HotelVO> availableHotel(String city, String checkInDate, String checkOutDate);

    /**
     * 列表获取管理员管理目标酒店列表
     * @param userid
     */
    List<HotelVO> getTargetHotel(int userid);

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId,String roomType,String checkInDate,String checkOutDate);

    ResponseVO updateHotelInfo(int id, String name,String bizRegion, String description);
    /** @param cover */
    ResponseVO updateHotelCover(int id, MultipartFile cover);

    /*
    * 获得所有商圈
    * */

    List<String> getBizRegions();

    int findHotel(String hotelName);


}
