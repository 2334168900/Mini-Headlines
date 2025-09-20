package org.example.service;

import org.example.stu.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.utils.Result;

/**
* @author 23341
* @description 针对表【news_type】的数据库操作Service
* @createDate 2025-06-14 12:03:25
*/
public interface TypeService extends IService<Type> {

    Result slt();
}
