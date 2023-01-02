package net.chenlin.dp.modules.biz.site.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.dao.HomepageUserMapper;
import net.chenlin.dp.modules.biz.site.entity.HomepageUserEntity;
import net.chenlin.dp.modules.biz.site.service.HomepageUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class HomepageUserServiceImpl implements HomepageUserService {

    private HomepageUserMapper homepageUserMapper;


    @Override
    public Page<HomepageUserEntity> listHomepage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<HomepageUserEntity> page = new Page<>(query);
        List<HomepageUserEntity> resp= homepageUserMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    @Override
    public Resp<HomepageUserEntity> saveHomepageUser(HomepageUserEntity homepageUserEntity) {
        int count = homepageUserMapper.save(homepageUserEntity);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp<HomepageUserEntity> getHomepageById(String id) {
        HomepageUserEntity homepageUserEntity = homepageUserMapper.getObjectById(id);
        return CommonUtils.msgResp(homepageUserEntity);
    }

    @Override
    public Resp<Integer> updateHomepage(HomepageUserEntity homepageUserEntity) {
        int count = homepageUserMapper.update(homepageUserEntity);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp batchRemove(String[] id) {
        int count = homepageUserMapper.batchRemove(id);
        return CommonUtils.msgResp(id, count);
    }
}
