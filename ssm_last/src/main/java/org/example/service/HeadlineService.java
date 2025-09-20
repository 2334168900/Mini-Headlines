package org.example.service;

import org.example.stu.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.stu.vo.portaivo;
import org.example.utils.Result;

/**
* @author 23341
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2025-06-14 12:03:25
*/
public interface HeadlineService extends IService<Headline> {

    Result findNews(portaivo portaivo);

    Result showHead(int hid);

    Result savehead(Headline headline,String token);

    Result findhead(Integer hid);

    Result upte(Headline headline);

    Result delet(Integer hid);
}
