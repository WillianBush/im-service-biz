package net.chenlin.dp.modules.biz.member.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/mr/member")
@Api(tags = "用户管理")
public class MemberController extends AbstractController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
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
	@RequestMapping("/save")
	@ApiOperation(value = "新增用户")
	public Resp save(@RequestBody MemberEntity member) {
		return memberService.saveMember(member);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "查询用户详情")
	public Resp getById(@RequestBody String id) {
		return memberService.getMemberById(id);
	}
	
	/**
	 * 修改
	 * @param member
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
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
	@RequestMapping("/updatePassword")
	@ApiOperation(value = "修改密码")
	public Resp updatePassword(@RequestBody MemberEntity member) {
		//todo 加密
		String password=member.getPassword();
		return  memberService.updateMember(member);
	}

	/**
	 * 冻结解冻登录
	 * @param member
	 * @return
	 */
	@SysLog("冻结解冻登录")
	@RequestMapping("/updateStatus")
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
	@RequestMapping("/remove")
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
	@RequestMapping("/removeMsgAll")
	public Resp removeMsgAll(@RequestBody String uid) {

		return null;
	}
	
}
