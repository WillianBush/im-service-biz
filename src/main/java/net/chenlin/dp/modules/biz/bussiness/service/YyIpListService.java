package net.chenlin.dp.modules.biz.bussiness.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyIpListService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YyIpListEntity> listYyIpList(Map<String, Object> params);

    /**
     * 新增
     * @param yyIpList
     * @return
     */
	Resp<YyIpListEntity> saveYyIpList(YyIpListEntity yyIpList,String domain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyIpListEntity> getYyIpListById(Long id);

    /**
     * 修改
     * @param yyIpList
     * @return
     */
	Resp<Integer> updateYyIpList(YyIpListEntity yyIpList);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);


    /**
     * 删除
     * @param ipAddress
     * @param type
     * @return
     */
    YyIpListEntity getByIP(String ipAddress, Integer type);

}
