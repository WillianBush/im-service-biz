package net.chenlin.dp.modules.biz.server.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.server.dao.ServerMapper;
import net.chenlin.dp.modules.biz.server.entity.ServerEntity;
import net.chenlin.dp.modules.biz.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 服务器基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("serverService")
public class ServerServiceImpl implements ServerService {

	@Autowired
    private ServerMapper serverMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<ServerEntity> listServer(Map<String, Object> params) {
		Query query = new Query(params);
		Page<ServerEntity> page = new Page<>(query);
		serverMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param server
     * @return
     */
	@Override
	public R saveServer(ServerEntity server) {
		int count = serverMapper.save(server);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getServerById(Long id) {
		ServerEntity server = serverMapper.getObjectById(id);
		return CommonUtils.msg(server);
	}

    /**
     * 修改
     * @param server
     * @return
     */
	@Override
	public R updateServer(ServerEntity server) {
		int count = serverMapper.update(server);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = serverMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
