package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author hoverkan
 * @create 2018-10-02 10:55
 */
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatlist(long parentId);
}
