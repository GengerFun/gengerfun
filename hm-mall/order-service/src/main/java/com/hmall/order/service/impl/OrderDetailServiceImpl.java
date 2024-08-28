package com.hmall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.order.mapper.OrderDetailMapper;
import com.hmall.order.pojo.OrderDetail;
import com.hmall.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月19日 21:09
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
