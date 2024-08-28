package com.hmall.common.feignclient;

import com.hmall.common.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月25日 22:58
 */

@FeignClient("itemservice")
public interface ItemClient {

    @GetMapping("/item/all")
    public List<Item> search();

}
