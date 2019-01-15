package com.zengtengpeng.test.service;

import com.zengtengpeng.test.bean.TestAutoCode;
import java.util.List;
public interface TestAutoCodeService {

	/**
	 * 删除
	 * @param testAutoCode
	 * @return
	 */
	public abstract int deleteByPrimaryKey(TestAutoCode testAutoCode);

	/**
	 * 新增
	 * @param testAutoCode
	 * @return
	 */
	public abstract int insert(TestAutoCode testAutoCode);

	/**
	 * 根据主键查询
	 * @param testAutoCode
	 * @return
	 */
	public abstract TestAutoCode selectByPrimaryKey(TestAutoCode testAutoCode);

	/**
	 * 根据条件查询
	 */
	public abstract List<TestAutoCode> queryTestAutoCodeByCondition(TestAutoCode testAutoCode);

	/**
	 * 分页查询
	 * @param testAutoCode 参数
	 * @return
	 */
	public abstract TestAutoCode selectAll(TestAutoCode testAutoCode);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(TestAutoCode testAutoCode);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<TestAutoCode> export(TestAutoCode testAutoCode);

}