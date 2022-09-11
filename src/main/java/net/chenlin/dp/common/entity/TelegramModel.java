package net.chenlin.dp.common.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "telegram")
@Data
@ToString
public class TelegramModel {
    private String robotToken;
    private String groupId;
}
