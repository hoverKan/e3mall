package cn.e3mall.content.service.impl;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.content.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容管理Service
 * @author hoverkan
 * @create 2018-10-04 16:36
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public E3Result addContent(TbContent content) {
        // 将内容插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        // 插入到数据库
        contentMapper.insert(content);
        // 缓存同步
        jedisClient.hdel(CONTENT_KEY,content.getCategoryId().toString());
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     *
     * @param cid
     * @return
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        //查询缓存
        try {
            String json = jedisClient.hget(CONTENT_KEY, cid + "");
            if (StringUtils.isNotBlank(json)) {
                return JsonUtils.jsonToList(json, TbContent.class);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        // 设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        // 执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        // 向缓存添加数据
        try {
            jedisClient.hset(CONTENT_KEY,cid+"",JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
