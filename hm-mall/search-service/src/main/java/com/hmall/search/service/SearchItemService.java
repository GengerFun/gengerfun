package com.hmall.search.service;

import java.util.List;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月23日 23:49
 */
public interface SearchItemService {

    List<String> suggestion(String key);

}
