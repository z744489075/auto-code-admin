package com.zengtengpeng.sys.dao;

import com.zengtengpeng.sys.bean.SysAuth;
import java.util.List;

public interface SysAuthDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAuth record);

    SysAuth selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<SysAuth> querySysAuthByCondition(SysAuth sysAuth);

    /**
	* 更新(忽略null)
	*/
    Integer updateSysAuthByKeyWithNotNull(SysAuth sysAuth);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<SysAuth> selectAll(SysAuth sysAuth);

    int updateByPrimaryKey(SysAuth record);
}