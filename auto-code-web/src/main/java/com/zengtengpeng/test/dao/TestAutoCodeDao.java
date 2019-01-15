package com.zengtengpeng.test.dao;

import com.zengtengpeng.test.bean.TestAutoCode;
import java.util.List;

public interface TestAutoCodeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestAutoCode record);

    TestAutoCode selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<TestAutoCode> queryTestAutoCodeByCondition(TestAutoCode testAutoCode);

    /**
	* 更新(忽略null)
	*/
    Integer updateTestAutoCodeByKeyWithNotNull(TestAutoCode testAutoCode);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<TestAutoCode> selectAll(TestAutoCode testAutoCode);

    int updateByPrimaryKey(TestAutoCode record);
}