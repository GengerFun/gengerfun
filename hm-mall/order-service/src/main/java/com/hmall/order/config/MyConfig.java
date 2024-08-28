package com.hmall.order.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author byzhao
 * @version 1.0.0
 * @date 2023-03-18 17:25
 * @description TODO
 */
@Configuration
public class MyConfig {


    // 1.1 声明交换机（普通交换机）

    @Bean("commonExchange")
    public Exchange commonExchange() {
        return ExchangeBuilder.topicExchange("common_exchange").durable(true).build();
    }

    // 1.2 声明交换机（死信换机）
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.topicExchange("dead_letter_exchange").durable(true).build();
    }

    // 2.1 声明（普通队列）
    @Bean("commonQueue")
    public Queue commonQueue() {
        return QueueBuilder
                .durable("common_queue")
                .withArgument("x-message-ttl", 30*60*1000)
                .deadLetterExchange("dead_letter_exchange") // 将死信路由到死信交换机
                .deadLetterRoutingKey("deadLetter.aa")  // 路由规则
                .build();
    }

    // 2.2 声明（死信队列）
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return QueueBuilder.durable("dead_letter_queue").build();
    }

    // 3.1 绑定普通队列到普通交换机
    @Bean("commonBinding")
    public Binding commonBinding() {
        return BindingBuilder.bind(commonQueue()).to(commonExchange()).with("common.#").noargs();
    }

    @Bean("deadLetterBinding")
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("deadLetter.#").noargs();
    }


}
