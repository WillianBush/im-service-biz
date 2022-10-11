package net.chenlin.dp.modules.biz.member.controller;

import java.util.Map;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;
import net.chenlin.dp.modules.biz.member.service.MemberloginlogService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/rm/memberloginlog")
@AllArgsConstructor
public class MemberloginlogController extends AbstractController {
	
	private MemberloginlogService memberloginlogService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ApiOperation(value = "用户列表")
	public Page<MemberloginlogEntity> list(@RequestBody Map<String, Object> params) {
		return memberloginlogService.listMemberloginlog(params);
	}
		
	/**
	 * 新增
	 * @param memberloginlog
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	@ApiOperation(value = "新增用户")
	public Resp save(@RequestBody MemberloginlogEntity memberloginlog) {
		return memberloginlogService.saveMemberloginlog(memberloginlog);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp getById(@RequestBody String id) {
		return memberloginlogService.getMemberloginlogById(id);
	}
	
	/**
	 * 修改
	 * @param memberloginlog
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	@ApiOperation(value = "修改用户信息")
	public Resp update(@RequestBody MemberloginlogEntity memberloginlog) {
		return memberloginlogService.updateMemberloginlog(memberloginlog);
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
		return memberloginlogService.batchRemove(id);
	}
	
}
