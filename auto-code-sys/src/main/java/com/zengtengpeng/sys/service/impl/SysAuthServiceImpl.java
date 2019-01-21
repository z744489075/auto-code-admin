package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import com.zengtengpeng.sys.bean.SysRole;
import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysAuth;
import com.zengtengpeng.sys.service.SysAuthService;
import com.zengtengpeng.sys.dao.SysAuthDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysAuthServiceImpl implements SysAuthService {

	@Resource
	private SysAuthDao sysAuthDao;


    @Override
    public SysAuthDao initDao() {
        return sysAuthDao;
    }

    @Override
    public List<SysAuth> queryByRole(SysRole sysRole) {
        return sysAuthDao.queryByRole(sysRole);
    }

    @Override
    public List<SysAuth> queryByUser(Integer userId) {
        return sysAuthDao.queryByUser(userId);
    }
}
