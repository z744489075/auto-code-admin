package com.zengtengpeng.oneToOne.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.oneToOne.bean.OneToOneClass;
import com.zengtengpeng.oneToOne.dao.OneToOneClassDao;


/**
 *班级 service
 */
public interface OneToOneClassService extends BaseService<OneToOneClass,OneToOneClassDao>{




	/**
	 * 级联查询(带分页) 用户--班级
	 */
	public OneToOneClass selectOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass);
	/**
	 * 级联条件查询 用户--班级
	 */
	public List<OneToOneClass> selectOneToOneUserAndOneToOneClassByCondition(OneToOneClass oneToOneClass);
	/**
	 * 级联删除(根据主键删除) 用户--班级
	 */
	public Integer deleteOneToOneUserAndOneToOneClass(OneToOneClass oneToOneClass);

}
