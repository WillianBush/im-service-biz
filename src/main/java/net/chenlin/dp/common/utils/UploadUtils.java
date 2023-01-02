package net.chenlin.dp.common.utils;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.support.properties.GlobalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author wang<fangyuan.co @ outlook.com>
 */
@DependsOn("springContextUtils")
@AllArgsConstructor
public class UploadUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UploadUtils.class);
    private static OssUtil ossUtil;

    private static final GlobalProperties globalProperties = SpringContextUtils.getBean("globalProperties", GlobalProperties.class);

    /**
     * 上传文件处理(支持批量)
     */
    public static List<String> uploadFile(HttpServletRequest request, String path) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        List<String> fileNames = new ArrayList<>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            File dirFile = new File(globalProperties.getUploadLocation().concat(path));
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            for (Iterator<String> iterator = multiRequest.getFileNames(); iterator.hasNext(); ) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    String uuid = UUID.randomUUID().toString();
                    String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                    String fileName = uuid + postFix;
                    String filePath = globalProperties.getUploadLocation() + path + fileName;
                    File file = new File(filePath);
                    file.setWritable(true, false);
                    try {
                        multipartFile.transferTo(file);
                        fileNames.add(globalProperties.getUploadMapping() + path + fileName);
                    } catch (Exception e) {
                        LOG.error(name + "保存失败", e);
                    }
                }
            }
        }
        return fileNames;
    }


        public static String removeDomain (String path){
            if (path.contains("http")) {
                try {
                    java.net.URL url = new java.net.URL(path);
                    return url.getPath();
                } catch (MalformedURLException e) {
                    LOG.error("获取路径失败");
                }
            }
            return null;
        }

    }
