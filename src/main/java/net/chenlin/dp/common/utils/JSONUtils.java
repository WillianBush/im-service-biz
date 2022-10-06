package net.chenlin.dp.common.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * json工具类(jackjson)
 * @author wang<fangyuan.co@outlook.com>
 */
public class JSONUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtils() {

    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * object转化json
     * @param obj
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object obj) {
        try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    /**
     * json转对象
     * @param jsonStr
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String jsonStr, Class<T> clazz) {
        try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    /**
     * json转map
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static <T> Map<String, Object> jsonToMap(String jsonStr)
            throws Exception {
        return objectMapper.readValue(jsonStr, Map.class);
    }


    /**
     * map转化对象
     */
    @SuppressWarnings("rawtypes")
	public static <T> T mapToBean(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static Map<String, Object> mapNoEmpty(Map<String, Object> map) {
        Map<String, Object> mapNew = new HashMap<>();
        Set<String> set = map.keySet();
        if(set != null && !set.isEmpty()) {
            for(String key : set) {
                if(map.get(key) != null && ! map.get(key).equals("")  ) {
                    mapNew.put(key,map.get(key));
                }
            }
        }
        return mapNew;
    }
	
}
