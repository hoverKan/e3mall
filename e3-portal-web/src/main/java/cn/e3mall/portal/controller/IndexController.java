package cn.e3mall.portal.controller;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页展示Controller
 *
 * @author hoverkan
 * @create 2018-10-03 20:23
 */
@Controller
public class IndexController {

    @Value("${CONTENT_LUNBO_ID}")
    private long CONTENT_LUNBO_ID;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        // 查询内容列表
        List<TbContent> ad1List = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        model.addAttribute("ad1List",ad1List);
        return "index";
    }
}
