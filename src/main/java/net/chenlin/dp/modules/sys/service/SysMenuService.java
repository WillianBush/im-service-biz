package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 * @author wang<fangyuan.co@outlook.com>
 */
public interface SysMenuService {

	/**
	 * 查询用户菜单
	 * @param userId
	 * @return
	 */
	Resp<SysMenuEntity> listUserMenu(Long userId);

	List<SysMenuEntity> getAllMenuList(Long userId);

	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 */
	List<SysMenuEntity> listMenu(Map<String, Object> params);

	/**
	 * 查询目录和菜单
	 * @return
	 */
	Resp<SysMenuEntity> listNotButton();

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	Resp<Integer> saveMenu(SysMenuEntity menu);

	/**
	 * 根据id查询菜单
	 * @param id
	 * @return
	 */
	Resp<SysMenuEntity> getMenuById(Long id);

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	Resp<Integer> updateMenu(SysMenuEntity menu);

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	Resp batchRemove(Long[] id);

}
