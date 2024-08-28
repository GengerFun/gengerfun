package com.hmall.common.feignclient;

import com.hmall.common.dto.PageDTO;
import com.hmall.common.pojo.Item;
import com.hmall.common.vo.PageVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月19日 20:52
 */
public interface ItemFeignClient {

    @GetMapping("/item/{id}")
    Item dateById(@PathVariable Long id);

    /**
     * 分页查询
     *
     * @param pageVo
     * @return
     */
    @GetMapping("/item/list")
    PageDTO<Item> getAllBrand(PageVo pageVo);
}
