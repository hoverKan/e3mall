package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

/**
 * @author hoverkan
 * @create 2018-10-01 16:05
 */

public interface ItemService {

    TbItem getItemById(long itemId);

    EasyUIDataGridResult getItemList(int page,int rows);
}
