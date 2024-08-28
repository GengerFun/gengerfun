package com.hmall.search.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 范俊哲
 * @Description
 * @date 2023年03月25日 22:43
 */
@Component
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticConfig {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
