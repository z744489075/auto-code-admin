package com.zengtengpeng.oneToMany.service.impl;

import com.zengtengpeng.oneToMany.dao.OneToManyUserDao;
import com.zengtengpeng.oneToMany.bean.OneToManyUser;
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.oneToMany.dao.OneToManyAddrDao;
import com.zengtengpeng.oneToMany.service.OneToManyAddrService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *用户收货地址 serverImpl
 */
@Service
@Transactional
public class OneToManyAddrServiceImpl   implements OneToManyAddrService {

	/**
	 * 收货地址
	 */
	@Resource
	private OneToManyUserDao oneToManyUserDao;



	/**
	 * 注入dao
	 */
	@Resource
	private OneToManyAddrDao oneToManyAddrDao;
	/**
	 * 初始化
	 */
	@Override
	public OneToManyAddrDao initDao(){
		return oneToManyAddrDao;
	}


	/**
	 * 级联查询(带分页) 用户--收货地址
	 */
	@Override
	public OneToManyAddr selectOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr){
		oneToManyAddr = this.selectAllByPaging(oneToManyAddr);
		if(oneToManyAddr!=null && oneToManyAddr.getRows()!=null){
			oneToManyAddr.getRows().forEach(t->{
				OneToManyAddr data= (OneToManyAddr) t;
				OneToManyUser oneToManyUser=new OneToManyUser();
				oneToManyUser.setId(data.getUserId());
				data.setOneToManyUser(oneToManyUserDao.selectByPrimaryKey(oneToManyUser));
			});
		}
		return oneToManyAddr;

	}


	/**
	 * 级联条件查询用户--收货地址
	 */
	@Override
	public List<OneToManyAddr> selectOneToManyUserAndOneToManyAddrByCondition(OneToManyAddr oneToManyAddr){
		List<OneToManyAddr> datas = this.selectByCondition(oneToManyAddr);
		if(datas!=null){
			datas.forEach(t->{
				OneToManyUser oneToManyUser=new OneToManyUser();
				oneToManyUser.setId(t.getUserId());
				t.setOneToManyUser(oneToManyUserDao.selectByPrimaryKey(oneToManyUser));
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--收货地址
	 */
	@Override
	public Integer deleteOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr){
		oneToManyAddr = oneToManyAddrDao.selectByPrimaryKey(oneToManyAddr);
		if(oneToManyAddr!=null){
			OneToManyUser oneToManyUser=new OneToManyUser();
			oneToManyUser.setId(oneToManyAddr.getUserId());
			oneToManyUserDao.deleteByPrimaryKey(oneToManyUser);
		}
		return oneToManyAddrDao.deleteByPrimaryKey(oneToManyAddr);

	}



}
