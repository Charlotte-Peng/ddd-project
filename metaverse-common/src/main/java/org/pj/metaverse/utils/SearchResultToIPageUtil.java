package org.pj.metaverse.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ejlchina.searcher.SearchResult;

import java.util.Map;

/**
 * SearchResult转换为IPage对象工具类
 * @author pengjie
 * @date 14:28 2022/7/12
 **/
public class SearchResultToIPageUtil<T> {
    public static <T> IPage<T> convert(SearchResult<T> searchResult) {
        IPage<T> page = new Page<>();
        page.setTotal(searchResult.getTotalCount().longValue());
        page.setRecords(searchResult.getDataList());
        return page;
    }
    public static <T> IPage<T> convert(SearchResult<T> searchResult, Map<String, Object> params) {
        Object page1 = params.get("page");
        Object size = params.get("size");
        if (page1 == null || size == null) {
            return convert(searchResult);
        }
        IPage<T> page = new Page<>();
        page.setCurrent(Integer.parseInt(page1.toString()));
        page.setSize(Integer.parseInt(size.toString()));
        page.setTotal(searchResult.getTotalCount().longValue());
        page.setRecords(searchResult.getDataList());
        return page;
    }
}
