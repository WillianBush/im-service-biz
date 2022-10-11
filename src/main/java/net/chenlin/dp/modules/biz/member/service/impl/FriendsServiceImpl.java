package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.Map;

import net.chenlin.dp.common.entity.Resp;
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
		page.setRows(friendsMapper.listForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param friends
     * @return
     */
	@Override
	public Resp saveFriends(FriendsEntity friends) {
		int count = friendsMapper.save(friends);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getFriendsById(String id) {
		FriendsEntity friends = friendsMapper.getObjectById(id);
		return CommonUtils.msgResp(friends);
	}

    /**
     * 修改
     * @param friends
     * @return
     */
	@Override
	public Resp updateFriends(FriendsEntity friends) {

		int count = friendsMapper.update(friends);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = friendsMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
