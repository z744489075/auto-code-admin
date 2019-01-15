package com.zengtengpeng.test.service;

import com.zengtengpeng.test.bean.TestAutoCode2;
import java.util.List;
public interface TestAutoCode2Service {

	/**
	 * 删除
	 * @param testAutoCode2
	 * @return
	 */
	public abstract int deleteByPrimaryKey(TestAutoCode2 testAutoCode2);

	/**
	 * 新增
	 * @param testAutoCode2
	 * @return
	 */
	public abstract int insert(TestAutoCode2 testAutoCode2);

	/**
	 * 根据主键查询
	 * @param testAutoCode2
	 * @return
	 */
	public abstract TestAutoCode2 selectByPrimaryKey(TestAutoCode2 testAutoCode2);

	/**
	 * 根据条件查询
	 */
	public abstract List<TestAutoCode2> queryTestAutoCode2ByCondition(TestAutoCode2 testAutoCode2);

	/**
	 * 分页查询
	 * @param testAutoCode2 参数
	 * @return
	 */
	public abstract TestAutoCode2 selectAll(TestAutoCode2 testAutoCode2);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(TestAutoCode2 testAutoCode2);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<TestAutoCode2> export(TestAutoCode2 testAutoCode2);

}