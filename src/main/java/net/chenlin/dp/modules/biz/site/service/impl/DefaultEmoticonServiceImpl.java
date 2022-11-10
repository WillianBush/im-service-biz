package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.DefaultEmoticonEntity;
import net.chenlin.dp.modules.biz.site.dao.DefaultEmoticonMapper;
import net.chenlin.dp.modules.biz.site.service.DefaultEmoticonService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("defaultEmoticonService")
@AllArgsConstructor
public class DefaultEmoticonServiceImpl implements DefaultEmoticonService {

    private DefaultEmoticonMapper defaultEmoticonMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<DefaultEmoticonEntity> listDefaultEmoticon(Map<String, Object> params) {
		Query query = new Query(params);
		Page<DefaultEmoticonEntity> page = new Page<>(query);
		List<DefaultEmoticonEntity> resp= defaultEmoticonMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param defaultEmoticon
     * @return
     */
	@Override
	public Resp<Integer> saveDefaultEmoticon(DefaultEmoticonEntity defaultEmoticon) {
		int count = defaultEmoticonMapper.save(defaultEmoticon);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<DefaultEmoticonEntity> getDefaultEmoticonById(String id) {
		DefaultEmoticonEntity defaultEmoticon = defaultEmoticonMapper.getObjectById(id);
		return CommonUtils.msgResp(defaultEmoticon);
	}

    /**
     * 修改
     * @param defaultEmoticon
     * @return
     */
	@Override
	public Resp<Integer> updateDefaultEmoticon(DefaultEmoticonEntity defaultEmoticon) {
		int count = defaultEmoticonMapper.update(defaultEmoticon);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = defaultEmoticonMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
