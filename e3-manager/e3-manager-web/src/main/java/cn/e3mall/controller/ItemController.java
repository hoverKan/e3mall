package cn.e3mall.controller;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理Controller
 *
 * @author hoverkan
 * @create 2018-10-01 16:16
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

}
