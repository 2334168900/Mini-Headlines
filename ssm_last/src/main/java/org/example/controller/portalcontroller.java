package org.example.controller;


import org.example.service.HeadlineService;
import org.example.service.TypeService;
import org.example.stu.vo.portaivo;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class portalcontroller {

    @Autowired
    TypeService typeService;

    @Autowired
    HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result typelist(){
        Result result = typeService.slt();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody portaivo portaivo){
        Result result = headlineService.findNews(portaivo);
        return result;
    }


    @PostMapping("showHeadlineDetail")
    public Result showHeadline(Integer hid){
        Result result = headlineService.showHead(hid);
        return result;
    }

}
