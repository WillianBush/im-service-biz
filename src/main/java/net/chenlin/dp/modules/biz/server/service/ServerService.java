package net.chenlin.dp.modules.biz.server.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.server.entity.ServerEntity;

import java.util.Map;

/**
 * 服务器基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
public interface ServerService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<ServerEntity> listServer(Map<String, Object> params);

    /**
     * 新增
     * @param server
     * @return
     */
    R saveServer(ServerEntity server);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    R getServerById(Long id);

    /**
     * 修改
     * @param server
     * @return
     */
    R updateServer(ServerEntity server);

    /**
     * 删除
     * @param id
     * @return
     */
    R batchRemove(Long[] id);

}
