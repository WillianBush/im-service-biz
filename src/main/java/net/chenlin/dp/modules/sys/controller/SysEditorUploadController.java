package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.UploadUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import net.chenlin.dp.modules.biz.room.entity.RoomEntity;
import net.chenlin.dp.modules.biz.room.service.RoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.chenlin.dp.common.utils.UploadUtils.removeDomain;

/**
 * 富文本上传controller
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/editor")
@AllArgsConstructor
public class SysEditorUploadController {

    private static final String EDITOR_IMG_UPLOAD_DIR = "editor/";
    private MemberService memberService;

    private RoomService roomService;
    /**
     * 上传图片
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("上传图片")
    public Map<String, Object> editorUpload(HttpServletRequest request) {
        // 大部分场景下，每次仅上传一张图片
        Map<String, Object> results = new HashMap<>(1);
        results.put("errno", 0);
        try {
            List<String> pathList = UploadUtils.uploadFile(request, EDITOR_IMG_UPLOAD_DIR);
            results.put("data", pathList);
        } catch (Exception e) {
            results.put("errno", 500);
        }
        return results;
    }


    @PostMapping("/uploadHeadPic")
    @ApiOperation("上传头像")
    public Map<String, Object> editorUploadHeadPic(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> results = new HashMap<>(1);
        results.put("errno", 0);
        String MEMBERID = (String) request.getSession().getAttribute("$MEMBERIDSESSION");
        try {
            String path = UploadUtils.uploadMemberHeadpic(request, file);
            Resp<MemberEntity> resp = memberService.getMemberById(MEMBERID);
            MemberEntity member = resp.getData();
            member.setHeadpic(removeDomain(path));
            memberService.updateMember(member);
            results.put("data", path);
        } catch (Exception e) {
            results.put("errno", 500);
        }
        return results;
    }

    @PostMapping("/uploadRoomHeadPic")
    @ApiOperation("上传聊天室头像")
    public Map<String, Object> editorUploadRoomHeadPic(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> results = new HashMap<>(1);
        results.put("errno", 0);
        String roomid = request.getHeader("x-access-roomid");

        try {
            String path = UploadUtils.uploadRoomHeadpic(request, file);
            Resp<RoomEntity> resp = roomService.getRoomById(roomid);
            RoomEntity room = resp.getData();
            room.setHeadimg(removeDomain(path));
            roomService.updateRoom(room);
            results.put("data", path);
        } catch (Exception e) {
            results.put("errno", 500);
        }
        return results;
    }
}
