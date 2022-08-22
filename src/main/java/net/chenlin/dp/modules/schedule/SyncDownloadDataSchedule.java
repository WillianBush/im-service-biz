package net.chenlin.dp.modules.schedule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.modules.api.entity.Constants;
import net.chenlin.dp.modules.biz.appResigned.dao.AppResignedMapper;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component("SyncDownloadDataSchedule")
@AllArgsConstructor
public class SyncDownloadDataSchedule {

    RedisCacheManager redisCacheManager;
    AppResignedMapper appResignedMapper;

    public void sync() {
        redisCacheManager.easyLock("SyncDownloadDataSchedule",()->{
            try {
                log.info("执行定时任务同步下载量");
                Set<Object> downloadRedisKeys= redisCacheManager.sGet(Constants.DOWNLOAD_KEYS_LIST);
                if (downloadRedisKeys.isEmpty()){
                    return;
                }
                for (Object obj : downloadRedisKeys){
                    String redisKey = (String) obj;
                    Long resignedId = Long.valueOf(redisKey.substring(redisKey.lastIndexOf(":")+1));
                    Object downloadTimeObj = redisCacheManager.get(redisKey);
                    if (downloadTimeObj == null ) {
                        continue;
                    }
                    Integer downloadTime = Integer.valueOf(downloadTimeObj.toString());
                    AppResignedEntity appResigned = new AppResignedEntity();
                    appResigned.setId(resignedId);
                    appResigned.setIosDownloadTimes(0);
                    appResigned.setAndroidDownloadTimes(0);
                    if (redisKey.startsWith(Constants.DOWNLOAD_PREFIX+Constants.IOS)) {
                        appResigned.setIosDownloadTimes(downloadTime);
                    }
                    if (redisKey.startsWith(Constants.DOWNLOAD_PREFIX+Constants.ANDROID)) {
                        appResigned.setAndroidDownloadTimes(downloadTime);
                    }
                    appResignedMapper.increaseDownloadTimes(appResigned);
                    redisCacheManager.decr(redisKey,downloadTime);
                }
            }catch (Exception e) {
                log.error("执行定时任务同步下载量失败",e);
            }

        });

    }
}
