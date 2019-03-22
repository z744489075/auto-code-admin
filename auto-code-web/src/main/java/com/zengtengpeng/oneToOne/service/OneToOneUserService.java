package com.zengtengpeng.oneToOne.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.oneToOne.bean.OneToOneUser;
import com.zengtengpeng.oneToOne.dao.OneToOneUserDao;


/**
 *一对一用户 service
 */
public interface OneToOneUserService extends BaseService<OneToOneUser,OneToOneUserDao>{




	/**
	 * 级联查询(带分页) 一对一用户--一对一班级
	 */
	public OneToOneUser selectOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser);
	/**
	 * 级联查询(带分页) 一对一用户--一对一班级
	 */
	public List<OneToOneUser> selectOneToOneUserAndOneToOneClassByCondition(OneToOneUser oneToOneUser);
	/**
	 * 级联删除(根据主键删除) 一对一用户--一对一班级
	 */
	public Integer deleteOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser);

}
