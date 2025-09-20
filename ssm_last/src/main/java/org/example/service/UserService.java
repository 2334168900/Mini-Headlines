package org.example.service;

import org.example.stu.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.utils.Result;

/**
* @author 23341
* @description 针对表【news_user】的数据库操作Service
* @createDate 2025-06-14 12:03:25
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result userlist(String token);

    Result username(String username);

    Result zhuce(User user);

    Result chlogin(String token);
}
