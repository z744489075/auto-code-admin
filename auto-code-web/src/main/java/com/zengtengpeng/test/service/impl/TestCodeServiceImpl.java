package com.zengtengpeng.test.service.impl;
import com.zengtengpeng.test.dao.TestCodeDao;
import com.zengtengpeng.test.service.TestCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *测试生成代码 serverImpl
 */
@Service
@Transactional
public class TestCodeServiceImpl   implements TestCodeService {


	/**
	 * 注入dao
	 */
	@Resource
	private TestCodeDao testCodeDao ;

	/**
	 * 初始化
	 */
	@Override
	public TestCodeDao initDao(){
		return testCodeDao;
	}


}
