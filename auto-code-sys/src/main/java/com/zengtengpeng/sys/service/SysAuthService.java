package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.dao.SysAuthDao;
import com.zengtengpeng.common.service.BaseService;
import java.util.List;
public interface SysAuthService extends  BaseService<SysAuth, SysAuthDao> {

    /**
     * 查询角色下的权限
     * @param sysRole
     * @return
     */
    List<SysAuth> queryByRole(SysRole sysRole);
}