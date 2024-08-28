package com.hmall.search;

import com.hmall.search.config.ElasticConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@EnableFeignClients
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class SearchApplication {
    public static void main( String[] args ) {
        log.info("search-service 项目启动了..............");
        SpringApplication.run(SearchApplication.class,args);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(ElasticConfig elasticConfig){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create(elasticConfig.getUrl())
        ));
    }
}
