package com.hmall.search.web;

import com.hmall.common.dto.PageDTO;
import com.hmall.common.pojo.Item;
import com.hmall.common.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月23日 23:49
 */
@RestController
@RequestMapping
public class SearchItemController{

   /* @Autowired
    private ItemClient itemClient;*/


    public PageDTO<Item> getAllBrand() {



        //itemClient.getAllBrand()

        return null;
    }
}
