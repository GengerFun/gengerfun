package com.hmall.item.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hmall.common.dto.PageDTO;
import com.hmall.common.vo.PageVo;
import com.hmall.common.pojo.Item;

public interface IItemService extends IService<Item> {
    PageDTO<Item> getAllBrand(PageVo pageVo);
}
