package cn.e3mall.service.impl;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hoverkan
 * @create 2018-10-01 16:06
 * <p>
 * 商品管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        // 根据主键查询
        // TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);

        // 根据查询条件查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andIdEqualTo(itemId);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        if (tbItems != null && tbItems.size() > 0){
            return tbItems.get(0);
        }
        return null;
    }
}
