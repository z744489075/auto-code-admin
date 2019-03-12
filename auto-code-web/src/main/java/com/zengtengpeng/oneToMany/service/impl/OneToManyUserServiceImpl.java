package com.zengtengpeng.oneToMany.service.impl;

import com.zengtengpeng.oneToMany.dao.OneToManyAddrDao;
import com.zengtengpeng.oneToMany.bean.OneToManyUser;
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.oneToMany.dao.OneToManyUserDao;
import com.zengtengpeng.oneToMany.service.OneToManyUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *用户 serverImpl
 */
@Service
@Transactional
public class OneToManyUserServiceImpl   implements OneToManyUserService {

	/**
	 * 收货地址
	 */
	@Resource
	private OneToManyAddrDao oneToManyAddrDao;



	/**
	 * 注入dao
	 */
	@Resource
	private OneToManyUserDao oneToManyUserDao;
	/**
	 * 初始化
	 */
	@Override
	public OneToManyUserDao initDao(){
		return oneToManyUserDao;
	}


	/**
	 * 级联查询(带分页) 用户--收货地址
	 */
	@Override
	public OneToManyUser selectOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser){
		oneToManyUser = this.selectAllByPaging(oneToManyUser);
		if(oneToManyUser!=null && oneToManyUser.getRows()!=null){
			oneToManyUser.getRows().forEach(t->{
				OneToManyUser data= (OneToManyUser) t;
				OneToManyAddr oneToManyAddr=new OneToManyAddr();
				oneToManyAddr.setUserId(data.getId());
				List<OneToManyAddr> lists = oneToManyAddrDao.selectByCondition(oneToManyAddr);
				data.setOneToManyAddrList(lists);
			});
		}
		return oneToManyUser;

	}


	/**
	 * 构建主表 级联条件查询 用户--收货地址
	 */
	@Override
	public List<OneToManyUser> selectOneToManyUserAndOneToManyAddrByCondition(OneToManyUser oneToManyUser){
		List<OneToManyUser> datas = this.selectByCondition(oneToManyUser);
		if(datas!=null){
			datas.forEach(t->{
				OneToManyAddr oneToManyAddr=new OneToManyAddr();
				oneToManyAddr.setUserId(t.getId());
				List<OneToManyAddr> lists = oneToManyAddrDao.selectByCondition(oneToManyAddr);
				t.setOneToManyAddrList(lists);
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--收货地址
	 */
	@Override
	public Integer deleteOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser){
		OneToManyAddr oneToManyAddr=new OneToManyAddr();
		oneToManyAddr.setUserId(oneToManyUser.getId());
		oneToManyAddrDao.deleteOneToManyAddrByOneToManyUser(oneToManyAddr);
		return oneToManyUserDao.deleteByPrimaryKey(oneToManyUser);

	}



}
