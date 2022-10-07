package net.chenlin.dp.modules.biz.tongji.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.tongji.entity.TongjiEntity;
import net.chenlin.dp.modules.biz.tongji.dao.TongjiMapper;
import net.chenlin.dp.modules.biz.tongji.service.TongjiService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("tongjiService")
@AllArgsConstructor
public class TongjiServiceImpl implements TongjiService {

    private TongjiMapper tongjiMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<TongjiEntity> listTongji(Map<String, Object> params) {
		Query query = new Query(params);
		Page<TongjiEntity> page = new Page<>(query);
		tongjiMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param tongji
     * @return
     */
	@Override
	public R saveTongji(TongjiEntity tongji) {
		int count = tongjiMapper.save(tongji);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getTongjiById(Long id) {
		TongjiEntity tongji = tongjiMapper.getObjectById(id);
		return CommonUtils.msg(tongji);
	}

    /**
     * 修改
     * @param tongji
     * @return
     */
	@Override
	public R updateTongji(TongjiEntity tongji) {
		int count = tongjiMapper.update(tongji);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = tongjiMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
