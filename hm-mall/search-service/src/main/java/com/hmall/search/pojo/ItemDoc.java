package com.hmall.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月25日 22:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDoc implements Serializable {

    private Long id;//商品id
    private String name;//商品名称
    private Long price;//价格（分）

    private String image;//商品图片
    private String category;//分类名称
    private String brand;//品牌名称

    private Integer sold;//销量
    private Integer commentCount;//评论数

}
