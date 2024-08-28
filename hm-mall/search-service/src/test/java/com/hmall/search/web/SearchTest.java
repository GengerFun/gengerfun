package com.hmall.search.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hmall.common.dto.PageDTO;
import com.hmall.common.feignclient.ItemClient;
import com.hmall.common.pojo.Item;
import com.hmall.search.pojo.ItemDoc;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Classes;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月25日 15:35
 */
@SpringBootTest
public class SearchTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ItemClient itemClient;

    @Test
    void search() throws IOException, InterruptedException {

        List<Item> search = itemClient.search();
        List<ItemDoc> list = search.stream().map((item) -> {

            ItemDoc build = ItemDoc.builder()
                    .id(item.getId())
                    .brand(item.getBrand())
                    .image(item.getImage())
                    .commentCount(item.getCommentCount())
                    .price(item.getPrice())
                    .sold(item.getSold())
                    .category(item.getCategory())
                    .name(item.getName()).build();
            return build;
        }).collect(Collectors.toList());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,10, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<>(10000));
        threadPoolExecutor.prestartAllCoreThreads();

        Lists.partition(list,2500).forEach((doc)->{

            BulkRequest bulkRequest = new BulkRequest("item");
            for (ItemDoc itemDoc : list) {

                IndexRequest objects = new IndexRequest();

                String jsonString = JSON.toJSONString(itemDoc);

                objects.id(itemDoc.getId().toString());

                objects.source(jsonString,XContentType.JSON);

                bulkRequest.add(objects);

            }

            BulkResponse b = null;
            try {
                b = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            b.status().getStatus();
        });

        threadPoolExecutor.execute(()->{

        });

        Thread.sleep(30000);
    }
}

