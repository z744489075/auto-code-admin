package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.dao.SysRoleDao;
import com.zengtengpeng.common.service.BaseService;
import java.util.List;
public interface SysRoleService extends  BaseService<SysRole, SysRoleDao> {


    int insert(SysRole sysRole, List<String> auths);

    int update(SysRole sysRole, List<String> auths);


}