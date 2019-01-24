package com.zengtengpeng.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.zengtengpeng.test.bean.TestCode;
import com.zengtengpeng.test.service.TestCodeService;
import com.zengtengpeng.test.dao.TestCodeDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestCodeServiceImpl implements TestCodeService {

	@Resource
	private TestCodeDao testCodeDao;


    @Override
    public TestCodeDao initDao() {
        return testCodeDao;
    }
}
