package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.stu.Headline;
import org.example.service.HeadlineService;
import org.example.mapper.HeadlineMapper;
import org.example.stu.vo.portaivo;
import org.example.utils.JwtHelper;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 23341
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2025-06-14 12:03:24
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNews(portaivo portaivo) {

        IPage<Map>  page =new Page<>(portaivo.getPageNum(),portaivo.getPageSize());
        headlineMapper.selectMyPage(page,portaivo);
        List<Map> records = page.getRecords();

        Map data =new HashMap<>();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPages",page.getPages());
        data.put("totalSize",page.getTotal());

        Map dt = new HashMap();
        dt.put("pageInfo",data);

        return Result.ok(dt);
    }

    @Override
    public Result showHead(int hid) {
        Map map = headlineMapper.selectAll(hid);
        Map data =new HashMap<>();
        data.put("headline",map);

        Headline headline = new Headline();
        headline.setHid((Integer) map.get("hid"));
        headline.setVersion((Integer) map.get("version"));
        headline.setPageViews((Integer) map.get("pageViews")+1);

        headlineMapper.updateById(headline);

        return Result.ok(data);
    }

    @Override
    public Result savehead(Headline headline,String token) {
        int i = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(i);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findhead(Integer hid) {

        Headline headline = headlineMapper.selectById(hid);


        Map map = new HashMap();
        map.put("headline",headline);


        return Result.ok(map);
    }

    @Override
    public Result upte(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result delet(Integer hid) {

        headlineMapper.deleteById(hid);
        return Result.ok(null);
    }


}




