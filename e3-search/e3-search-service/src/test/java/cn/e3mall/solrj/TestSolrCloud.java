package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author hoverkan
 * @create 2018-10-09 17:26
 */
public class TestSolrCloud {

    @Test
    public void testAddDocument() throws Exception{
        // 创建一个集群的连接，应该使用CloudSolrServer
        // zkHost:zookeeper的地址列表
        CloudSolrServer solrServer =
                new CloudSolrServer("192.168.25.160:2181,192.168.25.160:2182,192.168.25.160:2183");

        // 设置一个dafaultCollection属性
        solrServer.setDefaultCollection("collection2");
        // 创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        // 向文档中添加域
        document.addField("id", "solrCloud01");
        document.addField("item_title","测试商品");
        document.addField("item_price",1000);
        // 把文件写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }

    @Test
    public void testQueryDocument() throws Exception{
        // 创建一个集群的连接，CloudSolrServer
        CloudSolrServer solrServer =
                new CloudSolrServer("192.168.25.160:2181,192.168.25.160:2182,192.168.25.160:2183");
        // 设置一个defaultCollection属性
        solrServer.setDefaultCollection("collection2");
        // 创建一个SolrQuery 查询对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
        query.setQuery("*:*");
        // 执行查询
        QueryResponse queryResponse = solrServer.query(query);
        // 取查询结果
        SolrDocumentList responseResults = queryResponse.getResults();
        System.out.println("总记录数：" + responseResults.getNumFound());
        // 打印结果
        for (SolrDocument document : responseResults) {
            System.out.println(document.get("id"));
            System.out.println(document.get("item_title"));
            System.out.println(document.get("item_price"));
        }
    }

}
