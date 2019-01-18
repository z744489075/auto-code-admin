package com.zengtengpeng.sys.service;

import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.dao.SysUserDao;
import com.zengtengpeng.common.service.BaseService;
import java.util.List;
public interface SysUserService extends  BaseService<SysUser, SysUserDao> {


    int insert(SysUser sysUser, List<String> roles);

    int update(SysUser sysUser, List<String> roles);
}