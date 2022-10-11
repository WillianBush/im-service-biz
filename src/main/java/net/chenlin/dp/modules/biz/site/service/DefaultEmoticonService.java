package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultEmoticonEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface DefaultEmoticonService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<DefaultEmoticonEntity> listDefaultEmoticon(Map<String, Object> params);

    /**
     * 新增
     * @param defaultEmoticon
     * @return
     */
	Resp<DefaultEmoticonEntity> saveDefaultEmoticon(DefaultEmoticonEntity defaultEmoticon);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<DefaultEmoticonEntity> getDefaultEmoticonById(String id);

    /**
     * 修改
     * @param defaultEmoticon
     * @return
     */
	Resp<Integer> updateDefaultEmoticon(DefaultEmoticonEntity defaultEmoticon);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);
	
}
