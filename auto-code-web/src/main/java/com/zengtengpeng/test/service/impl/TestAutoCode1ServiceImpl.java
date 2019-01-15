package com.zengtengpeng.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.test.bean.TestAutoCode1;
import com.zengtengpeng.test.service.TestAutoCode1Service;
import com.zengtengpeng.test.dao.TestAutoCode1Dao;

@Service
public class TestAutoCode1ServiceImpl implements TestAutoCode1Service {

	@Resource
	private TestAutoCode1Dao testAutoCode1Dao;

	
	@Override
	public int deleteByPrimaryKey(TestAutoCode1 testAutoCode1){
		return testAutoCode1Dao.deleteByPrimaryKey(testAutoCode1.getId());
	}

	@Override
	public int insert(TestAutoCode1 testAutoCode1){
    	return testAutoCode1Dao.insert(testAutoCode1);
    }

	@Override
	public TestAutoCode1 selectByPrimaryKey(TestAutoCode1 testAutoCode1){
    	return testAutoCode1Dao.selectByPrimaryKey(testAutoCode1.getId());
    }

   
	@Override
	public List<TestAutoCode1> queryTestAutoCode1ByCondition(TestAutoCode1 testAutoCode1){
    	return testAutoCode1Dao.queryTestAutoCode1ByCondition(testAutoCode1);
    }

     
	@Override
	public TestAutoCode1 selectAll(TestAutoCode1 testAutoCode1){
    	return PagingUtils.queryData(testAutoCode1, testAutoCode1Dao, null);
    }

	
	@Override
	public int updateByPrimaryKey(TestAutoCode1 testAutoCode1){
    	return testAutoCode1Dao.updateTestAutoCode1ByKeyWithNotNull(testAutoCode1);
    }

	@Override
	public List<TestAutoCode1> export(TestAutoCode1 testAutoCode1) {
    return testAutoCode1Dao.selectAll(testAutoCode1);
    }
}
