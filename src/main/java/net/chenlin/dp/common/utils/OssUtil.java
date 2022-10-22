package net.chenlin.dp.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.OSSModel;
import net.chenlin.dp.common.entity.OSSUploadResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class OssUtil {

    OSSModel ossModel;

    @Autowired
    public OssUtil(OSSModel ossModel) {
        this.ossModel = ossModel;
    }



    /*
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    private OSS getOSSClient(){
        return new OSSClientBuilder().build(ossModel.getAppPoint(),ossModel.getAccessKeyId(), ossModel.getAccessKeySecret());
    }


    /**
     * 创建模拟文件夹
     * @param folder   模拟文件夹名如"qj_nanjing/"
     * @return  文件夹名
     */
    public String createFolder(String folder){

        OSS ossClient = getOSSClient();
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在，不存在则创建
        if(!ossClient.doesObjectExist(ossModel.getBucketname(), keySuffixWithSlash)){
            //创建文件夹
            ossClient.putObject(ossModel.getBucketname(), keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            log.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(ossModel.getBucketname(), keySuffixWithSlash);
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param filePath Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public void deleteFile(String folder, String filePath){
        OSS ossClient = getOSSClient();
        String bucketName = ossModel.getBucketname();
        ossClient.deleteObject(bucketName, folder + filePath);
        log.info("删除" + bucketName + "下的文件" + folder + filePath + "成功");
    }

    public void deleteFile( String filePath){
        OSS ossClient = getOSSClient();
        String bucketName = ossModel.getBucketname();
        ossClient.deleteObject(bucketName, filePath);
        log.info("删除" + bucketName + "下的文件" +  filePath + "成功");
    }

    /**
     * 上传图片至OSS
     * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @return String 返回的唯一MD5数字签名
     * */
    public String uploadObjectToOSS(File file, String folder) {
        OSS ossClient = getOSSClient();
        String bucketName = ossModel.getBucketname();

        String resultStr = null;
        String ossFilePath=null;
        try {
            //以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            resultStr = putResult.getETag();
//            ossFilePath = ossModel.getDomain()+"/"+folder+"/" + fileName;
            is.close();
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }


    /**
     *
     * @param inputStream 上传文件流
     * @param fileName 文件名
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @param fileSize  文件大小
     * @return 返回的唯一MD5数字签名
     */
    public OSSUploadResp uploadObjectToOSS(InputStream inputStream, String fileName, String folder, Long fileSize) {
        OSS ossClient = getOSSClient();
        String bucketName = ossModel.getBucketname();

        String resultStr = null;
        String ossFilePath=null;
        try {
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(inputStream.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder+"/" + fileName, inputStream, metadata);
            //解析结果
            resultStr = putResult.getETag();
            ossFilePath = "https://" + ossModel.getEndpoint() + "/"+folder+"/" + fileName;
            inputStream.close();
        } catch (Exception e) {
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return new OSSUploadResp(ossFilePath,resultStr);
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }


    public String downloadFileAndReadString(String filePath){
        StringBuilder buffer = new StringBuilder();
        try {
            OSS ossClient = getOSSClient();
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(ossModel.getBucketname(), filePath);

            BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                buffer.append("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
            // ossObject对象使用完毕后必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            ossObject.close();
            return buffer.toString();
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.",oe);
        } catch (Throwable ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message:" + ce.getMessage());
        }
        return null;
    }

    //测试
//    public static void main(String[] args) {
        //初始化OSSClient
//        OSSClient ossClient=new AliyunOSSClientUtil().getOSSClient();
//        //上传文件
//        String files="D:\\image\\1010.jpg,D:\\image\\1111.jpg,D:\\image\\1212.jpg,D:\\image\\1313.jpg,D:\\image\\2222.jpg,D:\\image\\3333.jpg,"
//                + "D:\\image\\4444.jpg,D:\\image\\5555.jpg,D:\\image\\6666.jpg,D:\\image\\7777.jpg,D:\\image\\8888.jpg";
//        String[] file=files.split(",");
//        for(String filename:file){
//            //System.out.println("filename:"+filename);
//            File filess=new File(filename);
//            String md5key = AliyunOSSClientUtil.uploadObject2OSS(ossClient, filess, BACKET_NAME, FOLDER);
//            log.info("上传后的文件MD5数字唯一签名:" + md5key);
//            //上传后的文件MD5数字唯一签名:40F4131427068E08451D37F02021473A
//        }


//    }

}
