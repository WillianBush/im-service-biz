package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.DefaultFriendEntity;
import net.chenlin.dp.modules.biz.site.dao.DefaultFriendMapper;
import net.chenlin.dp.modules.biz.site.service.DefaultFriendService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("defaultFriendService")
@AllArgsConstructor
public class DefaultFriendServiceImpl implements DefaultFriendService {

    private DefaultFriendMapper defaultFriendMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<DefaultFriendEntity> listDefaultFriend(Map<String, Object> params) {
		Query query = new Query(params);
		Page<DefaultFriendEntity> page = new Page<>(query);
		List<DefaultFriendEntity> resp= defaultFriendMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param defaultFriend
     * @return
     */
	@Override
	public Resp<DefaultFriendEntity> saveDefaultFriend(DefaultFriendEntity defaultFriend) {
		int count = defaultFriendMapper.save(defaultFriend);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<DefaultFriendEntity> getDefaultFriendById(String id) {
		DefaultFriendEntity defaultFriend = defaultFriendMapper.getObjectById(id);
		return CommonUtils.msgResp(defaultFriend);
	}

    /**
     * 修改
     * @param defaultFriend
     * @return
     */
	@Override
	public Resp<Integer> updateDefaultFriend(DefaultFriendEntity defaultFriend) {
		int count = defaultFriendMapper.update(defaultFriend);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = defaultFriendMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
