package cn.e3mall.item.controller;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品详情页面展示Controller
 *
 * @author hoverkan
 * @create 2018-10-10 17:09
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model){
        // 调用服务取商品基本信息
        TbItem item = itemService.getItemById(itemId);
        // 取商品描述信息
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        // 把信息传递给页面
        model.addAttribute("item",new Item(item));
        model.addAttribute("itemDesc", itemDesc);
        // 返回逻辑视图
        return "item";
    }
}
