package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户dao
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	SysUserEntity getByUserName(String username);


	SysUserEntity getByUserNameAndPassword(@Param("username") String username,@Param("password")  String password);
	/**
	 * 查询用户所有菜单id
	 * @param userId
	 * @return
	 */
	List<Long> listAllMenuId(Long userId);

	/**
	 * 查询用户所有机构id
	 * @param userId
	 * @return
	 */
	List<Long> listAllOrgId(Long userId);

	/**
	 * 用户修改密码
	 * @param query
	 * @return
	 */
	int updatePswdByUser(Query query);

	/**
	 * 更新用户状态
	 * @param query
	 * @return
	 */
	int updateUserStatus(Query query);

	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int updatePswd(SysUserEntity user);
	
}
