package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysRole;
import com.zengtengpeng.sys.service.SysRoleService;
import com.zengtengpeng.sys.dao.SysRoleDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;


    @Override
    public SysRoleDao initDao() {
        return sysRoleDao;
    }
}
