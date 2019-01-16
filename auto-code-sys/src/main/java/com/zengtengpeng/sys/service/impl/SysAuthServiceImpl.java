package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.service.SysAuthService;
import com.zengtengpeng.sys.dao.SysAuthDao;

@Service
public class SysAuthServiceImpl implements SysAuthService {

	@Resource
	private SysAuthDao sysAuthDao;

	
	@Override
	public int deleteByPrimaryKey(SysAuth sysAuth){
		return sysAuthDao.deleteByPrimaryKey(sysAuth.getId());
	}

	@Override
	public int insert(SysAuth sysAuth){
    	return sysAuthDao.insert(sysAuth);
    }

	@Override
	public SysAuth selectByPrimaryKey(SysAuth sysAuth){
    	return sysAuthDao.selectByPrimaryKey(sysAuth.getId());
    }

   
	@Override
	public List<SysAuth> querySysAuthByCondition(SysAuth sysAuth){
    	return sysAuthDao.querySysAuthByCondition(sysAuth);
    }

     
	@Override
	public SysAuth selectAll(SysAuth sysAuth){
    	return PagingUtils.queryData(sysAuth, sysAuthDao, null);
    }

	
	@Override
	public int updateByPrimaryKey(SysAuth sysAuth){
    	return sysAuthDao.updateSysAuthByKeyWithNotNull(sysAuth);
    }

	@Override
	public List<SysAuth> export(SysAuth sysAuth) {
    return sysAuthDao.selectAll(sysAuth);
    }
}
