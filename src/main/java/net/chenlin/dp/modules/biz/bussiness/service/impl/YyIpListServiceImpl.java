package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.YyIpListMapper;
import net.chenlin.dp.modules.biz.bussiness.service.YyIpListService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyIpListService")
@AllArgsConstructor
public class YyIpListServiceImpl implements YyIpListService {

    private YyIpListMapper yyIpListMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YyIpListEntity> listYyIpList(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YyIpListEntity> page = new Page<>(query);
		List<YyIpListEntity> resp=yyIpListMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param yyIpList
     * @return
     */
	@Override
	public Resp<YyIpListEntity> saveYyIpList(YyIpListEntity yyIpList) {
		int count = yyIpListMapper.save(yyIpList);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyIpListEntity> getYyIpListById(Long id) {
		YyIpListEntity yyIpList = yyIpListMapper.getObjectById(id);
		return CommonUtils.msgResp(yyIpList);
	}

    /**
     * 修改
     * @param yyIpList
     * @return
     */
	@Override
	public Resp<Integer> updateYyIpList(YyIpListEntity yyIpList) {
		int count = yyIpListMapper.update(yyIpList);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyIpListMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}