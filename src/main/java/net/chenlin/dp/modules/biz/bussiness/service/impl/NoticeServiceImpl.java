package net.chenlin.dp.modules.biz.bussiness.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.dao.NoticeMapper;
//import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
////import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.NoticeEntity;
import net.chenlin.dp.modules.biz.bussiness.service.NoticeService;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("NoticeService")
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;
    private DomainsMapper domainsMapper;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    public Page<NoticeEntity> listPage(Map<String, Object> params) {
        params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
        Query query = new Query(params);
        Page<NoticeEntity> page = new Page<>(query);
        List<NoticeEntity> resp= noticeMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    /**
     * 新增
     *
     * @param notice
     * @return
     */
    public Resp<NoticeEntity> save(NoticeEntity notice, String domain) {
        notice.setOrgId(domainsMapper.getOrgIdByDomain(domain));
        notice.setOrgId(1L);
        notice.setCreateTime(new Date());
        notice.setModifyTime(new Date());
        int count = noticeMapper.save(notice);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp<NoticeEntity> getById(String id) {
        NoticeEntity NoticeEntity = noticeMapper.getObjectById(id);
        return CommonUtils.msgResp(NoticeEntity);
    }

    @Override
    public Resp<NoticeEntity> getByTitle(String title) {
        NoticeEntity NoticeEntity = noticeMapper.getObjectByTitle(title);
        return CommonUtils.msgResp(NoticeEntity);
    }

    /**
     * 修改
     *
     * @param notice
     * @return
     */
    public Resp<Integer> update(NoticeEntity notice, String domain) {
        notice.setOrgId(domainsMapper.getOrgIdByDomain(domain));
        notice.setOrgId(1L);
        notice.setModifyTime(new Date());
        int count = noticeMapper.update(notice);
        return CommonUtils.msgResp(count);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Resp batchRemove(String[] id) {
        int count = noticeMapper.batchRemove(id);
        return CommonUtils.msgResp(id, count);
    }
}
