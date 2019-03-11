package com.zengtengpeng.test.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.test.bean.TestRole;
import com.zengtengpeng.test.dao.TestRoleDao;


/**
 *测试角色 service
 */
public interface TestRoleService extends BaseService<TestRole,TestRoleDao>{




	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public TestRole selectUserAndTestRole(TestRole testRole);
	/**
	 * 级联条件查询 用户--角色
	 */
	public List<TestRole> selectUserAndTestRoleByCondition(TestRole testRole);
	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	public Integer deleteUserAndTestRole(TestRole testRole);
	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	public TestRole selectTestRoleByUser(TestRole testRole);

}
