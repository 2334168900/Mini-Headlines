package org.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.stu.User;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.example.utils.JwtHelper;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23341
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2025-06-14 12:03:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaUpdateWrapper<User> lamd =new LambdaUpdateWrapper<>();
        lamd.eq(User::getUsername,user.getUsername());
        User loguser = userMapper.selectOne(lamd);

        if(loguser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if(!StringUtils.isEmpty(user.getUserPwd()) &&
                MD5Util.encrypt(user.getUserPwd()).equals(loguser.getUserPwd())){
            String token =jwtHelper.createToken(Long.valueOf(loguser.getUid()));
            Map map = new HashMap();
            map.put("token",token);

            return Result.ok(map);
        }

        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result userlist(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int i = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(i);
        user.setUserPwd("");

        Map map = new HashMap();
        map.put("loginUser",user);

        return Result.ok(map);
    }

    @Override
    public Result username(String username) {

        LambdaUpdateWrapper<User> lamd=new LambdaUpdateWrapper<>();
        lamd.eq(User::getUsername,username);

        Long l = userMapper.selectCount(lamd);

        if(l>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }



        return Result.ok(null);
    }

    @Override
    public Result zhuce(User user) {
        LambdaUpdateWrapper<User> lamd=new LambdaUpdateWrapper<>();
        lamd.eq(User::getUsername,user.getUsername());
        Long l = userMapper.selectCount(lamd);
        if(l>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUsername()));
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result chlogin(String token) {
        boolean expiration = jwtHelper.isExpiration(token);

        if(expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}




