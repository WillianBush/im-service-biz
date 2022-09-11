package net.chenlin.dp.modules.biz.domain.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface DomainService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<DomainEntity> listDomain(Map<String, Object> params);

    /**
     * 新增
     * @param domain
     * @return
     */
	R saveDomain(DomainEntity domain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getDomainById(Long id);

    /**
     * 修改
     * @param domain
     * @return
     */
	R updateDomain(DomainEntity domain);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);

    List<DomainEntity> getDomainEnable();
	
}
