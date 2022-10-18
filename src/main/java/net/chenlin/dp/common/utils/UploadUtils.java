package net.chenlin.dp.common.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.OSSUploadResp;
import net.chenlin.dp.common.support.properties.GlobalProperties;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.room.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
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
    private OssUtil ossUtil;

    private static final GlobalProperties globalProperties = SpringContextUtils.getBean("globalProperties", GlobalProperties.class);

    /**
     * 上传文件处理(支持批量)
     */
    public static List<String> uploadFile(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        List<String> fileNames = new ArrayList<>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            File dirFile = new File(globalProperties.getUploadLocation());
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
                    String filePath = globalProperties.getUploadLocation() + fileName;
                    File file = new File(filePath);
                    file.setWritable(true, false);
                    try {
                        multipartFile.transferTo(file);
                        fileNames.add(globalProperties.getUploadMapping().concat(fileName));
                    } catch (Exception e) {
                        LOG.error(name + "保存失败", e);
                    }
                }
            }
        }
        return fileNames;
    }

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

    public String uploadRoomHeadpic(HttpServletRequest request, MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String ossPath = "img_sys/upload/room";
        String roomid = request.getHeader("x-access-roomid");

        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String now = format.format(System.currentTimeMillis());
                String iconName = roomid + "-" + now + fileExtension;
                //上传room 头像
                OSSUploadResp ossUploadResp = ossUtil.uploadObjectToOSS(file.getInputStream(), iconName, ossPath, file.getSize());
                return ossUploadResp.getFilePath();

//                try {
//                    //TODO 获取redis中room信息
////                    ossUtil.deleteFile(roomBeanCache.getImg());
//                } catch (Exception e) {
//                    LOG.error("删除图片失败", e);
//                }
//
//                roomBeanCache.setImg(removeDomain(resp.getFilePath()));
//                roomBeanCache.setUseCustomHeadpic(1);
//                roomService.update(BeanUtils.roomBeanTransferToRoomSimple(roomBeanCache));
//                accessRecordService.updateByEid(roomid, new String[]{"img"}, new String[]{roomBeanCache.getImg()});
//                ResponseUtils.json(response, 200, removeDomain(resp.getFilePath()), null);
            } catch (Exception e) {
                LOG.error("上传失败", e);
                return "上传失败";
            }
        } else {
            return "文件格式不正确";
        }
    }


    public String uploadMemberHeadpic(HttpServletRequest request, MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String ossPath = "img_sys/upload/member";
        String MEMBERID = (String) request.getSession().getAttribute("$MEMBERIDSESSION");

        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String now = format.format(System.currentTimeMillis());
                String iconName = MEMBERID + "-" + now + fileExtension;
                //上传room 头像
                OSSUploadResp ossUploadResp = ossUtil.uploadObjectToOSS(file.getInputStream(), iconName, ossPath, file.getSize());
                return ossUploadResp.getFilePath();

//                try {
//                    //TODO 获取redis中room信息
////                    ossUtil.deleteFile(roomBeanCache.getImg());
//                } catch (Exception e) {
//                    LOG.error("删除图片失败", e);
//                }
//
//                roomBeanCache.setImg(removeDomain(resp.getFilePath()));
//                roomBeanCache.setUseCustomHeadpic(1);
//                roomService.update(BeanUtils.roomBeanTransferToRoomSimple(roomBeanCache));
//                accessRecordService.updateByEid(roomid, new String[]{"img"}, new String[]{roomBeanCache.getImg()});
//                ResponseUtils.json(response, 200, removeDomain(resp.getFilePath()), null);
            } catch (Exception e) {
                LOG.error("上传失败", e);
                return "上传失败";
            }
        } else {
            return "文件格式不正确";
        }
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
