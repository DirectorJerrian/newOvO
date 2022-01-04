package com.example.hotel.controller.comment;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zy
 * @date 2022/1/2 12:26
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/addComment")
    public ResponseVO addComment(@RequestBody CommentVO commentVO) throws ServiceException {
        commentService.commitComment(commentVO);
        return ResponseVO.buildSuccess(true);
    }
    @GetMapping("/{userId}/getUserComments")
    public ResponseVO getUserComments(@PathVariable Integer userId){
        return ResponseVO.buildSuccess(commentService.getCommentsByUserId(userId));
    }

    @GetMapping("/{hotelId}/getHotelComments")
    public ResponseVO getHotelComments(@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(commentService.getCommentsByHotelId(hotelId));
    }

    @GetMapping("/{commentId}/getComment")
    public ResponseVO getComment(@PathVariable Integer commentId){
        return ResponseVO.buildSuccess(commentService.getCommentByCommentId(commentId));
    }
}
