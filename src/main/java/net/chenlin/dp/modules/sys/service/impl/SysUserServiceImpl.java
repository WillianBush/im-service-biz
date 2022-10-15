package net.chenlin.dp.modules.sys.service.impl;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.constant.SystemConstant;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.support.properties.JwtProperties;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.common.utils.GoogleGenerator;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.modules.sys.dao.*;
import net.chenlin.dp.modules.sys.entity.SysRoleEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import net.chenlin.dp.modules.sys.entity.SysUserTokenEntity;
import net.chenlin.dp.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 系统用户
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("sysUserService")
@AllArgsConstructor
@Slf4j
public class SysUserServiceImpl implements SysUserService {

	private SysUserMapper sysUserMapper;

	private SysMenuMapper sysMenuMapper;

	private SysRoleMapper sysRoleMapper;

	private SysUserRoleMapper sysUserRoleMapper;

	private SysUserTokenMapper sysUserTokenMapper;

	private JwtProperties jwtProperties;

	private RedisCacheManager redisCacheManager;
	/**
	 * 分页查询用户列表
	 * @param params
	 * @return
	 */
	@Override
	public Page<SysUserEntity> listUser(Map<String, Object> params) {
		if (null != params && !params.isEmpty()){
			List<String> lastLogin= (ArrayList<String>)params.get("last_login_time");
			if (lastLogin != null && lastLogin.size() == 2){
				params.put("lastLoginTimeStart",lastLogin.get(0));
				params.put("lastLoginTimeEnd",lastLogin.get(1));
			}
		}
		Query form = new Query(params);
		Page<SysUserEntity> page = new Page<>(form);
		List<SysUserEntity> userEntities = sysUserMapper.listForPage(page, form);
		List<SysUserEntity> userEntities2 = new ArrayList<>();
		for (SysUserEntity sysUser : userEntities){
			List<SysRoleEntity> sysRoleEntities =listUserRoleList(sysUser.getUserId());
			sysUser.setRoleList(sysRoleEntities);
			userEntities2.add(sysUser);
		}
		page.setRows(userEntities2);
		return page;
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Override
	public SysUserEntity getByUserName(String username) {
		return sysUserMapper.getByUserName(username);
	}

	/**
	 * 用户所有机构id
	 * @param userId
	 * @return
	 */
	@Override
	public List<Long> listAllOrgId(Long userId) {
		return sysUserMapper.listAllOrgId(userId);
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@Override
	public Resp saveUser(SysUserEntity user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserMapper.save(user);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	@Override
	public Resp<SysUserEntity> getUserById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
		return CommonUtils.msgResp(user);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Override
	public Resp updateUser(SysUserEntity user) {
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 查询用户权限集合
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> listUserPerms(Long userId) {
		List<String> perms = sysMenuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for(String perm : perms) {
			if(StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	/**
	 * 查询用户角色集合
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> listUserRoles(Long userId) {
		List<String> roles = sysRoleMapper.listUserRoles(userId);
		Set<String> rolesSet = new HashSet<>();
		for(String role : roles) {
			if(StringUtils.isNotBlank(role)) {
				rolesSet.addAll(Arrays.asList(role.trim().split(",")));
			}
		}
		return rolesSet;
	}

	@Override
	public List<SysRoleEntity> listUserRoleList(Long userId) {
		return sysRoleMapper.listUserRoleList(userId);
	}

	/**
	 * 用户修改密码
	 * @param user
	 * @return
	 */
	@Override
	public Resp updatePswdByUser(SysUserEntity user) {
		String username = user.getUsername();
		String pswd = user.getPassword();
		String newPswd = user.getEmail();
		pswd = MD5Utils.encrypt(username, pswd);
		newPswd = MD5Utils.encrypt(username, newPswd);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("pswd", pswd);
		query.put("newPswd", newPswd);
		int count = sysUserMapper.updatePswdByUser(query);
		if(!CommonUtils.isIntThanZero(count)) {
			return Resp.error("原密码错误");
		}
		return CommonUtils.msgResp(count);
	}

	/**
	 * 启用用户
	 * @param id
	 * @return
	 */
	@Override
	public Resp updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	@Override
	public Resp updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 重置用户密码
	 * @param user
	 * @return
	 */
	@Override
	public Resp updatePswd(SysUserEntity user) {
		SysUserEntity currUser = sysUserMapper.getObjectById(user.getUserId());
		user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		int count = sysUserMapper.updatePswd(user);
		return CommonUtils.msgResp(count);
	}

	/**
	 * 保存用户token
	 * @param sysUser
	 * @param token
	 * @return
	 */
	@Override
	public int saveOrUpdateToken(SysUserEntity sysUser, String token) {
		Date now = new Date();
		Date expire = new Date(now.getTime() + jwtProperties.getExpiration() * 1000);
		SysUserTokenEntity sysUserTokenEntity = new SysUserTokenEntity();
		sysUserTokenEntity.setUserId(sysUser.getUserId());
		sysUserTokenEntity.setGmtModified(now);
		sysUserTokenEntity.setGmtExpire(expire);
		sysUserTokenEntity.setToken(token);
		int count = sysUserTokenMapper.update(sysUserTokenEntity);
		if (count == 0) {
			return sysUserTokenMapper.save(sysUserTokenEntity);
		}
		SysUserEntity update = new SysUserEntity();
		update.setUserId(sysUser.getUserId());
		update.setLast_login_time(now);
		update.setLast_login_ip(sysUser.getLast_login_ip());
		sysUser.setLast_login_time(now);
		sysUserMapper.update(update);
		redisCacheManager.set(RedisCacheKeys.LOGIN_REDIS_CACHE +token, sysUser,jwtProperties.getExpiration());
		return count;
	}

	/**
	 * 根据token查询
	 * @param token
	 * @return
	 */
	@Override
	public SysUserTokenEntity getUserTokenByToken(String token) {
		return sysUserTokenMapper.getByToken(token);
	}

	/**
	 * 根据userId查询
	 * @param userId
	 * @return
	 */
	@Override
	public SysUserTokenEntity getUserTokenByUserId(Long userId) {
		return sysUserTokenMapper.getByUserId(userId);
	}

	/**
	 * 根据userId查询：用于token校验
	 * @param userId
	 * @return
	 */
	@Override
	public SysUserEntity getUserByIdForToken(Long userId) {
		return sysUserMapper.getObjectById(userId);
	}

	@Override
	public SysUserEntity login(String username, String password) {
		log.info("login, username:{};password:{}",username,password);
		return sysUserMapper.getByUserNameAndPassword(username,password);
	}

	@Override
	public Resp<String> getGoogleKaptcha(Long userId, String username) {
		SysUserEntity userEntity = sysUserMapper.getByUserName(username);
		if(userEntity.getEnableGoogleKaptcha() == null || userEntity.getEnableGoogleKaptcha().equals(0)){
			String googleSecure = GoogleGenerator.generateSecretKey();
			String url = GoogleGenerator.getQRBarcode(userEntity.getUsername(),googleSecure);
			SysUserEntity user = new SysUserEntity();
			user.setUserId(userId);
			user.setGoogleKaptchaKey(googleSecure);
			int count = sysUserMapper.update(user);
			if(count > 0) {
				log.info("生成谷歌验证成功,username:{},sercure:{}",username,googleSecure);
				return CommonUtils.msgResp(url);
			}
		}else if(userEntity.getEnableGoogleKaptcha() != null && userEntity.getEnableGoogleKaptcha().equals(1)){
			String url = GoogleGenerator.getQRBarcode(userEntity.getUsername(),userEntity.getGoogleKaptchaKey());
			return CommonUtils.msgResp(url);
		}
		return Resp.error("生成二维码失败");
	}

	@Override
	@Transactional
	public Resp updateGoogleKaptcha(Long userId,String username , String kaptcha) {
		SysUserEntity userEntity = sysUserMapper.getByUserName(username);
		String googleSecure = userEntity.getGoogleKaptchaKey();
		if( GoogleGenerator.check_code(googleSecure,Long.parseLong(kaptcha),System.currentTimeMillis())){
			SysUserEntity user = new SysUserEntity();
			user.setUserId(userId);
			user.setEnableGoogleKaptcha(1);
			int count = sysUserMapper.update(user);
			return Resp.ok();
		}
		return Resp.error("绑定谷歌验证失败");
	}

	@Override
	public boolean checkGoogleKaptcha(String username, String kaptcha) {
		SysUserEntity userEntity = sysUserMapper.getByUserName(username);
		String googleSecure = userEntity.getGoogleKaptchaKey();
		return GoogleGenerator.check_code(googleSecure,Long.parseLong(kaptcha),System.currentTimeMillis());
	}
}
