package net.chenlin.dp.modules.biz.employee.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.employee.dao.EmployeeMapper;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import net.chenlin.dp.modules.biz.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("employeeService")
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<EmployeeEntity> listEmployee(Map<String, Object> params) {
		Query query = new Query(params);
		Page<EmployeeEntity> page = new Page<>(query);
		page.setRows(employeeMapper.listForPage(page, query));

//		EmployeeEntity employee = employeeMapper.selectOne(EmployeeEntity.builder()
//						.name("admin")
//				.build());
//		Example example = Example.builder(EmployeeEntity.class)
//				.select("id","name")
//				.where(Sqls.custom().andEqualTo("name", "admin")
//						.andLike("name", "%admin%"))
//				.orderByDesc("count","name")
//				.build();
//		List<EmployeeEntity> employeeEntities = employeeMapper.selectByExample(example);
		return page;
	}

    /**
     * 新增
     * @param employee
     * @return
     */
	@Override
	public R saveEmployee(EmployeeEntity employee) {
		int count = employeeMapper.save(employee);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getEmployeeById(Long id) {
		EmployeeEntity employee = employeeMapper.getObjectById(id);
		return CommonUtils.msg(employee);
	}

    /**
     * 修改
     * @param employee
     * @return
     */
	@Override
	public R updateEmployee(EmployeeEntity employee) {
		int count = employeeMapper.update(employee);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = employeeMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
