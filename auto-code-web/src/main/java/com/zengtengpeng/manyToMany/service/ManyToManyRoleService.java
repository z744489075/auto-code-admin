package com.zengtengpeng.manyToMany.service;

import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.manyToMany.bean.ManyToManyRole;
import com.zengtengpeng.manyToMany.dao.ManyToManyRoleDao;


/**
 *角色 service
 */
public interface ManyToManyRoleService extends BaseService<ManyToManyRole,ManyToManyRoleDao>{




	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public ManyToManyRole selectManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole);
	/**
	 * 级联条件查询 用户--角色
	 */
	public List<ManyToManyRole> selectManyToManyUserAndManyToManyRoleByCondition(ManyToManyRole manyToManyRole);
	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	public Integer deleteManyToManyUserAndManyToManyRole(ManyToManyRole manyToManyRole);
	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	public ManyToManyRole selectManyToManyRoleByManyToManyUser(ManyToManyRole manyToManyRole);

}
