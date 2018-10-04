package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理service
 * @author hoverkan
 * @create 2018-10-03 21:31
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        // 1.取查询参数id,parentId
        // 2.根据parentId查询tb_content_category,查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        // 设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        // 执行查询
        // 3.得到List<TbContentgory>
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        // 4.将列表转换成List<EasyUITreeNode>
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {
        // 创建一个 tb_content_category表对应的pojo
        TbContentCategory contentCategory = new TbContentCategory();
        // 设置pojo的属性
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        // 默认排序是1
        contentCategory.setSortOrder(1);
        // 新添加的节点一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        // 插入数据库
        contentCategoryMapper.insert(contentCategory);
        // 判断父节点的isparent属性，如果不是true改为true
        // 根据parentid查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            // 更新到数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        // 返回结果，返回E3Result,包含pojo
        return E3Result.ok(contentCategory);
    }
}
