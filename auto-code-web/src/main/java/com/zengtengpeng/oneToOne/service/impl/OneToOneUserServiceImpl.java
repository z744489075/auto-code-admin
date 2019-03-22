package com.zengtengpeng.oneToOne.service.impl;

import com.zengtengpeng.oneToOne.dao.OneToOneClassDao;
import com.zengtengpeng.oneToOne.bean.OneToOneUser;
import com.zengtengpeng.oneToOne.bean.OneToOneClass;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zengtengpeng.oneToOne.dao.OneToOneUserDao;
import com.zengtengpeng.oneToOne.service.OneToOneUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 *一对一用户 serverImpl
 */
@Service
@Transactional
public class OneToOneUserServiceImpl   implements OneToOneUserService {

	/**
	 * 一对一班级
	 */
	@Resource
	private OneToOneClassDao oneToOneClassDao;



	/**
	 * 注入dao
	 */
	@Resource
	private OneToOneUserDao oneToOneUserDao;
	/**
	 * 初始化
	 */
	@Override
	public OneToOneUserDao initDao(){
		return oneToOneUserDao;
	}


	/**
	 * 级联查询(带分页) 一对一用户--一对一班级
	 */
	@Override
	public OneToOneUser selectOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser){
		oneToOneUser = this.selectAllByPaging(oneToOneUser);
		if(oneToOneUser!=null && oneToOneUser.getRows()!=null){
			oneToOneUser.getRows().forEach(t->{
				OneToOneUser data= (OneToOneUser) t;
				OneToOneClass oneToOneClass=new OneToOneClass();
				oneToOneClass.setUserId(data.getId());
				List<OneToOneClass> lists = oneToOneClassDao.selectByCondition(oneToOneClass);
				if(lists!=null && lists.size()>0){
					data.setOneToOneClass(lists.get(0));
				}
			});
		}
		return oneToOneUser;

	}


	/**
	 * 级联条件查询 一对一用户--一对一班级
	 */
	@Override
	public List<OneToOneUser> selectOneToOneUserAndOneToOneClassByCondition(OneToOneUser oneToOneUser){
		List<OneToOneUser> datas = this.selectByCondition(oneToOneUser);
		if(datas!=null){
			datas.forEach(t->{
				OneToOneClass oneToOneClass=new OneToOneClass();
				oneToOneClass.setUserId(t.getId());
				List<OneToOneClass> lists = oneToOneClassDao.selectByCondition(oneToOneClass);
				if(lists!=null && lists.size()>0){
					t.setOneToOneClass(lists.get(0));
				}
			});
		}
		return datas;

	}


	/**
	 * 级联删除(根据主表删除) 一对一用户--一对一班级
	 */
	@Override
	public Integer deleteOneToOneUserAndOneToOneClass(OneToOneUser oneToOneUser){
		OneToOneClass oneToOneClass=new OneToOneClass();
		oneToOneClass.setUserId(oneToOneUser.getId());
		oneToOneClassDao.deleteOneToOneClassByOneToOneUser(oneToOneClass);
		return oneToOneUserDao.deleteByPrimaryKey(oneToOneUser);

	}



}
