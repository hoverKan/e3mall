package cn.e3mall.controller;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理Controller
 *
 * @author hoverkan
 * @create 2018-10-04 16:43
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content){
        // 调用服务把内容数据保存到数据库
        E3Result result = contentService.addContent(content);
        return result;
    }
}
