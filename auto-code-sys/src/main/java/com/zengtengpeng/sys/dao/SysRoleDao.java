package com.zengtengpeng.sys.dao;

import com.zengtengpeng.common.dao.BaseDao;
import com.zengtengpeng.sys.bean.SysRole;

public interface SysRoleDao extends BaseDao<SysRole> {
    /**
     * 删除权限
     * @param sysRole
     */
    void deletAuths(SysRole sysRole);
}