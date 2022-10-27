package net.chenlin.dp.modules.biz.member.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/mr/member")
@Api(tags = "用户管理")
@DependsOn("springContextUtils")
@AllArgsConstructor
public class MemberController extends AbstractController {

	private MemberService memberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "用户列表")
	public Page<MemberEntity> list(@RequestBody Map<String, Object> params) {
		return memberService.listMember(params);
	}
		
	/**
	 * 新增
	 * @param member
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增用户")
	public Resp save(@RequestBody MemberEntity member) {
		return memberService.saveMember(member);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "查询用户详情")
	public Resp getById(@RequestParam String id) {
		return memberService.getMemberById(id);
	}
	
	/**
	 * 修改
	 * @param member
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改用户信息")
	public Resp update(@RequestBody MemberEntity member) {
		return memberService.updateMember(member);
	}

	/**
	 * 修改密码
	 * @param member
	 * @return
	 */
	@SysLog("修改密码")
	@PostMapping("/updatePassword")
	@ApiOperation(value = "修改密码")
	public Resp updatePassword(@RequestBody MemberEntity member) {
		return  memberService.updateMemberPass(member);
	}

	/**
	 * 冻结解冻登录
	 * @param member
	 * @return
	 */
	@SysLog("冻结解冻登录")
	@PostMapping("/updateStatus")
	@ApiOperation(value = "冻结解冻登录")
	public Resp updateStatus(@RequestBody MemberEntity member) {
		MemberEntity memberEntity=new MemberEntity();
		memberEntity.setId(member.getId());
		memberEntity.setStatus(member.getStatus());
		return  memberService.updateMember(member);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除用户")
	public Resp batchRemove(@RequestBody String[] id) {
		return memberService.batchRemove(id);
	}

	/**
	 * 清空聊天记录
	 * @param uid
	 * @return
	 */
	@SysLog("清空聊天记录")
	@PostMapping("/removeMsgAll")
	@ApiOperation(value = "清空聊天记录")
	public Resp removeMsgAll(@RequestBody String uid) {
		return memberService.removeAllHistoryMsgByUid(uid);
	}
	
}
