package com.zengtengpeng.simple.service.impl;
import com.zengtengpeng.simple.dao.SimpleCodeDao;
import com.zengtengpeng.simple.service.SimpleCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *单表代码生成 serverImpl
 */
@Service
@Transactional
public class SimpleCodeServiceImpl   implements SimpleCodeService {


	/**
	 * 注入dao
	 */
	@Resource
	private SimpleCodeDao simpleCodeDao;
	/**
	 * 初始化
	 */
	@Override
	public SimpleCodeDao initDao(){
		return simpleCodeDao;
	}


}
