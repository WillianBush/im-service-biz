package net.chenlin.dp.common.constant;

public class RedisCacheKeys {

    public final static String LOGIN_REDIS_CACHE ="im:login:";

    private final static String APP_LAST_VERSION = "app:last:version";

    /**在线用户*/
    public static final String ONLINE_MEMBER="online:member";

    /**redis中存放已经生成websocket的user所在服务器地址信息，提供远程调用*/
    public static final String REDIS_WSS_KEY="websocket:userid:address";

    /**
     * 根据房间ID保存 roomBean  Map<String, RoomBean>
     */
    public static final String REDIS_KEY_ROOMB_BEAN_MAP="ROOMB_BEAN_MAP";

    /**功能配置*/
    public static final String REDIS_KEY_FUNCTION_CONFIG="function:config";

    public static final String REDIS_KEY_DOMAINS_CONFIG="config:domains";

    public static final String REDIS_KEY_CREATE_MEMBERID="memberId";

    public static final String REDIS_KEY_CREATE_USERNAME="member";

    public final static String default_telephone = "18888888888";

    public static String appLastVersion(String os,Long siteId,String appId) {
        return APP_LAST_VERSION +":" +siteId +":"+ os +":"+appId;
    }
}
