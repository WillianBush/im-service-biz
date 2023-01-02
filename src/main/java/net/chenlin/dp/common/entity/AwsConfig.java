package net.chenlin.dp.common.entity;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import net.chenlin.dp.modules.sys.service.impl.FileSystemServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class AwsConfig {

    @Resource
    private S3Model s3Model;

    @Value("${file-system-active}")
    private String fileSystemActive;

    @Bean
    public AmazonS3 amazonS3() {
        if (FileSystemServiceImpl.OSS.equals(fileSystemActive)){
            return null;
        }
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(s3Model.getAccessKeyId(), s3Model.getAccessKeySecret());
        return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
    }
}
