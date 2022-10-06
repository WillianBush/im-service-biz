package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统角色
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

	/**
	 * 查询用户角色集合
	 * @param userId
	 * @return
	 */
	List<String> listUserRoles(Long userId);

	List<SysRoleEntity> listUserRoleList(Long userId);
	
}
