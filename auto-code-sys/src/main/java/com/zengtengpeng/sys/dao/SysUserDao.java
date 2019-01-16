package com.zengtengpeng.sys.dao;

import com.zengtengpeng.sys.bean.SysUser;
import java.util.List;

public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<SysUser> querySysUserByCondition(SysUser sysUser);

    /**
	* 更新(忽略null)
	*/
    Integer updateSysUserByKeyWithNotNull(SysUser sysUser);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<SysUser> selectAll(SysUser sysUser);

    int updateByPrimaryKey(SysUser record);
}