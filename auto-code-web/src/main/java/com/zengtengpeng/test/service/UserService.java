package com.zengtengpeng.test.service;








import java.util.List;
import com.zengtengpeng.common.service.BaseService;
import com.zengtengpeng.test.bean.User;
import com.zengtengpeng.test.dao.UserDao;


/**
 *用户 service
 */
public interface UserService extends BaseService<User,UserDao>{


















	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public User selectUserAndTestRole(User user);
	/**
	 * 级联查询(带分页) 用户--角色
	 */
	public List<User> selectUserAndTestRoleByCondition(User user);
	/**
	 * 级联删除(根据主键删除) 用户--角色
	 */
	public Integer deleteUserAndTestRole(User user);
	/**
	 * 根据外表id查询主表表所有数据(带分页)
	 */
	public User selectUserByTestRole(User user);








}
