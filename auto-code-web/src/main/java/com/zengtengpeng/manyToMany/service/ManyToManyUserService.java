package com.zengtengpeng.manyToMany.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.manyToMany.bean.ManyToManyUser;
import com.zengtengpeng.manyToMany.dao.ManyToManyUserDao;


/**
 *用户 service
 */
public interface ManyToManyUserService extends BaseService<ManyToManyUser,ManyToManyUserDao>{




	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public ManyToManyUser selectManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public List<ManyToManyUser> selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyUser manyToManyUser);
	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	public Integer deleteManyToManyUserAndManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	public ManyToManyUser selectManyToManyUserByManyToManyRole(ManyToManyUser manyToManyUser);

}
