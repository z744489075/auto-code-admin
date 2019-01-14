package com.etiaolong.newYear.service;

import com.etiaolong.newYear.bean.UserBorrowTender;
import java.util.List;
public interface UserBorrowTenderService {

	/**
	 * 删除
	 * @param userBorrowTender
	 * @return
	 */
	public abstract int deleteByPrimaryKey(UserBorrowTender userBorrowTender);

	/**
	 * 新增
	 * @param userBorrowTender
	 * @return
	 */
	public abstract int insert(UserBorrowTender userBorrowTender);

	/**
	 * 根据主键查询
	 * @param userBorrowTender
	 * @return
	 */
	public abstract UserBorrowTender selectByPrimaryKey(UserBorrowTender userBorrowTender);

	/**
	 * 根据条件查询
	 */
	public abstract List<UserBorrowTender> queryUserBorrowTenderByCondition(UserBorrowTender userBorrowTender);

	/**
	 * 分页查询
	 * @param userBorrowTender 参数
	 * @return
	 */
	public abstract UserBorrowTender selectAll(UserBorrowTender userBorrowTender);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	public abstract int updateByPrimaryKey(UserBorrowTender userBorrowTender);

	/**
	 * 导出所有数据
	 * @param tests
	 * @return
	 */
	List<UserBorrowTender> export(UserBorrowTender userBorrowTender);

}