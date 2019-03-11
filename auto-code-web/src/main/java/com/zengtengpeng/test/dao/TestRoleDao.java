package com.zengtengpeng.test.dao;

import java.util.List;
import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.test.bean.TestRole;


/**
 *测试角色 dao
 */
@Mapper
public interface TestRoleDao  extends BaseDao<TestRole>{




	/**
	 * 根据用户查询角色
	 */
	public List<TestRole> selectTestRoleByUser(TestRole testRole);
	/**
	 * 根据用户删除角色
	 */
	public Integer deleteTestRoleByUser(TestRole testRole);

}
