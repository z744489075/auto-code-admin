package com.zengtengpeng.manyToMany.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.manyToMany.bean.ManyToManyUser;


/**
 *用户 dao
 */
@Mapper
public interface ManyToManyUserDao  extends BaseDao<ManyToManyUser>{




	/**
	 * 根据角色查询用户
	 */
	public List<ManyToManyUser> selectManyToManyUserByManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 根据角色删除用户
	 */
	public Integer deleteManyToManyUserByManyToManyRole(ManyToManyUser manyToManyUser);
	/**
	 * 级联新增
	 */
	public Integer insertRelation(@Param("id") String id,@Param("roleId") String[] roleId);

}
