package net.chenlin.dp.modules.biz.employee.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface EmployeeMapper extends TkBaseMapper<EmployeeEntity> {
	
}
