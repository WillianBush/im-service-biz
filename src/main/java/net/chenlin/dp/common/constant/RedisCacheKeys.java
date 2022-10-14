package net.chenlin.dp.common.constant;

public class RedisCacheKeys {

    public final static String LOGIN_REDIS_CACHE ="im:login:";

    private final static String APP_LAST_VERSION = "app:last:version";

    public static final String ONLINE_MEMBER="websocket:userid:address";


    public static String appLastVersion(String os,Integer siteId,String appId) {
        return APP_LAST_VERSION +":" +siteId +":"+ os +":"+appId;
    }
}
