package com.zengtengpeng.test.dao;








import java.util.List;
import com.zengtengpeng.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import com.zengtengpeng.test.bean.User;


/**
 *用户 dao
 */
@Mapper
public interface UserDao  extends BaseDao<User>{


















	/**
	 * 根据角色查询用户
	 */
	public List<User> selectUserByTestRole(User user);
	/**
	 * 根据角色删除用户
	 */
	public Integer deleteUserByTestRole(User user);








}
