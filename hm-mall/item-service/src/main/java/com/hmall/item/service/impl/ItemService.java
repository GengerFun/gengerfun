package com.hmall.item.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.dto.PageDTO;
import com.hmall.common.vo.PageVo;
import com.hmall.item.mapper.ItemMapper;
import com.hmall.common.pojo.Item;
import com.hmall.item.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemService extends ServiceImpl<ItemMapper, Item> implements IItemService {


    @Override
    public PageDTO<Item> getAllBrand(PageVo pageVo) {
        log.info("执行了{}",pageVo);
        //构造分页构造器
        Page<Item> itemPage = new Page<>(pageVo.getPage(), pageVo.getSize());
        //执行查询
        Page page = this.page(itemPage);
        List records = page.getRecords();
        long total = page.getTotal();

        PageDTO<Item> itemPageDTO = new PageDTO<>();
        itemPageDTO.setList(records);
        itemPageDTO.setTotal(total);
        return itemPageDTO;
    }
}
