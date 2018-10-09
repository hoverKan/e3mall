package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

/**
 * @author hoverkan
 * @create 2018-10-09 9:56
 */
public interface SearchService {

    SearchResult search(String keyword,int page,int rows) throws Exception;
}
