package com.hmall.common.vo;

import lombok.Data;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月19日 19:38
 */
@Data
public class OrderVo {
    /**
     * 付款方式：1:微信支付, 2:支付宝支付, 3:扣减余额
     */
    private Integer paymentType;
    /**
     * 商品id
     */
    private Long itemId;
    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 收获地址id
     */
    private Long addressId;

}
