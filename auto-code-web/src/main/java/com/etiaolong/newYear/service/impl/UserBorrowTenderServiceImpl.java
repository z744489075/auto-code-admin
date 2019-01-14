package com.etiaolong.newYear.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.zengtengpeng.common.utils.PagingUtils;
import com.etiaolong.newYear.bean.UserBorrowTender;
import com.etiaolong.newYear.service.UserBorrowTenderService;
import com.etiaolong.newYear.dao.UserBorrowTenderDao;

@Service
public class UserBorrowTenderServiceImpl implements UserBorrowTenderService {

	@Resource
	private UserBorrowTenderDao userBorrowTenderDao;

	
	@Override
	public int deleteByPrimaryKey(UserBorrowTender userBorrowTender){
		return userBorrowTenderDao.deleteByPrimaryKey(userBorrowTender.getId());
	}

	@Override
	public int insert(UserBorrowTender userBorrowTender){
    	return userBorrowTenderDao.insert(userBorrowTender);
    }

	@Override
	public UserBorrowTender selectByPrimaryKey(UserBorrowTender userBorrowTender){
    	return userBorrowTenderDao.selectByPrimaryKey(userBorrowTender.getId());
    }

   
	@Override
	public List<UserBorrowTender> queryUserBorrowTenderByCondition(UserBorrowTender userBorrowTender){
    	return userBorrowTenderDao.queryUserBorrowTenderByCondition(userBorrowTender);
    }

     
	@Override
	public UserBorrowTender selectAll(UserBorrowTender userBorrowTender){
    	return PagingUtils.queryData(userBorrowTender, userBorrowTenderDao, null);
    }

	
	@Override
	public int updateByPrimaryKey(UserBorrowTender userBorrowTender){
    	return userBorrowTenderDao.updateUserBorrowTenderByKeyWithNotNull(userBorrowTender);
    }

	@Override
	public List<UserBorrowTender> export(UserBorrowTender userBorrowTender) {
    return userBorrowTenderDao.selectAll(userBorrowTender);
    }
}
