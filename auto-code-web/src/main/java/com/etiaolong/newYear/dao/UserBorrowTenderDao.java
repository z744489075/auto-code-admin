package com.etiaolong.newYear.dao;

import com.etiaolong.newYear.bean.UserBorrowTender;
import java.util.List;

public interface UserBorrowTenderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBorrowTender record);

    UserBorrowTender selectByPrimaryKey(Integer id);

    /**
	* 根据条件查询
	*/
    List<UserBorrowTender> queryUserBorrowTenderByCondition(UserBorrowTender userBorrowTender);

    /**
	* 更新(忽略null)
	*/
    Integer updateUserBorrowTenderByKeyWithNotNull(UserBorrowTender userBorrowTender);

     /**
	* 分页查询
	* @param page 参数
	* @return
	*/
    List<UserBorrowTender> selectAll(UserBorrowTender userBorrowTender);

    int updateByPrimaryKey(UserBorrowTender record);
}