package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 * @author wang<fangyuan.co@outlook.com>
 */
public interface SysAreaService {

	/**
	 * 根据父级id查询区域：ztree异步数据源
	 * @param areaCode
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(String areaCode);

	/**
	 * 根据父级id查询区域：表格数据源
	 * @param params
	 * @return
	 */
	Resp listAreaByParentCode(Map<String, Object> params);

	/**
	 * 新增区域
	 * @param area
	 * @return
	 */
	Resp saveArea(SysAreaEntity area);

	/**
	 * 根据id查询区域
	 * @param areaId
	 * @return
	 */
	Resp getAreaById(Long areaId);

	/**
	 * 修改区域
	 * @param area
	 * @return
	 */
	Resp updateArea(SysAreaEntity area);

	/**
	 * 批量删除区域
	 * @param id
	 * @return
	 */
	Resp batchRemoveArea(Long[] id);
	
}
