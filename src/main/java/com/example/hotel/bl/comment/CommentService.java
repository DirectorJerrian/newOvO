package com.example.hotel.bl.comment;

import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author zy
 * @date 2022/1/2 12:28
 */
public interface CommentService {
    /**
     * 添加评论
     * @param commentVO
     * @return
     */
    ResponseVO commitComment(CommentVO commentVO);

    /**
     * 获取某userId下所有评论
     * @param userId
     * @return
     */
    List<Comment> getCommentsByUserId(Integer userId);

    /**
     * 获取某hotelId下所有评论
     * @param hotelId
     * @return
     */
    List<Comment> getCommentsByHotelId(Integer hotelId);

    /**
     * 根据commentId获取该评论
     * @param commentId
     * @return
     */
    Comment getCommentByCommentId(Integer commentId);
}
