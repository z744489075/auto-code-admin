package com.zengtengpeng.test.service.impl;








import com.zengtengpeng.test.dao.TestRoleDao;
import com.zengtengpeng.test.bean.User;
import com.zengtengpeng.test.bean.TestRole;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.test.dao.UserDao;
import com.zengtengpeng.test.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *用户 serverImpl
 */
@Service
@Transactional
public class UserServiceImpl   implements UserService {















	/**
	 * 角色
	 */
	@Resource
	private TestRoleDao testRoleDao;



	/**
	 * 注入dao
	 */
	@Resource
	private UserDao userDao;
	/**
	 * 初始化
	 */
	@Override
	public UserDao initDao(){
		return userDao;
	}


	/**
	 * 级联查询(带分页) 用户--角色
	 */
	@Override
	public User selectUserAndTestRole(User user){
		user = this.selectAllByPaging(user);
		if(user!=null && user.getRows()!=null){
			user.getRows().forEach(t->{
				User data= (User) t;
				TestRole testRole=new TestRole();
				testRole.setUserId(data.getId().toString());
				List<TestRole> datas=testRoleDao.selectTestRoleByUser(testRole);
				data.setTestRoleList(datas);
			});
		}
		return user;

	}


	/**
	 * 级联条件查询 用户--角色
	 */
	@Override
	public List<User> selectUserAndTestRoleByCondition(User user){
		List<User> datas = this.selectByCondition(user);
		if(datas!=null){
			datas.forEach(t->{
				TestRole testRole=new TestRole();
				testRole.setUserId(t.getId().toString());
				List<TestRole> lists=testRoleDao.selectTestRoleByUser(testRole);
				t.setTestRoleList(lists);
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 用户--角色
	 */
	@Override
	public Integer deleteUserAndTestRole(User user){
		TestRole testRole=new TestRole();
		user = userDao.selectByPrimaryKey(user);
		testRole.setUserId(user.getId().toString());
		testRoleDao.deleteTestRoleByUser(testRole);
		return userDao.deleteByPrimaryKey(user);

	}


	/**
	 * 根据外表id查询主表所有数据(带分页)
	 */
	@Override
	public User selectUserByTestRole(User t){
		PageHelper.startPage(t.getPage(), t.getPageSize());
		List<User> lists = userDao.selectUserByTestRole(t);
		PageInfo pageInfo = new PageInfo(lists);
		t.setRows(lists);
		t.setTotal((new Long(pageInfo.getTotal())).intValue());
		return t;

	}










}
