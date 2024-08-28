package com.hmall.item.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hmall.common.dto.PageDTO;
import com.hmall.common.feignclient.ItemFeignClient;
import com.hmall.common.pojo.Item;
import com.hmall.common.vo.PageVo;
import com.hmall.item.service.IItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController implements ItemFeignClient {

    @Autowired
    private IItemService itemService;

    @GetMapping("/all")
    public List<Item> search(){
        List<Item> list = itemService.list();
        return list;
    }

    /**
     * 分页查询
     *
     * @param pageVo
     * @return
     */
    @GetMapping("/list")
    @Override
    public PageDTO<Item> getAllBrand(PageVo pageVo) {
        log.info("执行了{}", pageVo);
        return itemService.getAllBrand(pageVo);
    }

    /**
     * 新增商品
     *
     * @param item
     */
    @PostMapping
    public void addBrand(@RequestBody Item item) {
        log.info("{}", item);
        boolean save = itemService.save(item);
        if (save) {
            System.out.println("保存成功");
        }
    }

    /**
     * 上下架商品
     *
     * @param id
     * @param status
     */
    @PutMapping("/status/{id}/{status}")
    public void getBrandById(@PathVariable("id") Long id, @PathVariable("status") Integer status) {

        log.info("执行了{},{}", id, status);
        Item item = new Item();
        item.setId(id);
        item.setStatus(status);
        boolean b = itemService.updateById(item);
        log.info("执行了{}", id);

    }

    /**
     * 修改商品
     *
     * @param item
     */
    @PutMapping
    public void getBrandById(@RequestBody Item item) {

        log.info("执行了{}", item);

        boolean b = itemService.updateById(item);

        if (b) {
            System.out.println("修改成功了");
        }
    }

    /**
     * 数据回显
     *
     * @param id
     */
    @GetMapping("/{id}")
    @Override
    public Item dateById(@PathVariable Long id) {
        Item byId = itemService.getById(id);
        com.hmall.common.pojo.Item item = new com.hmall.common.pojo.Item();
        BeanUtils.copyProperties(byId, item);
        return item;
    }


    /**
     * 删除商品
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void getBrandById(@PathVariable Long id) {
        log.info("执行了{}", id);

        boolean b = itemService.removeById(id);

        if (b) {
            System.out.println("修改成功了");
        }
    }


 //   @PutMapping("/stock/{itemId}/{num}")
    @RabbitListener(queues = {"deadLetterQueue"})
    public void getMessage(String message, @PathVariable("itemId") Long itemId, @PathVariable("num") Integer num) throws InterruptedException {
        log.info("订单状态为{},商品Id{},购买件数{}", message, itemId, num);
        int number = Integer.parseInt(message);
        //Thread.sleep(30 * 60 * 1000);
        Item byId = itemService.getById(itemId);
        if (number > 1) {
//LambdaQueryWrapper
            LambdaQueryWrapper<Item> lqw = Wrappers.lambdaQuery(Item.class);
            Integer n = byId.getStock() - num;
            lqw.eq(Item::getId, itemId);
            Item item = Item.builder()
                    .stock(n)
                    .build();
            itemService.update(item,lqw);
        }
    }
}
