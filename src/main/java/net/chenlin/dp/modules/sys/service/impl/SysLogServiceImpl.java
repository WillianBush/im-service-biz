package net.chenlin.dp.modules.sys.service.impl;

import io.swagger.models.auth.In;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.sys.dao.SysLogMapper;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;
import net.chenlin.dp.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public Page<SysLogEntity> listLog(Map<String, Object> params) {
        if (null != params && !params.isEmpty()){
            List<String> lastLogin= (ArrayList<String>)params.get("gmtCreate");
            if (lastLogin != null && lastLogin.size() == 2){
                params.put("gmtCreateStart",lastLogin.get(0));
                params.put("gmtCreateEnd",lastLogin.get(1));
            }
        }
        Query query = new Query(params);
        Page<SysLogEntity> page = new Page<>(query);
        List<SysLogEntity> resp= sysLogMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    /**
     * 批量删除
     * @param id
     * @return
     */
    @Override
    public Resp batchRemove(Long[] id) {
        int count = sysLogMapper.batchRemove(id);
        return CommonUtils.msgResp(id, count);
    }

    /**
     * 清空
     * @return
     */
    @Override
    public Resp<Integer> batchRemoveAll() {
        int count = sysLogMapper.batchRemoveAll();
        return CommonUtils.msgResp(count);
    }

}
