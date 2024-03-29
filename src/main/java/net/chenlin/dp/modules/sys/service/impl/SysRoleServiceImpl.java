package net.chenlin.dp.modules.sys.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.sys.dao.SysRoleMapper;
import net.chenlin.dp.modules.sys.dao.SysRoleMenuMapper;
import net.chenlin.dp.modules.sys.dao.SysRoleOrgMapper;
import net.chenlin.dp.modules.sys.dao.SysUserRoleMapper;
import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import net.chenlin.dp.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired
	private SysRoleOrgMapper sysRoleOrgMapper;

	/**
	 * 分页查询角色列表
	 * @param params
	 * @return
	 */
	@Override
	public Page<SysRoleEntity> listRole(Map<String, Object> params) {
		Query query = new Query(params);
		Page<SysRoleEntity> page = new Page<>(query);
		List<SysRoleEntity> resp=sysRoleMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@Override
	public Resp<Integer> saveRole(SysRoleEntity role) {
		int count = sysRoleMapper.save(role);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	@Override
	public Resp<SysRoleEntity> getRoleById(Long id) {
		SysRoleEntity role = sysRoleMapper.getObjectById(id);
		List<Long> menuId = sysRoleMenuMapper.listMenuId(id);
		List<Long> orgId = sysRoleOrgMapper.listOrgId(id);
		role.setMenuIdList(menuId);
		role.setOrgIdList(orgId);
		return CommonUtils.msgResp(role);
	}

	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@Override
	public Resp<Integer> updateRole(SysRoleEntity role) {
		int count = sysRoleMapper.update(role);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = sysRoleMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByRoleId(id);
		sysRoleMenuMapper.batchRemoveByRoleId(id);
		sysRoleOrgMapper.batchRemoveByRoleId(id);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 所有角色集合：用户角色选择数据源
	 * @return
	 */
	@Override
	public List<SysRoleEntity> listRole() {
		return sysRoleMapper.list();
	}

	/**
	 * 操作权限
	 * @param role
	 * @return
	 */
	@Override
	public Resp<Integer> updateRoleOptAuthorization(SysRoleEntity role) {
		Long roleId = role.getRoleId();
		int count = sysRoleMenuMapper.remove(roleId);
		Query query = new Query();
		query.put("roleId", roleId);
		List<Long> menuId = role.getMenuIdList();
		if(menuId.size() > 0) {
			query.put("menuIdList", role.getMenuIdList());
			count = sysRoleMenuMapper.save(query);
		}
		return CommonUtils.msgResp(count);
	}

	/**
	 * 数据权限
	 * @param role
	 * @return
	 */
	@Override
	public  Resp<Integer> updateRoleDataAuthorization(SysRoleEntity role) {
		Long roleId = role.getRoleId();
		int count = sysRoleOrgMapper.remove(roleId);
		Query query = new Query();
		query.put("roleId", roleId);
		List<Long> orgId = role.getOrgIdList();
		if(orgId.size() > 0) {
			query.put("orgIdList", role.getOrgIdList());
			count = sysRoleOrgMapper.save(query);
		}
		return CommonUtils.msgResp(count);
	}
	
}
