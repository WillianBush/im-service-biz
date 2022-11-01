package net.chenlin.dp.modules.sys.service;

import com.alibaba.fastjson2.JSONObject;
import net.chenlin.dp.common.entity.Resp;

import java.util.Map;

public interface DomainsConfigService {

    /***
     * 保存域名配置到OSS、Redis
     * @param param
     */
    Resp save(Map<String, Object> param);

    Resp get();
}
