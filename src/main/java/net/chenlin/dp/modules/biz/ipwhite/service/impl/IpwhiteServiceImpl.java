package net.chenlin.dp.modules.biz.ipwhite.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.ipwhite.entity.IpwhiteEntity;
import net.chenlin.dp.modules.biz.ipwhite.dao.IpwhiteMapper;
import net.chenlin.dp.modules.biz.ipwhite.service.IpwhiteService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("ipwhiteService")
@AllArgsConstructor
public class IpwhiteServiceImpl implements IpwhiteService {

    private IpwhiteMapper ipwhiteMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<IpwhiteEntity> listIpwhite(Map<String, Object> params) {
		Query query = new Query(params);
		Page<IpwhiteEntity> page = new Page<>(query);
		ipwhiteMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param ipwhite
     * @return
     */
	@Override
	public R saveIpwhite(IpwhiteEntity ipwhite) {
		int count = ipwhiteMapper.save(ipwhite);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getIpwhiteById(Long id) {
		IpwhiteEntity ipwhite = ipwhiteMapper.getObjectById(id);
		return CommonUtils.msg(ipwhite);
	}

    /**
     * 修改
     * @param ipwhite
     * @return
     */
	@Override
	public R updateIpwhite(IpwhiteEntity ipwhite) {
		int count = ipwhiteMapper.update(ipwhite);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = ipwhiteMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
