package org.example.controller;

import org.example.service.HeadlineService;
import org.example.stu.Headline;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class headlinecontroller {

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token){
       Result result = headlineService.savehead(headline,token);
       return result;
    }


    @PostMapping("findHeadlineByHid")
    public Result findHead(Integer hid){
        Result result = headlineService.findhead(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.upte(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result delet(Integer hid){
        Result result = headlineService.delet(hid);
        return result;
    }
}
