package net.chenlin.dp.modules.api.entity;

public class Constants {
    public final static String DEFAULT_DOMAIN = "www.baidu.com/";

    public final static String DEFAULT_DOMAIN1 = "cc3.syxicecream.com";
    public final  static String DOWNLOAD_IOS_PATH ="qwefff";

    public final  static String DOWNLOAD_ANDROID_PATH ="dif1234";

    public final  static String DOWNLOAD_KEYS_LIST= "download:keys:list";

    public final  static String DOWNLOAD_PREFIX= "download:";

    public final  static String IOS="iOS";

    public final  static String ANDROID = "android";

    public static String downloadRedisCacheAndroid(String appBaseName, Long resignId){
        return DOWNLOAD_PREFIX+ANDROID+":"+appBaseName+":"+resignId;
    }

    public static String downloadRedisCacheIos(String appBaseName, Long resignId){
        return DOWNLOAD_PREFIX+IOS+":"+appBaseName+":"+resignId;
    }
}
