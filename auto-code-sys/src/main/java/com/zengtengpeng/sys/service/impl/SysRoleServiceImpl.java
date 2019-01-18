package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.dao.SysAuthDao;
import org.springframework.stereotype.Service;
import java.util.List;

import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.service.SysRoleService;
import com.zengtengpeng.sys.dao.SysRoleDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysAuthDao sysAuthDao;


    @Override
    public SysRoleDao initDao() {
        return sysRoleDao;
    }

    @Override
    public int insert(SysRole sysRole, List<String> auths) {
        int insert = sysRoleDao.insert(sysRole);
        if(auths!=null && auths.size()>0){
            sysAuthDao.insertBatch(auths,sysRole.getId());
        }
        return insert;
    }

    @Override
    public int update(SysRole sysRole, List<String> auths) {
        Integer update = sysRoleDao.update(sysRole);
        sysRoleDao.deletAuths(sysRole);
        if(auths!=null && auths.size()>0) {
            sysAuthDao.insertBatch(auths, sysRole.getId());
        }
        return update;
    }

}
