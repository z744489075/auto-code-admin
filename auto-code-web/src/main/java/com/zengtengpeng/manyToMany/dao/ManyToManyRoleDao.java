package com.zengtengpeng.manyToMany.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.manyToMany.bean.ManyToManyRole;


/**
 *角色 dao
 */
@Mapper
public interface ManyToManyRoleDao  extends BaseDao<ManyToManyRole>{




	/**
	 * 根据用户查询角色
	 */
	public List<ManyToManyRole> selectManyToManyRoleByManyToManyUser(ManyToManyRole manyToManyRole);
	/**
	 * 根据用户删除角色
	 */
	public Integer deleteManyToManyRoleByManyToManyUser(ManyToManyRole manyToManyRole);
	/**
	 * 级联新增
	 */
	public Integer insertRelation(@Param("id") String id,@Param("userId") String[] userId);

}
