package com.zengtengpeng.sys.dao;

import com.zengtengpeng.sys.bean.SysRole;
import java.util.List;

public interface SysRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<SysRole> querySysRoleByCondition(SysRole sysRole);

    /**
	* 更新(忽略null)
	*/
    Integer updateSysRoleByKeyWithNotNull(SysRole sysRole);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<SysRole> selectAll(SysRole sysRole);

    int updateByPrimaryKey(SysRole record);
}