package com.fauzanirfanto.product_manager.utility;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransformPagination {

    private String sortByColumn;
    private String sort;
    String[] sortArr = new String[2];

    public Map<String,Object> transformPagination(List ls,//List dari DTO nya
                                                  Page page,
                                                  String column,
                                                  String value
    ){
        sortArr = page.getSort().toString().split(":");
        sortByColumn = sortArr[0];
        Boolean isSorted = sortByColumn.equals("UNSORTED");
        sortByColumn = isSorted?"id":sortByColumn;
        sort = isSorted?"asc":sortArr[1];
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("content", ls);
        map.put("total-data",page.getTotalElements());
        map.put("total-pages",page.getTotalPages());
        map.put("current-page",page.getNumber());
        map.put("sort",sort.trim().toLowerCase());
        map.put("sort-by",sortByColumn);
        map.put("size-per-page",page.getSize());
        map.put("column-name",column==null?"":column);
        map.put("value",value==null?"":value);
        return map;
    }


}
