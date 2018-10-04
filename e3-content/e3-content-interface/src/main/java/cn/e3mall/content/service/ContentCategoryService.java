package cn.e3mall.content.service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

import java.util.List;

/**
 * @author hoverkan
 * @create 2018-10-03 21:30
 */
public interface ContentCategoryService {

    List<EasyUITreeNode> getContentCatList(long parentId);
    E3Result addContentCategory(long parentId,String name);
}
