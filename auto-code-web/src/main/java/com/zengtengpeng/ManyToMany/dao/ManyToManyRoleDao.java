package com.zengtengpeng.ManyToMany.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.ManyToMany.bean.ManyToManyRole;


/**
 *多对多角色 dao
 */
@Mapper
public interface ManyToManyRoleDao  extends BaseDao<ManyToManyRole>{




	/**
	 * 根据多对多用户查询多对多角色
	 */
	public List<ManyToManyRole> selectManyToManyRoleByManyToManyUser(ManyToManyRole manyToManyRole);
	/**
	 * 根据多对多用户删除多对多角色
	 */
	public Integer deleteManyToManyRoleByManyToManyUser(ManyToManyRole manyToManyRole);
	/**
	 * 级联新增
	 */
	public Integer insertRelation(@Param("id") String id,@Param("userId") String[] userId);

}
