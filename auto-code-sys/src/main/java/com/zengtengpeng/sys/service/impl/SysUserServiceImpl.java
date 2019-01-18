package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import com.zengtengpeng.sys.dao.SysRoleDao;
import org.springframework.stereotype.Service;
import java.util.List;

import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.service.SysUserService;
import com.zengtengpeng.sys.dao.SysUserDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao sysRoleDao;


    @Override
    public SysUserDao initDao() {
        return sysUserDao;
    }

    @Override
    public int insert(SysUser sysUser, List<String> roles) {
        int insert = sysUserDao.insert(sysUser);
        if(roles.size()>0){
            sysUserDao.insertBatch(sysUser.getId(),roles);
        }
        return insert;
    }

    @Override
    public int update(SysUser sysUser, List<String> roles) {
        Integer update = sysUserDao.update(sysUser);
        sysUserDao.deleteRoles(sysUser);
        if(roles!=null && roles.size()>0) {
            sysUserDao.insertBatch(sysUser.getId(),roles);
        }
        return update;
    }
}
