package com.hmall.order.web;

import com.hmall.common.feignclient.ItemClient;
import com.hmall.common.pojo.Item;
import com.hmall.common.util.SnowflakeIdWorker;
import com.hmall.common.vo.OrderVo;
import com.hmall.order.pojo.Order;
import com.hmall.order.pojo.OrderDetail;
import com.hmall.order.service.IOrderService;
import com.hmall.order.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
   /* @Autowired
    private ItemClient itemClient;*/

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/{id}")
    public Order queryOrderById(@PathVariable("id") Long orderId) {
        return orderService.getById(orderId);
    }

    /*@PostMapping
    public Long addOrder(@RequestBody OrderVo orderVo) {
        log.info("orderVo参数为{}", orderVo);
        //使用snakeflower生成61为雪花算法
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        System.out.println(id);
        Item item = itemClient.dateById(orderVo.getItemId());
        Long price = item.getPrice();
        Integer num = orderVo.getNum();
        long l = price * num;
        Order order = Order
                .builder()
                .id(id)
                .paymentType(orderVo.getPaymentType())
                .totalFee(l)
                .build();
        OrderDetail orderDetail = OrderDetail
                .builder()
                .orderId(id)
                .itemId(item.getId())
                .num(num)
                .price(l)
                .build();
        orderDetailService.save(orderDetail);

        Integer status = order.getStatus();

        String message = status.toString();

        rabbitTemplate.convertAndSend("common_exchange","common.aaa",message);

        orderService.save(order);
        return id;
    }*/
}
