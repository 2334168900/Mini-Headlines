package org.example.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.example.stu.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.stu.vo.portaivo;

import java.util.Map;

/**
* @author 23341
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2025-06-14 12:03:24
* @Entity org.example.stu.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage iPage, @Param("portaivo") portaivo portaivo);


    Map selectAll(int hid);
}




