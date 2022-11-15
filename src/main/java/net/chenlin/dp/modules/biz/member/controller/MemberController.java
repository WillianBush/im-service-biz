package net.chenlin.dp.modules.biz.member.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
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

	private EmployeeService employeeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "用户列表")
	public Page<MemberEntity> list(@RequestBody Map<String, Object> params) {
		params.put("domain",getServerName());
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
	public Resp save(@RequestBody MemberEntity member, @RequestBody Integer is_employee) {
		if (StringUtils.isEmpty(member.getPassword())) {
			return Resp.error(500, "密码不能为空");
		}
		if (StringUtils.isEmpty(member.getNickname()) || member.getNickname().contains(" ")) {
			return Resp.error(500, "昵称不呢为空且不能含有空格");
		}
		Resp resp = memberService.saveMember(member,getServerName());
		return resp;
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



	/**
	 * 新增
	 * @param member
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/batchSave")
	@ApiOperation(value = "批量新增用户")
	public Resp batchSave(@RequestBody List<MemberEntity> member) {
		return memberService.batchSaveMember(member,getServerName());
	}

	/**
	 * 根据mid查询用户信息-提供给添加employee使用
	 * @param mid
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/getByMid")
	@ApiOperation(value = "批量新增用户")
	public Resp<MemberEntity> getByMid(@RequestParam(value = "mid") String mid) {
		return memberService.getMemberByMid(mid);
	}
}
