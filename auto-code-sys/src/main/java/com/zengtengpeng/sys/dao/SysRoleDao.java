package com.zengtengpeng.sys.dao;

import com.zengtengpeng.common.dao.BaseDao;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.bean.SysUser;

import java.util.List;

public interface SysRoleDao extends BaseDao<SysRole> {
    /**
     * 删除权限
     * @param sysRole
     */
    void deletAuths(SysRole sysRole);

    /**
     * 查询用户已有的权限
     * @param sysUser
     * @return
     */
    List<SysRole> queryByUser(SysUser sysUser);
}