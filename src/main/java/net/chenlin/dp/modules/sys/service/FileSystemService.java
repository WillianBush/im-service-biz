package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.UploadResp;

import java.io.InputStream;

public interface FileSystemService {

    void deleteFile( String folder, String filePath);

    void deleteFile( String filePath);

    UploadResp uploadObject(InputStream inputStream, String fileName, String folder, Long fileSize);

    String getEndpoint();

    String removeBucketName(String uriWithBucketName);
}
