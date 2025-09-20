package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.stu.Type;
import org.example.service.TypeService;
import org.example.mapper.TypeMapper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 23341
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2025-06-14 12:03:25
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    TypeMapper typeMapper;

    @Override
    public Result slt() {

        List<Type> types = typeMapper.selectList(null);

        return Result.ok(types);
    }
}




