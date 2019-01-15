package com.zengtengpeng.test.dao;

import com.zengtengpeng.test.bean.TestAutoCode2;
import java.util.List;

public interface TestAutoCode2Dao {
    int deleteByPrimaryKey(Integer codeId);

    int insert(TestAutoCode2 record);

    TestAutoCode2 selectByPrimaryKey(Integer codeId);

    /**
	* 根据条件查询
	*/
    List<TestAutoCode2> queryTestAutoCode2ByCondition(TestAutoCode2 testAutoCode2);

    /**
	* 更新(忽略null)
	*/
    Integer updateTestAutoCode2ByKeyWithNotNull(TestAutoCode2 testAutoCode2);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<TestAutoCode2> selectAll(TestAutoCode2 testAutoCode2);

    int updateByPrimaryKey(TestAutoCode2 record);
}