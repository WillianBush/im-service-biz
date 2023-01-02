package net.chenlin.dp.common.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.s3")
@Data
@ToString
public class S3Model {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketname;

}
