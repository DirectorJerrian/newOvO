package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String USER_NOT_EXIST = "账号不存在";

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AccountMapper accountMapper;
    //增加工作人员
    @Override
    public ResponseVO addManager(UserForm userForm) {
        User check=accountMapper.getAccountByName(userForm.getEmail());
        if(check!=null) return ResponseVO.buildFailure(ACCOUNT_EXIST);
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(UserType.HotelManager);
        try {
            adminMapper.addManager(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<User> getAllManagers() {
        return adminMapper.getAllManagers();
    }

    //删除工作人员
    @Override
    public ResponseVO deleteManager(int userId){
        try{
            adminMapper.deleteManager(userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(USER_NOT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }
}
