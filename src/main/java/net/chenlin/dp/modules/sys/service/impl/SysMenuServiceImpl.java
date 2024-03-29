package net.chenlin.dp.modules.sys.service.impl;

import net.chenlin.dp.common.constant.MsgConstant;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.sys.dao.SysMenuMapper;
import net.chenlin.dp.modules.sys.dao.SysRoleMenuMapper;
import net.chenlin.dp.modules.sys.dao.SysUserMapper;
import net.chenlin.dp.modules.sys.entity.SysMenuEntity;
import net.chenlin.dp.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	/**
	 * 查询用户权限菜单
	 * @param userId
	 * @return
	 */
	@Override
	public Resp<SysMenuEntity> listUserMenu(Long userId) {
		List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
		return Resp.ok(getAllMenuList(menuIdList));
	}

	@Override
	public List<SysMenuEntity> getAllMenuList(Long userId) {
		List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	/**
	 * 获取所有菜单列表
	 * @param menuIdList
	 * @return
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = listParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 * @param menuList
	 * @param menuIdList
	 * @return
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

		for(SysMenuEntity entity : menuList){
			if(entity.getType() == SystemConstant.MenuType.CATALOG.getValue()){
				//目录
				entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}

	/**
	 * 父菜单的所有子菜单
	 * @param parentId
	 * @param menuIdList
	 * @return
	 */
	public List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = sysMenuMapper.listParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}

		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	/**
	 * 菜单列表：树形表格
	 * @param params
	 * @return
	 */
	@Override
	public List<SysMenuEntity> listMenu(Map<String, Object> params) {
		Query query = new Query(params);
		List<SysMenuEntity> menuList = sysMenuMapper.list(query);
		return menuList;
	}

	/**
	 * 查询非按钮菜单
	 * @return
	 */
	@Override
	public Resp<SysMenuEntity> listNotButton() {
		List<SysMenuEntity> menuList = sysMenuMapper.listNotButton();
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return CommonUtils.msgRespNotCheckNull(menuList);
	}

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@Override
	public Resp<Integer> saveMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.save(menu);
		// 刷新权限链
		return CommonUtils.msgResp(count);
	}

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public Resp<SysMenuEntity> getMenuById(Long id) {
		SysMenuEntity menu = sysMenuMapper.getObjectById(id);
		return CommonUtils.msgResp(menu);
	}

	/**
	 * 更新
	 * @param menu
	 * @return
	 */
	@Override
	public Resp<Integer> updateMenu(SysMenuEntity menu) {
		int count = sysMenuMapper.update(menu);
		// 刷新权限链

		return CommonUtils.msgResp(count);
	}

	/**
	 * 批量删除
	 * @param id
	 * @return
	 */
	@Override
	public Resp batchRemove(Long[] id) {
		boolean children = this.hasChildren(id);
		if(children) {
			return Resp.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysMenuMapper.batchRemove(id);
		sysRoleMenuMapper.batchRemoveByMenuId(id);
		// 刷新权限链

		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 菜单是否有子节点
	 * @param id
	 * @return
	 */
	public boolean hasChildren(Long[] id) {
		for(Long parentId : id) {
			int count = sysMenuMapper.countMenuChildren(parentId);
			if(CommonUtils.isIntThanZero(count)) {
				return true;
			}
		}
		return false;
	}


}
