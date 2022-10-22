package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.OSSUploadResp;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.OssUtil;
import net.chenlin.dp.common.utils.UploadUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.modules.biz.room.service.RoomService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.chenlin.dp.common.utils.UploadUtils.removeDomain;

/**
 * 富文本上传controller
 *
 * @author wang<fangyuan.co @ outlook.com>
 */
@RestController
@RequestMapping("/editor")
@AllArgsConstructor
@Slf4j
public class SysEditorUploadController {

    private static final String EDITOR_IMG_UPLOAD_DIR = "editor/";
    private MemberService memberService;

    private RoomService roomService;
    private OssUtil ossUtil;

    /**
     * 上传图片
     *
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("系统内上传图片")
    public Map<String, Object> editorUpload(HttpServletRequest request) {
        // 大部分场景下，每次仅上传一张图片
        Map<String, Object> results = new HashMap<>(1);
        results.put("errno", 0);
        try {
            List<String> pathList = UploadUtils.uploadFile(request, EDITOR_IMG_UPLOAD_DIR);
            results.put("data", pathList);
        } catch (Exception e) {
            log.error("upload", e);
            results.put("errno", 500);
        }
        return results;
    }


    @ApiOperation("上传图片")
    @RequestMapping(value = {"/uploadPic"}, method = {RequestMethod.POST, RequestMethod.OPTIONS})
    public Resp editorUploadPic(@RequestParam(value = "file", required = true) MultipartFile file) {
        String fileName = file.getOriginalFilename();  // 文件名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String ossPath = "img_sys/upload";

        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String now = format.format(System.currentTimeMillis());
                String iconName = "APP" + now + fileExtension;

                OSSUploadResp resp = ossUtil.uploadObjectToOSS(file.getInputStream(), iconName, ossPath, file.getSize());
                log.info(removeDomain(resp.getFilePath()));
                return  Resp.ok("上传成功", removeDomain(resp.getFilePath()));
            } catch (Exception e) {
                log.error("上传失败", e);
                return Resp.error("上传失败");
            }
        } else {
            return Resp.error("上传失败,文件类型错误");
        }
    }

    @ApiOperation("上传头像")
    @RequestMapping(value = {"/uploadMemberPic"}, method = {RequestMethod.POST})
    public String editorUploadHeadPic(@RequestParam(value = "file", required = true) MultipartFile file, String uid, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();  // 文件名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String ossPath = "img_sys/upload/member";

        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String now = format.format(System.currentTimeMillis());
                String iconName = uid + "-" + now + fileExtension;

                OSSUploadResp resp = ossUtil.uploadObjectToOSS(file.getInputStream(), iconName, ossPath, file.getSize());
                Resp<MemberEntity> respMember = memberService.getMemberById(uid);
                if (respMember.getData() == null) {
                    return "用户uid不存在";
                }
                MemberEntity member = respMember.getData();
                member.setHeadpic(removeDomain(resp.getFilePath()));
                memberService.updateMember(member);
                log.info(removeDomain(resp.getFilePath()));
                return removeDomain(resp.getFilePath());
            } catch (Exception e) {
                log.error("上传失败", e);
                return "上传失败";
            }
        } else {
            return "文件类型不支持，上传失败";
        }
    }

    @RequestMapping(value = {"/uploadRoomHeadPic"}, method = {RequestMethod.POST})
    @ApiOperation("上传聊天室头像")
    public String editorUploadRoomHeadPic(@RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) {
        String roomid = request.getHeader("x-access-roomid");
        String fileName = file.getOriginalFilename();  // 文件名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String ossPath = "img_sys/upload/room";
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String now = format.format(System.currentTimeMillis());

                String iconName = roomid + "-" + now + fileExtension;
                OSSUploadResp resp = ossUtil.uploadObjectToOSS(file.getInputStream(), iconName, ossPath, file.getSize());
                Resp<RoomEntity> respRoom = roomService.getRoomById(roomid);
                RoomEntity room = respRoom.getData();
                room.setHeadimg(removeDomain(resp.getFilePath()));
                roomService.updateRoom(room);
                return removeDomain(resp.getFilePath());
            } catch (Exception e) {
                log.error("上传失败", e);
                return "上传失败";
            }
        } else {
            return "文件类型不支持，上传失败";
        }
    }
}
