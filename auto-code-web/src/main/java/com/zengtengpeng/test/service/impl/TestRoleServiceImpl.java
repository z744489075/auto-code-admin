package com.zengtengpeng.test.service.impl;

import com.zengtengpeng.test.dao.UserDao;
import com.zengtengpeng.test.bean.User;
import com.zengtengpeng.test.bean.TestRole;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.test.dao.TestRoleDao;
import com.zengtengpeng.test.service.TestRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *测试角色 serverImpl
 */
@Service
@Transactional
public class TestRoleServiceImpl   implements TestRoleService {

	/**
	 * 角色
	 */
	@Resource
	private UserDao userDao;



	/**
	 * 注入dao
	 */
	@Resource
	private TestRoleDao testRoleDao;
	/**
	 * 初始化
	 */
	@Override
	public TestRoleDao initDao(){
		return testRoleDao;
	}


	/**
	 * 级联查询(带分页) 角色--用户
	 */
	@Override
	public TestRole selectUserAndTestRole(TestRole testRole){
		testRole = this.selectAllByPaging(testRole);
		if(testRole!=null && testRole.getRows()!=null){
			testRole.getRows().forEach(t->{
				TestRole data= (TestRole) t;
				User user=new User();
				user.setRoleId(data.getId().toString());
				List<User> datas=userDao.selectUserByTestRole(user);
				data.setUserList(datas);
			});
		}
		return testRole;

	}


	/**
	 * 级联条件查询用户--角色
	 */
	@Override
	public List<TestRole> selectUserAndTestRoleByCondition(TestRole testRole){
		List<TestRole> datas = this.selectByCondition(testRole);
		if(datas!=null){
			datas.forEach(t->{
				User user=new User();
				user.setRoleId(t.getId().toString());
				List<User> lists=userDao.selectUserByTestRole(user);
				t.setUserList(lists);
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 角色--用户
	 */
	@Override
	public Integer deleteUserAndTestRole(TestRole testRole){
		User user=new User();
		testRole = testRoleDao.selectByPrimaryKey(testRole);
		user.setRoleId(testRole.getId().toString());
		userDao.deleteUserByTestRole(user);
		return testRoleDao.deleteByPrimaryKey(testRole);

	}


	/**
	 * 根据主表id查询外表所有数据(带分页)
	 */
	@Override
	public TestRole selectTestRoleByUser(TestRole t){
		PageHelper.startPage(t.getPage(), t.getPageSize());
		List<TestRole> lists = testRoleDao.selecttestRoleByUser(t);
		PageInfo pageInfo = new PageInfo(lists);
		t.setRows(lists);
		t.setTotal((new Long(pageInfo.getTotal())).intValue());
		return t;

	}



}
