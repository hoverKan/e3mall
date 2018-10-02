package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author hoverkan
 * @create 2018-10-02 8:21
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
