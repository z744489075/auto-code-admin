package com.zengtengpeng.oneToMany.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.oneToMany.bean.OneToManyAddr;
import com.zengtengpeng.oneToMany.dao.OneToManyAddrDao;


/**
 *用户收货地址 service
 */
public interface OneToManyAddrService extends BaseService<OneToManyAddr,OneToManyAddrDao>{




	/**
	 * 级联查询(带分页) 用户--收货地址
	 */
	public OneToManyAddr selectOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr);
	/**
	 * 级联条件查询 用户--收货地址
	 */
	public List<OneToManyAddr> selectOneToManyUserAndOneToManyAddrByCondition(OneToManyAddr oneToManyAddr);
	/**
	 * 级联删除(根据主键删除) 用户--收货地址
	 */
	public Integer deleteOneToManyUserAndOneToManyAddr(OneToManyAddr oneToManyAddr);

}
