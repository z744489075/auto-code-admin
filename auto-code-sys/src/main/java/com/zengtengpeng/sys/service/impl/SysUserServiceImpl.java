package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysUser;
import com.zengtengpeng.sys.service.SysUserService;
import com.zengtengpeng.sys.dao.SysUserDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDao;


    @Override
    public SysUserDao initDao() {
        return sysUserDao;
    }
}
