package com.zengtengpeng.ManyToMany.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.ManyToMany.bean.ManyToManyUser;
import com.zengtengpeng.ManyToMany.dao.ManyToManyUserDao;


/**
 *多对多用户 service
 */
public interface ManyToManyUserService extends BaseService<ManyToManyUser,ManyToManyUserDao>{




	/**
	 * 级联查询(带分页) 多对多用户--多对多角色
	 */
	public ManyToManyUser selectManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 级联查询(带分页) 多对多用户--多对多角色
	 */
	public List<ManyToManyUser> selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyUser manyToManyUser);
	/**
	 * 级联删除(根据主键删除) 多对多用户--多对多角色
	 */
	public Integer deleteManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	public ManyToManyUser selectManyToManyUserByManyToManyRole(ManyToManyUser manyToManyUser);

}
