package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.DefaultRoomEntity;
import net.chenlin.dp.modules.biz.site.dao.DefaultRoomMapper;
import net.chenlin.dp.modules.biz.site.service.DefaultRoomService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("defaultRoomService")
@AllArgsConstructor
public class DefaultRoomServiceImpl implements DefaultRoomService {

    private DefaultRoomMapper defaultRoomMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<DefaultRoomEntity> listDefaultRoom(Map<String, Object> params) {
		Query query = new Query(params);
		Page<DefaultRoomEntity> page = new Page<>(query);
		List<DefaultRoomEntity> resp= defaultRoomMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param defaultRoom
     * @return
     */
	@Override
	public Resp<DefaultRoomEntity> saveDefaultRoom(DefaultRoomEntity defaultRoom) {
		int count = defaultRoomMapper.save(defaultRoom);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<DefaultRoomEntity> getDefaultRoomById(String id) {
		DefaultRoomEntity defaultRoom = defaultRoomMapper.getObjectById(id);
		return CommonUtils.msgResp(defaultRoom);
	}

    /**
     * 修改
     * @param defaultRoom
     * @return
     */
	@Override
	public Resp<Integer> updateDefaultRoom(DefaultRoomEntity defaultRoom) {
		int count = defaultRoomMapper.update(defaultRoom);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = defaultRoomMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
