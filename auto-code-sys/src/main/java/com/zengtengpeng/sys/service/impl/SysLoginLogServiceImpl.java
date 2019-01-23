package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysLoginLog;
import com.zengtengpeng.sys.service.SysLoginLogService;
import com.zengtengpeng.sys.dao.SysLoginLogDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLoginLogServiceImpl implements SysLoginLogService {

	@Resource
	private SysLoginLogDao sysLoginLogDao;


    @Override
    public SysLoginLogDao initDao() {
        return sysLoginLogDao;
    }
}
