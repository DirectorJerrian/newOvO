package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author zy
 * @date 2022/1/2 12:30
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //新增评论
    @Override
    public ResponseVO commitComment(CommentVO commentVO){
        //写入数据库
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        commentVO.setCreate_time(curdate);
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentVO,comment);
        commentMapper.commitComment(comment);
        return ResponseVO.buildSuccess(true);
    };

    //通过用户Id获取该用户评论
    @Override
    public List<Comment> getCommentsByUserId(Integer userId){
        List<Comment> commentList=commentMapper.selectByUserId(userId);
        System.out.println("订单id");
        System.out.println(commentList.get(0).getOrderId());
        return commentList;
    };

    //通过酒店Id获取该用户评论
    @Override
    public List<Comment> getCommentsByHotelId(Integer hotelId){
        List<Comment> commentList=commentMapper.selectByHotelId(hotelId);
        return commentList;
    };

    //通过评论Id获取该评论
    @Override
    public Comment getCommentByCommentId(Integer commentId){
        Comment comment=commentMapper.selectByCommentId(commentId);
        return comment;
    };

}
