package com.hmall.search.service.impl;

import com.hmall.search.service.SearchItemService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月23日 23:50
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {


    @Resource
    private  RestHighLevelClient restHighLevelClient;

    @Override
    public List<String> suggestion(String key) {
        return null;
    }
}
