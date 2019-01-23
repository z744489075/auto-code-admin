package com.zengtengpeng.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.sys.bean.SysOperationLog;
import com.zengtengpeng.sys.service.SysOperationLogService;
import com.zengtengpeng.sys.dao.SysOperationLogDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOperationLogServiceImpl implements SysOperationLogService {

	@Resource
	private SysOperationLogDao sysOperationLogDao;


    @Override
    public SysOperationLogDao initDao() {
        return sysOperationLogDao;
    }
}
