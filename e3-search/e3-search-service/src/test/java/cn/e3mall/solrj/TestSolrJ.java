package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author hoverkan
 * @create 2018-10-07 22:03
 */
public class TestSolrJ {
    @Test
    public void testSolrJ() throws Exception {

        // 创建一个SolrServer对象，创建一个连接，参数solr 的服务
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.160:8888/solr/collection1/");
        // 创建一个文档对象SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        // 向文档对象中添加域。文档中必须包含一个id域，所有域的名称必须在schema.xml中定义。
        document.addField("id","doc01");
        document.addField("item_title","测试商品01");
        document.addField("item_price",1000);
        // 把文档写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }

    @Test
    public void testDelete() throws Exception{
        // 创建一个SolrServer对象，创建一个连接，参数solr 的服务
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.160:8888/solr/collection1/");
        // 删除文档
        // solrServer.deleteById("doc01");
        solrServer.deleteByQuery("id:doc01");
        // 提交
        solrServer.commit();
    }

    @Test
    public void queryIndex() throws Exception {
        // 创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.160:8888/solr/collection1/");
        // 创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
//        query.setQuery("*:*");
        query.set("q", "*:*");
        // 执行查询，QueryResponse对象
        QueryResponse queryResponse = solrServer.query(query);
        // 取文档列表，取出结果的总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 遍历文档列表，获取域的内容
        for (SolrDocument document : solrDocumentList) {
            System.out.println(document.get("id"));
            System.out.println(document.get("item_title"));
            System.out.println(document.get("item_sell_point"));
            System.out.println(document.get("item_price"));
            System.out.println(document.get("item_image"));
            System.out.println(document.get("item_category_name"));
        }
    }

    @Test
    public void queryIndexFuza() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.160:8888/solr/collection1/");
        // 创建一个SolrQuery对象
        SolrQuery query = new SolrQuery();
        // 设置查询条件
        query.setQuery("手机");
        query.setStart(0);
        query.setRows(20);
        query.set("df", "item_title");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        // 执行查询，QueryResponse对象
        QueryResponse queryResponse = solrServer.query(query);
        // 取文档列表，取出结果的总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 遍历文档列表，获取域的内容
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        for (SolrDocument document : solrDocumentList) {
            System.out.println(document.get("id"));
            // 取高亮显示
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0){
                title = list.get(0);
            } else {
                title = (String) document.get("item_title");
            }

            System.out.println(title);
            System.out.println(document.get("item_sell_point"));
            System.out.println(document.get("item_price"));
            System.out.println(document.get("item_image"));
            System.out.println(document.get("item_category_name"));
        }
    }

}
