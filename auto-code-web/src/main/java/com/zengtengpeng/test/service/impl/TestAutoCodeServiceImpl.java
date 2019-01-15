package com.zengtengpeng.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.test.bean.TestAutoCode;
import com.zengtengpeng.test.service.TestAutoCodeService;
import com.zengtengpeng.test.dao.TestAutoCodeDao;

@Service
public class TestAutoCodeServiceImpl implements TestAutoCodeService {

	@Resource
	private TestAutoCodeDao testAutoCodeDao;

	
	@Override
	public int deleteByPrimaryKey(TestAutoCode testAutoCode){
		return testAutoCodeDao.deleteByPrimaryKey(testAutoCode.getId());
	}

	@Override
	public int insert(TestAutoCode testAutoCode){
    	return testAutoCodeDao.insert(testAutoCode);
    }

	@Override
	public TestAutoCode selectByPrimaryKey(TestAutoCode testAutoCode){
    	return testAutoCodeDao.selectByPrimaryKey(testAutoCode.getId());
    }

   
	@Override
	public List<TestAutoCode> queryTestAutoCodeByCondition(TestAutoCode testAutoCode){
    	return testAutoCodeDao.queryTestAutoCodeByCondition(testAutoCode);
    }

     
	@Override
	public TestAutoCode selectAll(TestAutoCode testAutoCode){
    	return PagingUtils.queryData(testAutoCode, testAutoCodeDao, null);
    }

	
	@Override
	public int updateByPrimaryKey(TestAutoCode testAutoCode){
    	return testAutoCodeDao.updateTestAutoCodeByKeyWithNotNull(testAutoCode);
    }

	@Override
	public List<TestAutoCode> export(TestAutoCode testAutoCode) {
    return testAutoCodeDao.selectAll(testAutoCode);
    }
}
