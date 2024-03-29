package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.modules.sys.entity.SysRoleOrgEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与机构的关系
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface SysRoleOrgMapper extends BaseMapper<SysRoleOrgEntity> {

	/**
	 * 查询角色所有机构id集合
	 * @param roleId
	 * @return
	 */
	List<Long> listOrgId(Long roleId);

	/**
	 * 根据机构id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByOrgId(Long[] id);

	/**
	 * 根据角色id删除
	 * @param id
	 * @return
	 */
	int batchRemoveByRoleId(Long[] id);
	
}
