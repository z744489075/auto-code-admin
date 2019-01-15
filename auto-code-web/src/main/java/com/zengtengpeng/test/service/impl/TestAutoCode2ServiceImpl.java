package com.zengtengpeng.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.test.bean.TestAutoCode2;
import com.zengtengpeng.test.service.TestAutoCode2Service;
import com.zengtengpeng.test.dao.TestAutoCode2Dao;

@Service
public class TestAutoCode2ServiceImpl implements TestAutoCode2Service {

	@Resource
	private TestAutoCode2Dao testAutoCode2Dao;

	
	@Override
	public int deleteByPrimaryKey(TestAutoCode2 testAutoCode2){
		return testAutoCode2Dao.deleteByPrimaryKey(testAutoCode2.getCodeId());
	}

	@Override
	public int insert(TestAutoCode2 testAutoCode2){
    	return testAutoCode2Dao.insert(testAutoCode2);
    }

	@Override
	public TestAutoCode2 selectByPrimaryKey(TestAutoCode2 testAutoCode2){
    	return testAutoCode2Dao.selectByPrimaryKey(testAutoCode2.getCodeId());
    }

   
	@Override
	public List<TestAutoCode2> queryTestAutoCode2ByCondition(TestAutoCode2 testAutoCode2){
    	return testAutoCode2Dao.queryTestAutoCode2ByCondition(testAutoCode2);
    }

     
	@Override
	public TestAutoCode2 selectAll(TestAutoCode2 testAutoCode2){
    	return PagingUtils.queryData(testAutoCode2, testAutoCode2Dao, null);
    }

	
	@Override
	public int updateByPrimaryKey(TestAutoCode2 testAutoCode2){
    	return testAutoCode2Dao.updateTestAutoCode2ByKeyWithNotNull(testAutoCode2);
    }

	@Override
	public List<TestAutoCode2> export(TestAutoCode2 testAutoCode2) {
    return testAutoCode2Dao.selectAll(testAutoCode2);
    }
}
