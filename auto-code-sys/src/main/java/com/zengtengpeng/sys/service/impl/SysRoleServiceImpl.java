package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.service.SysRoleService;
import com.zengtengpeng.sys.dao.SysRoleDao;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;

	
	@Override
	public int deleteByPrimaryKey(SysRole sysRole){
		return sysRoleDao.deleteByPrimaryKey(sysRole.getId());
	}

	@Override
	public int insert(SysRole sysRole){
    	return sysRoleDao.insert(sysRole);
    }

	@Override
	public SysRole selectByPrimaryKey(SysRole sysRole){
    	return sysRoleDao.selectByPrimaryKey(sysRole.getId());
    }

   
	@Override
	public List<SysRole> querySysRoleByCondition(SysRole sysRole){
    	return sysRoleDao.querySysRoleByCondition(sysRole);
    }

     
	@Override
	public SysRole selectAll(SysRole sysRole){
    	return PagingUtils.queryData(sysRole, sysRoleDao, null);
    }

	
	@Override
	public int updateByPrimaryKey(SysRole sysRole){
    	return sysRoleDao.updateSysRoleByKeyWithNotNull(sysRole);
    }

	@Override
	public List<SysRole> export(SysRole sysRole) {
    return sysRoleDao.selectAll(sysRole);
    }
}
