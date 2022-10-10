package net.chenlin.dp.modules.biz.yy.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.yy.entity.YySendGroupMsgEntity;
import net.chenlin.dp.modules.biz.yy.dao.YySendGroupMsgMapper;
import net.chenlin.dp.modules.biz.yy.service.YySendGroupMsgService;


/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yySendGroupMsgService")
@AllArgsConstructor
public class YySendGroupMsgServiceImpl implements YySendGroupMsgService {

    private YySendGroupMsgMapper yySendGroupMsgMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YySendGroupMsgEntity> listYySendGroupMsg(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YySendGroupMsgEntity> page = new Page<>(query);
		List<YySendGroupMsgEntity> resp= yySendGroupMsgMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param yySendGroupMsg
     * @return
     */
	@Override
	public Resp<YySendGroupMsgEntity> saveYySendGroupMsg(YySendGroupMsgEntity yySendGroupMsg) {
		int count = yySendGroupMsgMapper.save(yySendGroupMsg);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YySendGroupMsgEntity> getYySendGroupMsgById(Long id) {
		YySendGroupMsgEntity yySendGroupMsg = yySendGroupMsgMapper.getObjectById(id);
		return CommonUtils.msgResp(yySendGroupMsg);
	}

    /**
     * 修改
     * @param yySendGroupMsg
     * @return
     */
	@Override
	public Resp<Integer> updateYySendGroupMsg(YySendGroupMsgEntity yySendGroupMsg) {
		int count = yySendGroupMsgMapper.update(yySendGroupMsg);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yySendGroupMsgMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
