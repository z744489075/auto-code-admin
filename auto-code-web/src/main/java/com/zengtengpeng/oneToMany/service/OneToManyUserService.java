package com.zengtengpeng.oneToMany.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.oneToMany.bean.OneToManyUser;
import com.zengtengpeng.oneToMany.dao.OneToManyUserDao;


/**
 *一对多用户 service
 */
public interface OneToManyUserService extends BaseService<OneToManyUser,OneToManyUserDao>{




	/**
	 * 级联查询(带分页) 一对多用户--一对多收货地址
	 */
	public OneToManyUser selectOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser);
	/**
	 * 级联查询(带分页) 一对多用户--一对多收货地址
	 */
	public List<OneToManyUser> selectOneToManyUserAndOneToManyAddrByCondition(OneToManyUser oneToManyUser);
	/**
	 * 级联删除(根据主键删除) 一对多用户--一对多收货地址
	 */
	public Integer deleteOneToManyUserAndOneToManyAddr(OneToManyUser oneToManyUser);

}
