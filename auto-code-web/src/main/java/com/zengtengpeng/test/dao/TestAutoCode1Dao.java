package com.zengtengpeng.test.dao;

import com.zengtengpeng.test.bean.TestAutoCode1;
import java.util.List;

public interface TestAutoCode1Dao {
    int deleteByPrimaryKey(Integer id);

    int insert(TestAutoCode1 record);

    TestAutoCode1 selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<TestAutoCode1> queryTestAutoCode1ByCondition(TestAutoCode1 testAutoCode1);

    /**
	* 更新(忽略null)
	*/
    Integer updateTestAutoCode1ByKeyWithNotNull(TestAutoCode1 testAutoCode1);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<TestAutoCode1> selectAll(TestAutoCode1 testAutoCode1);

    int updateByPrimaryKey(TestAutoCode1 record);
}