package net.chenlin.dp.modules.biz.domain.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.domain.dao.DomainMapper;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainOriginEnum;
import net.chenlin.dp.modules.biz.domain.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("domainService")
public class DomainServiceImpl implements DomainService {

	@Autowired
    private DomainMapper domainMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<DomainEntity> listDomain(Map<String, Object> params) {
		Query query = new Query(params);
		Page<DomainEntity> page = new Page<>(query);
		domainMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param domain
     * @return
     */
	@Override
	public R saveDomain(DomainEntity domain) {
		if (domain.getDomainOrigin() == null ){
			domain.setDomainOrigin(DomainOriginEnum.OfficialDomain.getCode());
		}
		int count = domainMapper.save(domain);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getDomainById(Long id) {
		DomainEntity domain = domainMapper.getObjectById(id);
		return CommonUtils.msg(domain);
	}

    /**
     * 修改
     * @param domain
     * @return
     */
	@Override
	public R updateDomain(DomainEntity domain) {
		int count = domainMapper.update(domain);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = domainMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public List<DomainEntity> getDomainEnable() {
		DomainEntity entity = DomainEntity.builder()
				.domainEnable(1)
				.isBlocked(1)
				.build();
		return domainMapper.select(entity);
	}

}
