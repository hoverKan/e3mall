package cn.e3mall.search.mapper;

import cn.e3mall.common.pojo.SearchItem;

import java.util.List;

/**
 * @author hoverkan
 * @create 2018-10-07 11:06
 */
public interface ItemMapper {

    List<SearchItem> getItemList();

    // 根据得到的消息中的itemId，查询数据库得到SearchItem 用于插入索引库
    SearchItem getItemById(long itemId);

}
