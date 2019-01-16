package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.service.SysUserService;
import com.zengtengpeng.sys.dao.SysUserDao;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;

	
	@Override
	public int deleteByPrimaryKey(SysUser sysUser){
		return sysUserDao.deleteByPrimaryKey(sysUser.getId());
	}

	@Override
	public int insert(SysUser sysUser){
    	return sysUserDao.insert(sysUser);
    }

	@Override
	public SysUser selectByPrimaryKey(SysUser sysUser){
    	return sysUserDao.selectByPrimaryKey(sysUser.getId());
    }

   
	@Override
	public List<SysUser> querySysUserByCondition(SysUser sysUser){
    	return sysUserDao.querySysUserByCondition(sysUser);
    }

     
	@Override
	public SysUser selectAll(SysUser sysUser){
    	return PagingUtils.queryData(sysUser, sysUserDao, null);
    }

	
	@Override
	public int updateByPrimaryKey(SysUser sysUser){
    	return sysUserDao.updateSysUserByKeyWithNotNull(sysUser);
    }

	@Override
	public List<SysUser> export(SysUser sysUser) {
    return sysUserDao.selectAll(sysUser);
    }
}
