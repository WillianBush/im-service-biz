package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 * @author wang<fangyuan.co@outlook.com>
 */
public interface SysLogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<SysLogEntity> listLog(Map<String, Object> params);

    /**
     * 批量删除
     * @param id
     * @return
     */
    Resp batchRemove(Long[] id);

    /**
     * 清空日志
     * @return
     */
    Resp<Integer> batchRemoveAll();

}
