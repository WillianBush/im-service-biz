package net.chenlin.dp.modules.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.OssConstant;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.entity.OSSUploadResp;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.OssUtil;
import net.chenlin.dp.modules.sys.service.DomainsConfigService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service("domainsConfigService")
public class DomainsConfigServiceImpl implements DomainsConfigService {

    private OssUtil ossUtil;
    private RedisCacheManager redisCacheManager;

    @Override
    public Resp save(Map<String, Object> param) {
        try {
            byte[] encryptBytes =  JSON.toJSONString(param).getBytes(StandardCharsets.UTF_8);
            InputStream in = new ByteArrayInputStream(encryptBytes);
            OSSUploadResp resp =  ossUtil.uploadObjectToOSS(in,OssConstant.OSS_DOMAINS_FILE_NAME, OssConstant.OSS_CONFIG_PATH, (long) in.available());
            redisCacheManager.hmset(RedisCacheKeys.REDIS_KEY_DOMAINS_CONFIG,param);
            Resp.ok();
        } catch (Exception e) {
            log.error("===,DomainsConfigServiceImpl.svae:{}",e);
        }
        return Resp.error();
    }

    @Override
    public Resp get() {
        Map<Object, Object> obj=redisCacheManager.hmget(RedisCacheKeys.REDIS_KEY_DOMAINS_CONFIG);
         return Resp.ok(Arrays.asList(obj));
    }

    public static void main(String[] args) {
        String ss="{\"httpDomains\": [\"1\",\"2\"],\"mediasDomain\": [\"3\",\"4\"],\"websocketDomains\": [\"5\",\"6\"]}";
        HashMap<String, Object> h=JSON.parseObject(ss, HashMap.class);
        System.out.println(h);
    }
}
