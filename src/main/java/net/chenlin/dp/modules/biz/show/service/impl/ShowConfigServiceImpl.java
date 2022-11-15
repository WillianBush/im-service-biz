package net.chenlin.dp.modules.biz.show.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.OssConstant;
import net.chenlin.dp.common.entity.*;
import net.chenlin.dp.common.utils.OssUtil;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.show.entity.ShowConfigEntity;
import net.chenlin.dp.modules.biz.show.dao.ShowConfigMapper;
import net.chenlin.dp.modules.biz.show.service.ShowConfigService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
@Service("showConfigService")
@AllArgsConstructor
public class ShowConfigServiceImpl implements ShowConfigService {

    private ShowConfigMapper showConfigMapper;
	private OssUtil ossUtil;
	private OSSModel ossModel;
	private DomainsMapper domainsMapper;
    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<ShowConfigEntity> listShowConfig(Map<String, Object> params) {
		params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
		Query query = new Query(params);
		Page<ShowConfigEntity> page = new Page<>(query);
		List<ShowConfigEntity> resp= showConfigMapper.listForPage(page, query);
		for (ShowConfigEntity showConfigEntity : resp) {
			if (StringUtils.isEmpty(showConfigEntity.getIcon())){
				showConfigEntity.setIcon("https://"+ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
			}else {
				showConfigEntity.setIcon("https://"+ossModel.getEndpoint() +showConfigEntity.getIcon());
			}

			if (StringUtils.isEmpty(showConfigEntity.getApp_start_img())){
				showConfigEntity.setApp_start_img("https://"+ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
			}else {
				showConfigEntity.setApp_start_img("https://"+ossModel.getEndpoint() +showConfigEntity.getApp_start_img());
			}
			if (StringUtils.isEmpty(showConfigEntity.getLogo())){
				showConfigEntity.setLogo("https://"+ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
			}else {
				showConfigEntity.setLogo("https://"+ossModel.getEndpoint() +showConfigEntity.getLogo());
			}
		}
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param showConfig
     * @return
     */
	@Override
	public Resp<ShowConfigEntity> saveShowConfig(ShowConfigEntity showConfig,String domain) {
		showConfig.setOrgid(domainsMapper.getOrgIdByDomain(domain));
		int count = showConfigMapper.save(showConfig);
		if(count>0){
			upLoadConfig(showConfig);
		}
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<ShowConfigEntity> getShowConfigById(String id) {
		ShowConfigEntity showConfig = showConfigMapper.getObjectById(id);
		return CommonUtils.msgResp(showConfig);
	}

    /**
     * 修改
     * @param showConfig
     * @return
     */
	@Override
	public Resp<Integer> updateShowConfig(ShowConfigEntity showConfig) {
		int count = showConfigMapper.update(showConfig);
		if(count>0){
			upLoadConfig(showConfig);
		}
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = showConfigMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 上传配置
	 * @param showConfigEntity
	 */
	public void upLoadConfig(ShowConfigEntity showConfigEntity){
		try {
			byte[] encryptBytes =  showConfigEntity.toString().getBytes(StandardCharsets.UTF_8);
			InputStream in = new ByteArrayInputStream(encryptBytes);
			String fileName = "show_config" + showConfigEntity.getOrgid() + "proc.txt" ;
//			OSSUploadResp resp =  ossUtil.uploadObjectToOSS(in, OssConstant.OSS_SHOW_FILE_NAME,OssConstant.OSS_CONFIG_PATH, (long) in.available());
			OSSUploadResp resp =  ossUtil.uploadObjectToOSS(in, fileName, OssConstant.OSS_CONFIG_PATH, (long) in.available());
			log.debug("oss上传结束:{}", JSONObject.toJSONString(resp));
		} catch (Exception e) {
			log.error("ShowConfigServiceImpl:{}",e);
		}
	}

}
