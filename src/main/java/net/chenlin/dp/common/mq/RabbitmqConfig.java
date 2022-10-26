package net.chenlin.dp.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

@Slf4j
@SpringBootConfiguration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${server.port}")
    private String serverPort;

    public static final String BASIC_EXCHANGE = "im.message.exchange";

    public static final String USER_QUEUE = "im.message.queue";
    public static final String USER_ROUTING_KEY = "im.message.routing.key";


    public static final String GROUP_QUEUE = "im.message.group.queue";
}
