package com.zengtengpeng.test.service;

import com.zengtengpeng.test.bean.TestAutoCode1;
import java.util.List;
public interface TestAutoCode1Service {

	/**
	 * 删除
	 * @param testAutoCode1
	 * @return
	 */
	public abstract int deleteByPrimaryKey(TestAutoCode1 testAutoCode1);

	/**
	 * 新增
	 * @param testAutoCode1
	 * @return
	 */
	public abstract int insert(TestAutoCode1 testAutoCode1);

	/**
	 * 根据主键查询
	 * @param testAutoCode1
	 * @return
	 */
	public abstract TestAutoCode1 selectByPrimaryKey(TestAutoCode1 testAutoCode1);

	/**
	 * 根据条件查询
	 */
	public abstract List<TestAutoCode1> queryTestAutoCode1ByCondition(TestAutoCode1 testAutoCode1);

	/**
	 * 分页查询
	 * @param testAutoCode1 参数
	 * @return
	 */
	public abstract TestAutoCode1 selectAll(TestAutoCode1 testAutoCode1);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(TestAutoCode1 testAutoCode1);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<TestAutoCode1> export(TestAutoCode1 testAutoCode1);

}