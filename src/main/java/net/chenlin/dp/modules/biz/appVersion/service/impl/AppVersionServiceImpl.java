package net.chenlin.dp.modules.biz.appVersion.service.impl;

import java.util.Map;
import java.util.List;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.appVersion.entity.AppVersionEntity;
import net.chenlin.dp.modules.biz.appVersion.dao.AppVersionMapper;
import net.chenlin.dp.modules.biz.appVersion.service.AppVersionService;


/**
 * app版本升级
 *
 * @author wang<fangyuan.co @ outlook.com>
 */
@Service("appVersionService")
@AllArgsConstructor
public class AppVersionServiceImpl implements AppVersionService {

    private AppVersionMapper appVersionMapper;

    private RedisCacheManager redisCacheManager;

    private DomainsMapper domainsMapper;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public Page<AppVersionEntity> listAppVersion(Map<String, Object> params) {
        Query query = new Query(params);
        Page<AppVersionEntity> page = new Page<>(query);
        List<AppVersionEntity> resp = appVersionMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    /**
     * 新增
     *
     * @param appVersion
     * @return
     */
    @Override
    public Resp<AppVersionEntity> saveAppVersion(AppVersionEntity appVersion,String domain) {
        appVersion.setOrgId(domainsMapper.getOrgIdByDomain(domain));
        int id = appVersionMapper.save(appVersion);
        if (id != 0) {
            String redisKey = RedisCacheKeys.appLastVersion(appVersion.getDeviceType(), appVersion.getOrgId(), appVersion.getAppId());
            redisCacheManager.set(redisKey, appVersion);
        }
        return CommonUtils.msgResp(appVersion);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Resp<AppVersionEntity> getAppVersionById(Long id) {
        AppVersionEntity appVersion = appVersionMapper.getObjectById(id);
        return CommonUtils.msgResp(appVersion);
    }

    /**
     * 修改
     *
     * @param appVersion
     * @return
     */
    @Override
    public Resp<Integer> updateAppVersion(AppVersionEntity appVersion) {
        int id = appVersionMapper.update(appVersion);
        appVersion = appVersionMapper.getObjectById(id);
//        String redisKey = RedisCacheKeys.appLastVersion(appVersion.getOs(), appVersion.getSite_id(), appVersion.getAppId());
//        redisCacheManager.set(redisKey, appVersion);
        return CommonUtils.msgResp(id);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public Resp<Integer> batchRemove(Long[] ids) {
        int counts = 0;
        for (Long id : ids) {
            AppVersionEntity appVersion = appVersionMapper.getObjectById(id);
            String redisKey = RedisCacheKeys.appLastVersion(appVersion.getDeviceType(), appVersion.getOrgId(), appVersion.getAppId());
            int count = appVersionMapper.remove(id);
            if (count == 1) {
                redisCacheManager.del(redisKey);
                counts++;
            }
        }
        return CommonUtils.msgResp(counts);
    }

    @Override
    public AppVersionEntity selectByUniqueKey(String version, Long siteId, String os, String appId) {
        return appVersionMapper.selectOne(AppVersionEntity.builder()
                .version(version)
                .orgId(siteId)
                .deviceType(os)
                .appId(appId)
                .build());
    }

    /**
     * 根据id查询
     *
     * @param os
     * @param appName
     * @return
     */
    @Override
    public Resp<AppVersionEntity> getAppVersionByOSAndAppName(String os, String appName) {
        AppVersionEntity appVersion = appVersionMapper.getObjectByOSAndAppName(os, appName);
        return CommonUtils.msgResp(appVersion);
    }

}
