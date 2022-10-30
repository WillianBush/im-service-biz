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


    public static String appLastVersion(String os,Integer siteId,String appId) {
        return APP_LAST_VERSION +":" +siteId +":"+ os +":"+appId;
    }
}
