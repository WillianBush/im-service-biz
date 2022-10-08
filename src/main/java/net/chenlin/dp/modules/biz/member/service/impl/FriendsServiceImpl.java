package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.member.dao.FriendsMapper;
import net.chenlin.dp.modules.biz.member.service.FriendsService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("friendsService")
public class FriendsServiceImpl implements FriendsService {

	@Autowired
    private FriendsMapper friendsMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<FriendsEntity> listFriends(Map<String, Object> params) {
		Query query = new Query(params);
		Page<FriendsEntity> page = new Page<>(query);
		friendsMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param friends
     * @return
     */
	@Override
	public R saveFriends(FriendsEntity friends) {
		int count = friendsMapper.save(friends);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getFriendsById(Long id) {
		FriendsEntity friends = friendsMapper.getObjectById(id);
		return CommonUtils.msg(friends);
	}

    /**
     * 修改
     * @param friends
     * @return
     */
	@Override
	public R updateFriends(FriendsEntity friends) {
		int count = friendsMapper.update(friends);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = friendsMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
