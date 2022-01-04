package com.example.hotel.data.comment;

import com.example.hotel.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zy
 * @date 2022/1/2 12:01
 */

@Mapper
@Repository
public interface CommentMapper {
    int commitComment(Comment comment);

    List<Comment> selectByHotelId(Integer hotelId);

    List<Comment> selectByUserId(Integer userId);
}
